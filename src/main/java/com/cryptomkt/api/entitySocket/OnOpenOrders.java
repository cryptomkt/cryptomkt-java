package com.cryptomkt.api.entitySocket;

import java.util.List;

public class OnOpenOrders {

    private List<OrderBook> openOrders;

    public List<OrderBook> getOpenOrders() {
        return openOrders;
    }
    public void setOpenOrders(List<OrderBook> openOrders) {
        this.openOrders = openOrders;
    }

}
