package com.cryptomkt.api.entity.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Filler implements Serializable {
    private static final long serialVersionUID = 1;

    @JsonProperty("price")
    private String price;

    @JsonProperty("fee")
    private String fee;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("date")
    private Date date;


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Filler{" +
                "price='" + price + '\'' +
                ", fee='" + fee + '\'' +
                ", amount='" + amount + '\'' +
                ", date=" + date +
                '}';
    }
}
