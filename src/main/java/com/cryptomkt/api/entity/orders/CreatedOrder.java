package com.cryptomkt.api.entity.orders;

import com.cryptomkt.api.entity.orders.Order;
import com.cryptomkt.api.entity.orders.OrderDescription;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CreatedOrder implements Serializable {
    private static final long serialVersionUID = 1;

    @JsonProperty("data")
    private Order data;

    @JsonProperty("original")
    private OrderDescription original;

    public Order getData() {
        return data;
    }

    public void setData(Order data) {
        this.data = data;
    }

    public OrderDescription getOriginal() {
        return original;
    }

    public void setOriginal(OrderDescription original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "CreatedOrder{" +
                "data=" + data +
                ", original=" + original +
                '}';
    }
}
