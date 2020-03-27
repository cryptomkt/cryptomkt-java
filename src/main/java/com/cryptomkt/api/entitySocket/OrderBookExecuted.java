package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.lang.model.type.NullType;
import java.io.Serializable;

public class OrderBookExecuted implements Serializable {

    @JsonProperty("executed_price")
    private String executed_price;

    @JsonProperty("executed_amount")
    private String executed_amount;

    @JsonProperty("executed_date")
    private Long executed_date;

    public String getExecuted_price() {
        return executed_price;
    }

    public void setExecuted_price(String executed_price) {
        this.executed_price = executed_price;
    }

    public String getExecuted_amount() {
        return executed_amount;
    }

    public void setExecuted_amount(String executed_amount) {
        this.executed_amount = executed_amount;
    }

    public Long getExecuted_date() {
        return executed_date;
    }

    public void setExecuted_date(Long executed_date) {
        this.executed_date = executed_date;
    }


}
