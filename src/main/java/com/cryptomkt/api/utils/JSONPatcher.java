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
        for (Iterator<String> it = delta.keys(); it.hasNext(); ) {
            String key = it.next();
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
            return dmp.patch_apply(patches, (String) object);
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

    // only works when the json array is inside a json object, so we keep the object, and change the pointer
    // to the array to point to a new array.
    // if this is not the case, and the array is in no structure, then we cannot make removals in the
    // json array and thus is no use for us.
    private static JSONArray patchArray(JSONArray jsonArray, JSONObject delta) throws JSONException, JSONPatchException {
        List<Integer> removeList = new ArrayList<>();
        List<IntPair> moveList = new ArrayList<>();
        List<Integer> patchList = new ArrayList<>();
        List<Integer> valuePatchList = new ArrayList<>();

        for (Iterator<String> it = delta.keys(); it.hasNext(); ) {
            String key = it.next();

            if (key.charAt(0) == '_' && key.charAt(1) != 't') { // is old index
                int index = Integer.parseInt(key.substring(1));
                Object innerDelta = delta.get(key);
                if (innerDelta instanceof JSONArray) {
                    if (JSONPatcher.isRemoval((JSONArray) innerDelta)) { // is a removal in array
                        removeList.add(index);
                    } else if (JSONPatcher.isMovement((JSONArray) innerDelta)) { // is a movement in array
                        moveList.add(new IntPair(index, (Integer) ((JSONArray) innerDelta).get(1)));
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
                if (innerDelta instanceof JSONObject) { // is an inner delta to apply over a json object in the array
                    patchList.add(index);
                } else if (innerDelta instanceof JSONArray) { // is a delta to apply over a json value in the array
                    valuePatchList.add(index);
                } else { // if we are here, then it wasn't a value change, so its an error
                    throw new JSONPatchException("new index delta is not an inner delta or a delta of a value");
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

        // first removals, then movement, then patchs of inner objects and arrays and patchs of values

        // removals must be from greater index to lower index
        Collections.sort(removeList);
        Collections.reverse(removeList);

        for (int i = 0; i < jsonArray.length(); i++) {
            tempList.add(jsonArray.get(i));
        }
        for (int idx : removeList) {
            tempList.remove(idx);
        }

        // now to add the elements that were moved, from the lower idx to the greatest.
        moveList.sort(Comparator.comparing(pair -> pair.snd));
        // the first index is from, the second is to.
        for (IntPair pair : moveList) {
            tempList.add(pair.snd, jsonArray.get(pair.fst));
        }

        for (int idx : valuePatchList) {
            if (tempList.size() <= idx) {
                tempList.add(idx, JSONPatcher.patch(JSONObject.NULL, delta.get(String.valueOf(idx))));
            } else {
                tempList.add(idx, JSONPatcher.patch(tempList.get(idx), delta.get(String.valueOf(idx))));
            }
        }

        // last is to apply the inner deltas
        for (int idx : patchList) {
            JSONPatcher.patch(tempList.get(idx), delta.get(String.valueOf(idx)));
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

