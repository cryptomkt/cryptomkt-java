package com.cryptomarket.params;

public enum TransferBy {
    EMAIL("email"),
    USERNAME("username");

    private final String label;

    private TransferBy(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}