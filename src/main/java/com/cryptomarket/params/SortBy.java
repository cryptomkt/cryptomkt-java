package com.cryptomarket.params;

public enum SortBy {
    TIMESTAMP("timestamp"),
    ID("id"),
    DATETIME("created_at");

    private final String label;

    private SortBy(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}