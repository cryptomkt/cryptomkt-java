package com.cryptomarket.params;

public enum SubscriptionMode {
    
    UPDATES("updates"),
    BATCHES("batches");

    public final String label;

    private SubscriptionMode(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
