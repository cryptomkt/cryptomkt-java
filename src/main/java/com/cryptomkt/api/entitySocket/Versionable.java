package com.cryptomkt.api.entitySocket;

import org.json.JSONObject;

public interface Versionable {
    public boolean isCorrectVersion(JSONObject jsonObject);

    public void updateVersion(JSONObject jsonObject);
}
