package com.cryptomarket.sdk.models;

public class Price {
    private String currency;
    private String price;
    private String timestamp;
    
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
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
        return "Price [currency=" + currency + ", price=" + price + ", timestamp=" + timestamp + "]";
    }
}