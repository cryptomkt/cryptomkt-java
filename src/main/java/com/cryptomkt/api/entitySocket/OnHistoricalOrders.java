package com.cryptomkt.api.entitySocket;

import java.util.List;

public class OnHistoricalOrders {

    private List<OrderOwnExecuted> historicalOrders;

    public List<OrderOwnExecuted> getHistoricalOrders() {
        return historicalOrders;
    }

    public void setHistoricalOrders(List<OrderOwnExecuted> historicalOrders) {
        this.historicalOrders = historicalOrders;
    }

}
