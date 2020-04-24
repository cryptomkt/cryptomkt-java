package com.cryptomkt.api.entity.orders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiOrderRequest implements Serializable {
    private static final long serialVersionUID = 1;

    private List<Map<String, String>> orders;

    public MultiOrderRequest() {
        orders = new ArrayList<>();
    }

    public MultiOrderRequest add(String market, String type, String side, String price, String amount) {
        Map<String, String> order = new HashMap<>();
        order.put("market", market);
        order.put("type", type);
        order.put("side", side);
        order.put("price", price);
        order.put("amount", amount);
        orders.add(order);
        return this;
    }

    public List<Map<String, String>> getOrders() {
        return orders;
    }
}
