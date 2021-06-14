package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.Trade;

import org.junit.Test;

public class TestRestClientTradingHistory {
    CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

    @Test
    public void testGetOrderHistoryAndGetOrders() {
        try {
            List<Order> orders = client.getOrderHistory("EOSETH", null, null, null, null);
            orders.forEach(Checker.checkOrder);
            orders = client.getOrders(orders.get(0).getClientOrderId());
            orders.forEach(Checker.checkOrder);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetTradingHistory() {
        try {
            List<Trade> trades = client.getTradingHistory(null, null, null, null, null, null, null, null);
            trades.forEach(Checker.checkTrade);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testGetTradesByOrder() {
        try {
            List<Order> orders = client.getOrderHistory("EOSETH", null, null, null, null);
            Order order = null;
            for (Order temp: orders) {
                if (temp.getStatus().equals("filled")) {
                      order = temp;
                }
            };
            if (order != null) {
                List<Trade> trades = client.getTradesByOrder(order.getId());
                assertTrue(trades.size() != 0);
                trades.forEach(Checker.checkTrade);
            } else {
                fail();
            }
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }   
}
