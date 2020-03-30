package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"from_tx"})
public class OnOpenOrders {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("data")
    private List<OrderBook> openOrders;

    public List<OrderBook> getOpenOrders() {
        return openOrders;
    }
    public void setOpenOrders(List<OrderBook> openOrders) {
        this.openOrders = openOrders;
    }

}
