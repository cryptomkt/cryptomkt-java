package com.cryptomarket.params;

public enum TransferType {
    BANK_TO_EXCHANGE("bankToExchange"),
    EXCHANGE_TO_BANK("exchangeToBank");

    private final String label;

    private TransferType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
