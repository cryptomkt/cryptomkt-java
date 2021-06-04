package com.cryptomarket.params;

public enum By {
    TIMESTAMP("timestamp"),
    ID("id");

    private final String label;

    private By(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}