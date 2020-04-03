package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({"from_tx"})
public class BalanceData {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("data")
    private Map<String, Balance> data;

    public Integer getTx() {
        return tx;
    }

    public void setTx(Integer tx) {
        this.tx = tx;
    }

    public Map<String, Balance> getData() {
        return data;
    }

    public void setData(Map<String, Balance> data) {
        this.data = data;
    }
}
