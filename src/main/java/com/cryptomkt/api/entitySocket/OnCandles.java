package com.cryptomkt.api.entitySocket;

import java.util.Map;

public class OnCandles {
    
    private Map<String,CandleMarket> market;

    public Map<String, CandleMarket> getMarket() {
        return market;
    }

    public void setMarket(Map<String, CandleMarket> market) {
        this.market = market;
    }
}
