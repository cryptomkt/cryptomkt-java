package com.cryptomarket.sdk.models;

public class TickerPrice {

    private String price;
    private String timestamp;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TickerPrice [price=" + price + ", timestamp=" + timestamp + "]";
    }
}
