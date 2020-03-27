package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties({"from_tx"})
public class OnCurrencies {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonAnySetter
    private Map<String, Currency> data;

    public Integer getTx() {
        return tx;
    }

    public void setTx(Integer tx) {
        this.tx = tx;
    }

    public Map<String, Currency> getData() {
        return data;
    }

    public void setData(Map<String, Currency> data) {
        this.data = data;
    }
}
