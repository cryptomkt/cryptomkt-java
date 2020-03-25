package com.cryptomkt.api.entitySocket;

import java.util.List;

public class OnOpenOrders {

    private List<OrderOwnActive> openOrders;

    public List<OrderOwnActive> getOpenOrders() {
        return openOrders;
    }
    public void setOpenOrders(List<OrderOwnActive> openOrders) {
        this.openOrders = openOrders;
    }

}
