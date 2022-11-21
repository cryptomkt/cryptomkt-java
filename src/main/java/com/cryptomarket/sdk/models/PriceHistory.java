package com.cryptomarket.sdk.models;

import java.util.List;

public class PriceHistory {
    
    private String currency;
    private List<HistoryPoint> history;
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public List<HistoryPoint> getHistory() {
        return history;
    }
    public void setHistory(List<HistoryPoint> history) {
        this.history = history;
    }
    @Override
    public String toString() {
        return "PriceHistory [currency=" + currency + ", history=" + history + "]";
    }
}
