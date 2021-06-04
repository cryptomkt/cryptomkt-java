package com.cryptomarket.params;

/**
 * Sort direction of pagination, ASC for ascending, DESC for descending
 */
public enum Sort {
    /**
     * Descending
     */
    DESC("DESC"),

    /**
     * Ascending
     */
    ASC("ASC");

    public final String label;

    private Sort(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}


