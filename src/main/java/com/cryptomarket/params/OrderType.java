package com.cryptomarket.params;

public enum OrderType {
    LIMIT("limit"),
    MARKET("market"),
    STOP_LIMIT("stopLimit"),
    STOP_MARKET("stopMarket"),
    TAKE_PROFIT_LIMIT("takeProfitLimit"),
    TAKE_PROFIT_MARKET("takeProfitMarket");

    private final String label;

    private OrderType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}