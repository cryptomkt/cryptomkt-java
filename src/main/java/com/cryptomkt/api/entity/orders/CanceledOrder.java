package com.cryptomkt.api.entity.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CanceledOrder {
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
