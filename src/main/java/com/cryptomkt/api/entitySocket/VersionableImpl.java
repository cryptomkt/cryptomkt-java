package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;

@JsonIgnoreProperties({"from_tx"})
public class VersionableImpl implements Versionable {

    @JsonProperty("to_tx")
    private Integer tx;

    @Override
    public boolean isCorrectVersion(JSONObject jsonObject) {
        try {
            return tx == jsonObject.getInt("from_tx");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateVersion(JSONObject jsonObject) {
        try {
            this.tx = jsonObject.getInt("to_tx");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
