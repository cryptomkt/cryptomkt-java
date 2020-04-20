package com.cryptomkt.api.entity.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NotCanceledOrder implements Serializable {

    @JsonProperty("message")
    private String message;

    @JsonProperty("order_id")
    private String orderId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "NotCanceledOrder{" +
                "message='" + message + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
