package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties({"from_tx"})
public class OnBoard {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("data")
    private Map<String, Ticker> data;
}
