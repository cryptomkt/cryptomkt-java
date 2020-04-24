package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Rate implements Serializable {
    private static final long serialVersionUID = 1;

    @JsonProperty("market_maker")
    private String marketMaker;

    @JsonProperty("market_taker")
    private String marketTaker;


    public String getMarketMaker() {
        return marketMaker;
    }

    public void setMarketMaker(String marketMaker) {
        this.marketMaker = marketMaker;
    }

    public String getMarketTaker() {
        return marketTaker;
    }

    public void setMarketTaker(String marketTaker) {
        this.marketTaker = marketTaker;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "marketMaker='" + marketMaker + '\'' +
                ", marketTaker='" + marketTaker + '\'' +
                '}';
    }
}
