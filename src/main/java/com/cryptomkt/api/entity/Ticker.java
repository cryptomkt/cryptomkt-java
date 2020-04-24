package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Ticker implements Serializable{
    private static final long serialVersionUID = 1;

    @JsonProperty("high")
    private double high;

    @JsonProperty("volume")
    private double volume;

    @JsonProperty("low")
    private double low;

    @JsonProperty("ask")
    private double ask;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("bid")
    private double bid;

    @JsonProperty("last_price")
    private double lastPrice;

    @JsonProperty("market")
    private String market;

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "market='" + market + '\'' +
                ", timestamp=" + timestamp +
                ", ask=" + ask +
                ", high=" + high +
                ", bid=" + bid +
                ", low=" + low +
                ", lastPrice=" + lastPrice +
                ", volume=" + volume +
                '}';
    }
}