package com.cryptomkt.api.entitySocket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CandlesData {

    private Map<String, CandleData> data;

    public CandlesData() {
        data = new HashMap<>();
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    public void put(String key, CandleData data) {
        this.data.put(key, data);
    }

    public CandleData get(String key) {
        return this.data.get(key);
    }

    public void remove(String key) {
        data.remove(key);
    }
}
