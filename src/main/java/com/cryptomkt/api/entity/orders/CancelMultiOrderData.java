package com.cryptomkt.api.entity.orders;

import com.cryptomkt.api.entity.Response;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class CancelMultiOrderData implements Serializable {
    private static final long serialVersionUID = 1;

    @JsonProperty("canceled")
    private List<CanceledOrder> canceledOrders;

    @JsonProperty("not_canceled")
    private List<NotCanceledOrder> notCanceledOrders;

    public List<CanceledOrder> getCanceledOrders() {
        return canceledOrders;
    }

    public void setCanceledOrders(List<CanceledOrder> canceledOrders) {
        this.canceledOrders = canceledOrders;
    }

    public List<NotCanceledOrder> getNotCanceledOrders() {
        return notCanceledOrders;
    }

    public void setNotCanceledOrders(List<NotCanceledOrder> notCanceledOrders) {
        this.notCanceledOrders = notCanceledOrders;
    }

    @Override
    public String toString() {
        return "CancelMultiOrderData{" +
                "canceledOrders=" + canceledOrders +
                ", notCanceledOrders=" + notCanceledOrders +
                '}';
    }
}
