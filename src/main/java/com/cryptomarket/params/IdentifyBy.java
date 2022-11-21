package com.cryptomarket.params;

public enum IdentifyBy {
    EMAIL("email"),
    USERNAME("username");

    private final String label;

    private IdentifyBy(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}