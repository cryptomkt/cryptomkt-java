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

    /**
     * Add a market order to the request
     *
     * @param market the market to make the order
     * @param side "buy" or "sell"
     * @param amount the amount to to buy or sell
     * @return the request
     */
    public MultiOrderRequest addMarketOrder(String market, String side, String amount) {
        Map<String, String> order = new HashMap<>();
        order.put("market", market);
        order.put("type", "market");
        order.put("side", side);
        order.put("amount", amount);
        orders.add(order);
        return this;
    }

    /**
     * Add a limit order to the request
     *
     *
     * @param market the market to make the order
     * @param side "buy" or "sell"
     * @param amount the amount to buy or sell
     * @param limit the limit price of the order
     * @return the request
     */
    public MultiOrderRequest addLimitOrder(String market, String side, String amount, String limit) {
        Map<String, String> order = new HashMap<>();
        order.put("market", market);
        order.put("type", "limit");
        order.put("side", side);
        order.put("amount", amount);
        order.put("price", limit);
        orders.add(order);
        return this;
    }

    /**
     * Add a stop limit order to the request
     *
     * @param market the market to make the order
     * @param side "buy" or "sell"
     * @param amount the amount to buy or sell
     * @param limit the limit price
     * @param stop the stop price
     * @return
     */
    public MultiOrderRequest addStopLimitOrder(String market, String side, String amount, String limit, String stop) {
        Map<String, String> order = new HashMap<>();
        order.put("market", market);
        order.put("type", "stop-limit");
        order.put("side", side);
        order.put("amount", amount);
        order.put("price", limit);
        order.put("stop", stop);
        orders.add(order);
        return this;
    }

    public List<Map<String, String>> getOrders() {
        return orders;
    }
}
