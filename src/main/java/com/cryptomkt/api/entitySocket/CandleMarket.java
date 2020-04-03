package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CandleMarket implements Serializable {

    @JsonProperty("1")
    private Map<String, List<Container>> buy;

    @JsonProperty("2")
    private Map<String, List<Container>> sell;

}
