package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public class CandleMarket implements Serializable {

    @JsonProperty("lastBuyPrice")
    private Integer lastBuyPrice;

    @JsonProperty("lastSellPrice")
    private Integer lastSellPrice;

    @JsonAnySetter
    private Map<String,CandleSide> marketSides;

    public Integer getLastBuyPrice() {
        return lastBuyPrice;
    }

    public void setLastBuyPrice(Integer lastBuyPrice) {
        this.lastBuyPrice = lastBuyPrice;
    }

    public Integer getLastSellPrice() {
        return lastSellPrice;
    }

    public void setLastSellPrice(Integer lastSellPrice) {
        this.lastSellPrice = lastSellPrice;
    }

    public Map<String, CandleSide> getMarketSides() {
        return marketSides;
    }

    public void setMarketSides(Map<String, CandleSide> marketSides) {
        this.marketSides = marketSides;
    }

}
