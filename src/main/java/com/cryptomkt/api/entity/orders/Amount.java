package com.cryptomkt.api.entity.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Amount implements Serializable {

    @JsonProperty("original")
    private Double original;

    @JsonProperty("remaining")
    private Double remaining;

    @JsonProperty("executed")
    private Double executed;

    public Double getOriginal() {
        return original;
    }

    public void setOriginal(Double original) {
        this.original = original;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

    public Double getExecuted() {
        return executed;
    }

    public void setExecuted(Double executed) {
        this.executed = executed;
    }
}
