package com.cryptomkt.api.entity.orders;

import com.cryptomkt.api.entity.Response;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateMultiOrderData {

    @JsonProperty("created")
    private List<CreatedOrder> created;

    @JsonProperty("not_created")
    private List<NotCreatedOrder> notCreated;

    public List<CreatedOrder> getCreated() {
        return created;
    }

    public void setCreated(List<CreatedOrder> created) {
        this.created = created;
    }

    public List<NotCreatedOrder> getNotCreated() {
        return notCreated;
    }

    public void setNotCreated(List<NotCreatedOrder> notCreated) {
        this.notCreated = notCreated;
    }

    @Override
    public String toString() {
        return "CreateMultiOrderData{" +
                "created=" + created +
                ", notCreated=" + notCreated +
                '}';
    }
}
