package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse extends Response {

    @JsonProperty("data")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orders) {
        this.order = orders;
    }
}
