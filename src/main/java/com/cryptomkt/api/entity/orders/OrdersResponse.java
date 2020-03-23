package com.cryptomkt.api.entity.orders;

import com.cryptomkt.api.entity.Response;
import com.cryptomkt.api.entity.orders.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrdersResponse extends Response {

    @JsonProperty("data")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
