package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.lang.model.type.NullType;
import java.io.Serializable;

public class OrderOwnExecuted extends OrderBook implements Serializable{

    @JsonProperty("dateTriggered")
    private NullType dateTriggered;

    public NullType getDateTriggered() { return dateTriggered; }

    public void setDateTriggered(NullType dateTriggered) { this.dateTriggered = dateTriggered; }

}
