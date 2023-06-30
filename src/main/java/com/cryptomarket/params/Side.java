package com.cryptomarket.params;

import com.squareup.moshi.Json;

public enum Side {
    @Json(name = "buy")
    BUY("buy"),
    @Json(name = "sell")
    SELL("sell");

    private final String label;

    private Side(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
