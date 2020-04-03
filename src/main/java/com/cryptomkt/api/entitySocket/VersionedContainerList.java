package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.management.ListenerNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class VersionedContainerList extends VersionableImpl {

    @JsonProperty("data")
    private List<Container> data;

    public void patch(JSONObject jsonObject) {
        List<String> toRemove = new LinkedList<>();
        List<String> toAdd = new LinkedList<>();
        for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
            String key = (String) it.next();
            if (key.charAt(0) == '_' && key.charAt(1) != 't') {
                String index = key.substring(2);
                toRemove.add(index);
                try {
                    if (jsonObject.get(key) instanceof JSONArray && ((Integer) ((JSONArray) jsonObject.get(key)).get(2)) == 2) {
                        toAdd.add(index);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (key.charAt(0) != ' ' ) {
                toAdd.add(key);
            }
        }
    }
}
