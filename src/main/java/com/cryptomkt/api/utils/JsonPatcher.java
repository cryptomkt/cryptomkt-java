package com.cryptomkt.api.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class JsonPatcher {

    public static void patch(Map<String, String> data, JSONObject jsonObject) {
        for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
            String key = (String) it.next();
            try {
                JSONArray jsonArray = (JSONArray) jsonObject.get(key);
                if (jsonArray.length() == 1) {
                    data.replace(key, (String) jsonArray.get(0));
                } else if (jsonArray.length() == 2) {
                    data.replace(key, (String) jsonArray.get(1));
                } else if (jsonArray.length() == 3) {
                    if ((Integer) jsonArray.get(2) == 0) {
                        data.remove(key);
                    } else if ((Integer) jsonArray.get(2) == 2) {
                        // #TODO text diff
                        data.remove(key);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
