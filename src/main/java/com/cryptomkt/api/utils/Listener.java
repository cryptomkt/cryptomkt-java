package com.cryptomkt.api.utils;

import org.json.JSONObject;

public interface Listener {
    void call(JSONObject jsonData);
}
