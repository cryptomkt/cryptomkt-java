package com.cryptomkt.api.entity.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NotCreatedOrder implements Serializable {
    private static final long serialVersionUID = 1;

    @JsonProperty("message")
    private String message;

    @JsonProperty("original")
    private OrderDescription order;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderDescription getOrder() {
        return order;
    }

    public void setOrder(OrderDescription order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "NotCreatedOrder{" +
                "message='" + message + '\'' +
                ", order=" + order +
                '}';
    }
}
