package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;


public class VersionedContainer  extends VersionableImpl{

    @JsonProperty("data")
    private Container data;

    public void patch(JSONObject jsonObject) {
        data.patch(jsonObject);
    }

}
