package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.lang.model.type.NullType;
import java.io.Serializable;

public class OrderOwnExecuted extends OrderBook {

    @JsonProperty("DateTriggered")
    private NullType DateTriggered;

    public NullType getDateTriggered() { return DateTriggered; }

    public void setDateTriggered(NullType dateTriggered) { DateTriggered = dateTriggered; }

}
