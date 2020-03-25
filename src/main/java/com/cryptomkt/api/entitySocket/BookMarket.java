package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class BookMarket implements Serializable {

    @JsonProperty("sell")
    private List<OrderBookActive> sell;

    @JsonProperty("buy")
    private List<OrderBookActive> buy;

    public List<OrderBookActive> getSell() {
        return sell;
    }

    public void setSell(List<OrderBookActive> sell) {
        this.sell = sell;
    }

    public List<OrderBookActive> getBuy() {
        return buy;
    }

    public void setBuy(List<OrderBookActive> buy) {
        this.buy = buy;
    }

}
