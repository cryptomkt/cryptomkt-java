package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.params.OrderRequest;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.Side;

import org.junit.Test;

public class TestRestClientTrading {
    CryptomktRestClient client = new CryptomktRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());


    @Test
    public void testGetBalance() {
        try {
            List<Balance> balances = client.getTradingBalance();
            balances.forEach(Checker.checkBalance);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetActiveOrders() {
        try {
            List<Order> orders = client.getActiveOrders(null); 
            orders.forEach(Checker.checkOrder);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCancelAllOrders() {
        try {
            List<Order> orders = client.cancelAllOrders(null);
            orders.forEach(Checker.checkOrder);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCancelOrdersOfSymbol() {
        try {
            client.cancelAllOrders(null);
            client.createOrder(new OrderRequest.Builder()
                .symbol("EOSBTC")
                .side(Side.SELL)
                .quantity("0.01")
                .orderType(OrderType.LIMIT)
                .price("1000")
                .build()
            );
            client.createOrder(new OrderRequest.Builder()
                .symbol("EOSETH")
                .side(Side.SELL)
                .quantity("0.01")
                .orderType(OrderType.LIMIT)
                .price("1000")
                .build()
            );
            List<Order> orders = client.cancelAllOrders("EOSBTC");
            System.out.println(orders.size());
            assertTrue(orders.size() == 1);
            client.cancelAllOrders(null);
            
            orders.forEach(Checker.checkOrder);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test 
    public void testOrderFlow() {
        try {
            String clientOrderId = String.format("%d", System.currentTimeMillis());
            Order order = client.createOrder(new OrderRequest.Builder()
                .clientOrderId(clientOrderId)
                .symbol("EOSETH")
                .side(Side.SELL)
                .quantity("0.01")
                .orderType(OrderType.LIMIT)
                .price("1000")
                .build()
            );
            Checker.checkOrder.accept(order);
            List<Order> orders = client.getActiveOrders("EOSETH");
            orders.forEach(Checker.checkOrder);
            Boolean present = false;
            for (Order ord: orders) {
                if (ord.getClientOrderId().equals(clientOrderId)) present = true;
            }
            if (!present) fail();
            order = client.cancelOrder(order.getClientOrderId());
            Checker.checkOrder.accept(order);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getTradingCommission() {
        try {
            Commission com = client.getTradingCommission("EOSETH");
            if (com.getTakeLiquidityRate() == null || com.getTakeLiquidityRate().equals("")) fail();
            if (com.getProvideLiquidityRate() == null || com.getProvideLiquidityRate().equals("")) fail();
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }
}