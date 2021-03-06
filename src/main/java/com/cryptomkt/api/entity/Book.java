package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{
    private static final long serialVersionUID = 1;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("amount")
    private Double amount;

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

    @Override
    public String toString() {
        return "Book{" +
                "timestamp=" + timestamp +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}