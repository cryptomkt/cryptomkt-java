package com.cryptomkt.api.entitySocket;

import java.util.Map;

public class OnOpenBook {
    private Map<String,BookMarket> market;

    public Map<String, BookMarket> getMarket() {
        return market;
    }

    public void setMarket(Map<String, BookMarket> market) {
        this.market = market;
    }

}
