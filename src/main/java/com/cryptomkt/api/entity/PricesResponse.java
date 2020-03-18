package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PricesResponse extends Response {

    @JsonProperty("data")
    private Prices prices;

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }
}
