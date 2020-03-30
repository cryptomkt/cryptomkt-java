package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties({"from_tx"})
public class OnOpenBook {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("data")
    private BookMarket market;
}
