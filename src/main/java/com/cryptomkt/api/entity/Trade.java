package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable{

    @JsonProperty("market_taker")
    private String marketTaker;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("market")
    private String market;

    public String getMarketTaker() {
        return marketTaker;
    }

    public void setMarketTaker(String marketTaker) {
        this.marketTaker = marketTaker;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "market='" + market + '\'' +
                ", timestamp=" + timestamp +
                ", marketTaker='" + marketTaker + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}