package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TickersResponse extends Response{

    @JsonProperty("data")
    private List<Ticker> tickers;

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        this.tickers = tickers;
    }
}
