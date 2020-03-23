package com.cryptomkt.api.entity.orders;

import com.cryptomkt.api.entity.Response;
import com.cryptomkt.api.entity.orders.Order;
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
