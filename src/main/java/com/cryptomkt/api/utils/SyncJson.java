package com.cryptomkt.api.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class SyncJson {
    private String data;

    public SyncJson() {
        data = "";
    }
    public synchronized JSONObject getData() {
        try {
            return new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public synchronized void setData(String data) {
        this.data = data;
    }
}
