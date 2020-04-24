package com.cryptomkt.api.entity.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CanceledOrder implements Serializable {
    private static final long serialVersionUID = 1;
    @JsonProperty("data")
    private Order data;

    @JsonProperty("order_id")
    private String orderId;

    @Override
    public String toString() {
        return "CanceledOrder{" +
                "data=" + data +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
