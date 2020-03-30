package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"from_tx"})
public class OnHistoricalOrders {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("data")
    private List<OrderOwnExecuted> historicalOrders;

    public List<OrderOwnExecuted> getHistoricalOrders() {
        return historicalOrders;
    }

    public void setHistoricalOrders(List<OrderOwnExecuted> historicalOrders) {
        this.historicalOrders = historicalOrders;
    }

}
