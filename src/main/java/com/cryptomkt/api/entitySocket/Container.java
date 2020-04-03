package com.cryptomkt.api.entitySocket;

import com.cryptomkt.api.utils.JsonPatcher;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.json.JSONObject;

import java.util.Map;

public class Container {

    @JsonAnySetter
    protected Map<String, String> data;

    public void patch(JSONObject jsonObject) {
        JsonPatcher.patch(this.data, jsonObject);
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

}
