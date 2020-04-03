package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"from_tx"})
public class HistoricalBookData {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("data")
    private List<OrderBookExecuted> historicalBook;

}
