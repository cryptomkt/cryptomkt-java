package com.cryptomarket.sdk.models;

public class HistoryPoint {
    private String timestamp;
    private String open;
    private String close;
    private String min;
    private String max;
    
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getOpen() {
        return open;
    }
    public void setOpen(String open) {
        this.open = open;
    }
    public String getClose() {
        return close;
    }
    public void setClose(String close) {
        this.close = close;
    }
    public String getMin() {
        return min;
    }
    public void setMin(String min) {
        this.min = min;
    }
    public String getMax() {
        return max;
    }
    public void setMax(String max) {
        this.max = max;
    }
    @Override
    public String toString() {
        return "HistoryPoint [close=" + close + ", max=" + max + ", min=" + min + ", open=" + open + ", timestamp="
                + timestamp + "]";
    }
    
}
