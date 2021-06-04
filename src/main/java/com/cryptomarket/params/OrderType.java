package com.cryptomarket.params;

public enum OrderType {
    LIMIT("limit"),
    MARKET("market"),
    STOPLIMIT("stopLimit"),
    STOPMARKET("stopMarket");

    private final String label;

    private OrderType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}