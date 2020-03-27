package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class BookMarket implements Serializable {

    @JsonProperty("sell")
    private List<OrderBook> sell;

    @JsonProperty("buy")
    private List<OrderBook> buy;

    public List<OrderBook> getSell() {
        return sell;
    }

    public void setSell(List<OrderBook> sell) {
        this.sell = sell;
    }

    public List<OrderBook> getBuy() {
        return buy;
    }

    public void setBuy(List<OrderBook> buy) {
        this.buy = buy;
    }

}
