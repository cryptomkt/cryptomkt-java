package com.cryptomkt.api.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.List;

public class JSONPatcher {
    static DiffMatchPatch dmp = new DiffMatchPatch();

    public static Object patch(Object object, Object delta) throws JSONException, JSONPatchException {
        if (delta instanceof String) {
            return JSONPatcher.patch(object, new JSONObject((String) delta));
        }
        // if it's not a String, then must be a JSONObject
        if (delta instanceof JSONObject) {
            if (JSONPatcher.isArrayPatch((JSONObject) delta)) {
                return JSONPatcher.patchArray((JSONArray) object, (JSONObject) delta);
            } else if (JSONPatcher.isObjectPatch((JSONObject) delta)) {
                return JSONPatcher.patchObject((JSONObject) object, (JSONObject) delta);
            }
        } else if (JSONPatcher.isValuePatch(delta)) { // is instance of JSONArray
            if (!JSONPatcher.isRemoval((JSONArray) delta)) {
                return JSONPatcher.patchValue(object, (JSONArray) delta);
            }
        } else { // if we are here then we have an error
            throw new JSONPatchException("invalid JSON format");
        }
        return null;
    }

    private static boolean isValuePatch(Object object) {
        return object instanceof JSONArray;
    }

    private static JSONObject patchObject(JSONObject jsonObject, JSONObject delta) throws JSONException, JSONPatchException {
        Iterator<?> iter = delta.keys();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            if (!jsonObject.has(key)) {
                jsonObject.put(key, JSONObject.NULL);
            }
            jsonObject.put(key, JSONPatcher.patch(jsonObject.get(key), delta.get(key)));
        }
        return jsonObject;
    }

    private static Object patchValue(Object object, JSONArray delta) throws JSONException, JSONPatchException {
        if (JSONPatcher.isAddition(delta)) {
            return delta.get(0);
        } else if (JSONPatcher.isModification(delta)) {
            return delta.get(1);
        } else if (JSONPatcher.isRemoval(delta)) { // there is no need to this case
            // arrays don't remove values like this, and object remove values before
            // calling this method
            throw new JSONPatchException("removal should not happen here");
        } else if (JSONPatcher.isTextModification(delta)) {
            LinkedList<DiffMatchPatch.Patch> patches = (LinkedList<DiffMatchPatch.Patch>) dmp.patch_fromText((String) delta.get(0));
            Object[] patched = dmp.patch_apply(patches, (String) object);
            return patched[0];
        } else { // there are no more cases, so an error just happen if we are here
            throw new JSONPatchException("invalid delta of value");
        }
    }

    private static boolean isTextModification(JSONArray jsonArray) throws JSONException {
        return jsonArray.length() == 3 && (int) jsonArray.get(2) == 2;
    }

    private static boolean isRemoval(JSONArray jsonArray) throws JSONException {
        return jsonArray.length() == 3 && (int) jsonArray.get(2) == 0;
    }

    private static boolean isModification(JSONArray jsonArray) {
        return jsonArray.length() == 2;
    }

    private static boolean isAddition(JSONArray jsonArray) {
        return jsonArray.length() == 1;
    }

    private static JSONArray patchArray(JSONArray jsonArray, JSONObject delta) throws JSONException, JSONPatchException {
        List<Integer> removeList = new ArrayList<>();
        List<Int3Tuple> insertList = new ArrayList<>();
        List<Integer> patchList = new ArrayList<>();
        Iterator<?> iter = delta.keys();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            if (key.charAt(0) == '_' && key.charAt(1) != 't') { // is old index
                int index = Integer.parseInt(key.substring(1));
                Object innerDelta = delta.get(key);
                if (innerDelta instanceof JSONArray) {
                    if (JSONPatcher.isRemoval((JSONArray) innerDelta)) { // is a removal in array
                        removeList.add(index);
                    } else if (JSONPatcher.isMovement((JSONArray) innerDelta)) { // is a movement in array
                        removeList.add(index);
                        // 3-tuple is (from idx, to idx, arr or delta ref)
                        insertList.add(Int3Tuple.newArrayRef(index, (Integer) ((JSONArray) innerDelta).get(1)));
                    } else { // if we are here, the change with an old index
                        // is not a removal or a movement, so its an error
                        throw new JSONPatchException("old index delta is not a remove or a move:" + key);
                    }
                } else { // if we are here, the change with an old index is not a removal or a movement, so its an error
                    throw new JSONPatchException("old index delta is not a remove or a move: " + key);
                }
            } else if (key.charAt(0) != '_') { // is new index
                int index = Integer.parseInt(key);
                Object innerDelta = delta.get(key);
                if ((innerDelta instanceof JSONArray) && (((JSONArray) innerDelta).length() == 1)) { // is an insertion
                        // 3-tuple is (from idx, to idx, arr or delta ref)
                        insertList.add(Int3Tuple.newDeltaRef(index, index));
                } else {
                    // is a patch to apply over an existing value in the array
                        patchList.add(index);
                }
            } else { // if we are here, then it was the "_t" key in the delta to indicate an array delta
                // otherwise is an error
                if (!key.equals("_t")) {
                    throw new JSONPatchException("invalid delta index: " + key);
                }
            }
        }
        // all the lists are filled now, so the next step is to apply them

        // using a temporal list<Object> to hold the new data is the ideal, as JSONArray support
        // additions of new elements and reassignment of old, but not removals.
        List<Object> tempList = new ArrayList<>();
        // temp data list
        for (int i = 0; i < jsonArray.length(); i++) {
            tempList.add(jsonArray.get(i));
        }

        // first removals, then insertions, then patches

        // removals must be from greater index to lower index
        Collections.sort(removeList);
        Collections.reverse(removeList);

        for (int idx : removeList) {
            tempList.remove(idx);
        }

        // insertions go from the lower idx to the greatest. the ordering is over the
        // index in the new array, so over the 'to' value (snd) in the tuples
        insertList.sort(Comparator.comparing(tuple -> tuple.snd));
        // the first index is from, the second is to, the third is an indicator for the from ref (from index of array or of delta)
        for (Int3Tuple tuple : insertList) {
            if (tuple.isDeltaRef()) {
                tempList.add(tuple.snd, ((JSONArray) delta.get(String.valueOf(tuple.fst))).get(0));
            } else {// tuple.isArrayRef()
                tempList.add(tuple.snd, jsonArray.get(tuple.fst));
            }
        }

        // last is to apply the inner deltas
        for (int idx : patchList) {
            tempList.set(idx, JSONPatcher.patch(tempList.get(idx), delta.get(String.valueOf(idx))));
        }
        // and now to get our finished json array
        return new JSONArray(tempList);
    }

    private static boolean isMovement(JSONArray delta) throws JSONException {
        return delta.length() == 3 && (int) delta.get(2) == 3;
    }

    private static boolean isArrayPatch(JSONObject jsonObject) throws JSONException {
        return jsonObject.has("_t") && (jsonObject.get("_t")).equals("a");
    }

    private static boolean isObjectPatch(JSONObject jsonObject) throws JSONException {
        return !JSONPatcher.isArrayPatch(jsonObject);
    }
}

