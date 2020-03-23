package com.cryptomkt.api.entitySocket;


import java.util.Map;

public class OnTicker {

    private Map<String,Ticker> Tickers;

    public Map<String, Ticker> getTickers() {
        return Tickers;
    }

    public void setTickers(Map<String, Ticker> tickers) {
        Tickers = tickers;
    }

}
