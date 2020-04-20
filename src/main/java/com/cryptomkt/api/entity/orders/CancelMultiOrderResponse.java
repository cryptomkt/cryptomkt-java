package com.cryptomkt.api.entity.orders;

import com.cryptomkt.api.entity.Response;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelMultiOrderResponse extends Response {

    @JsonProperty("data")
    public CancelMultiOrderData data;

    public CancelMultiOrderData getData() {
        return data;
    }

    public void setData(CancelMultiOrderData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CancelMultiOrderResponse{" +
                super.toString() +
                ", data=" + data +
                '}';
    }
}
