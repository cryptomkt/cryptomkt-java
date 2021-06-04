package com.cryptomarket.params;

public enum Side {
    BUY("buy"),
    SELL("sell");

    private final String label;

    private Side(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
