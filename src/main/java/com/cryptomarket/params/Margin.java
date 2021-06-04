package com.cryptomarket.params;

public enum Margin {
    INCLUDE("include"),
    ONLY("only"),
    IGNORE("ignore");

    private final String label;

    private Margin(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
