package com.cryptomarket.params;

public enum Period {
    _1_MINUTES("M1"),
    _3_MINUTES("M3"),
    _5_MINUTES("M5"),
    _15_MINUTES("M15"),
    _30_MINUTES("M30"),
    _1_HOURS("1H"),
    _4_HOURS("4H"),
    _1_DAYS("1D"),
    _7_DAYS("7D"),
    _1_MONTHS("1M");

    private final String label;

    private Period(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
