package com.cryptomkt.api.entity.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OrderDescription implements Serializable {

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("market")
    private  String market;

    @JsonProperty("price")
    private String price;

    @JsonProperty("side")
    private String side;

    @JsonProperty("type")
    private String type;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OrderDescription{" +
                "amount='" + amount + '\'' +
                ", market='" + market + '\'' +
                ", price='" + price + '\'' +
                ", side='" + side + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
