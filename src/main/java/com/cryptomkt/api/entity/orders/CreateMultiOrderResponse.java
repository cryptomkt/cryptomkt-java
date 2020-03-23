package com.cryptomkt.api.entity.orders;

import com.cryptomkt.api.entity.Response;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateMultiOrderResponse extends Response {

    @JsonProperty("data")
    private CreateMultiOrderData data;

    public CreateMultiOrderData getData() {
        return data;
    }

    public void setData(CreateMultiOrderData data) {
        this.data = data;
    }
}
