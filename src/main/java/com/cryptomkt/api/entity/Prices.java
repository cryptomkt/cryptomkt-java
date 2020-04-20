package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Prices {

    @JsonProperty("ask")
    private List<Candle> ask;

    @JsonProperty("bid")
    private List<Candle> bid;

    public List<Candle> getAsk() {
        return ask;
    }

    public void setAsk(List<Candle> ask) {
        this.ask = ask;
    }

    public List<Candle> getBid() {
        return bid;
    }

    public void setBid(List<Candle> bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "ask=" + ask +
                ", bid=" + bid +
                '}';
    }
}
