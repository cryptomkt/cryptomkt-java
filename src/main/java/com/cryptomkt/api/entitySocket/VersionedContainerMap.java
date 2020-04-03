package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

@JsonIgnoreProperties({"from_tx"})
public class VersionedContainerMap extends VersionableImpl {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("data")
    private Map<String, Container> data;

    public void patch(JSONObject jsonObject) {
        for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
            String key = it.next().toString();
            if (data.containsKey(key)) {
                try {
                    data.get(key).patch((JSONObject) jsonObject.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer getTx() {
        return tx;
    }

    public void setTx(Integer tx) {
        this.tx = tx;
    }

    public Map<String, Container> getData() {
        return data;
    }

    public void setData(Map<String, Container> data) {
        this.data = data;
    }
}