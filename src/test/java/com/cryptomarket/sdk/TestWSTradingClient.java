package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.OrderRequest;
import com.cryptomarket.params.Side;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.websocket.CryptomarketWSTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSTradingClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSTradingClient {

    CryptomarketWSTradingClient wsClient;
    Boolean authenticated = false;

    @Before
    public void before() {
        try {
            wsClient = new CryptomarketWSTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
            wsClient.connect();
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @After
    public void after() {
        wsClient.close();
    }

    @Test
    public void TestCancellAllOrders() {
        for (int i=0; i< 10; i++) {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            String timestamp = new Timestamp(System.currentTimeMillis()).toString();
            wsClient.createOrder(new OrderRequest
                .Builder()
                .clientOrderId(timestamp)
                .symbol("EOSETH")
                .price("1000")
                .quantity("0.01")
                .side(Side.SELL)
                .build()
                , null);
        }
        wsClient.getActiveOrders(new Callback<List<Report>>() {
            @Override
            public void resolve(List<Report> result) {
                result.forEach((Report order) -> wsClient.cancelOrder(order.getClientOrderId(), null));
            }
        });
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        wsClient.getActiveOrders(new Callback<List<Report>>() {
            @Override
            public void resolve(List<Report> result) {assertTrue(result.size() == 0);}
        });
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
    }

    @Test
    public void testCreateOrder() {
        wsClient.createOrder(
            String.format("%d", System.currentTimeMillis()), 
            "EOSETH", 
            Side.SELL, 
            "0.1", 
            null, 
            "10000", 
            null, 
            null,
            null,
            null,
            null,
            new Callback<Report>() {
                @Override
                public void resolve(Report result) {Checker.checkReport.accept(result);}

                @Override
                public void reject(Throwable exception) {fail();}
            }
        );
        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
    }

    @Test
    public void testOrderFlow() {
        String oldClientOrderId = String.format("%d", System.currentTimeMillis()) + "11";
        String newClientOrderId = String.format("%d", System.currentTimeMillis()) + "22";

        // create
        wsClient.createOrder(
            new OrderRequest
            .Builder()
            .side(Side.SELL)
            .symbol("EOSETH")
            .price("10000")
            .quantity("0.01")
            .clientOrderId(oldClientOrderId)
            .build(),
            new Callback<Report>() {
                @Override
                public void resolve(Report result) {Checker.checkReport.accept(result);}
    
                @Override
                public void reject(Throwable exception) {fail();}
            }
        );
            
        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
        // check
        wsClient.getActiveOrders(
             new Callback<List<Report>>() {
            @Override
            public void resolve(List<Report> result) {
                Boolean present = false;
                for(Report order: result) {
                    if (order.getClientOrderId().equals(oldClientOrderId)) present = true;
                }
                if (!present) fail("could not find");
            }

            @Override
            public void reject(Throwable exception) {fail();}
        });

        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}

        //replace
        wsClient.replaceOrder(
            oldClientOrderId,
            newClientOrderId, 
            "0.02",
            "2000",
            null,
            new Callback<Report>() {
                @Override
                public void resolve(Report result) {
                    if (!result.getOriginalRequestClientOrderId().equals(oldClientOrderId)) fail();
                }

                @Override
                public void reject(Throwable exception) {exception.printStackTrace();fail("could not replace");}
            }
        );


        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}

        // cancel
        wsClient.cancelOrder(
            newClientOrderId,
            new Callback<Report>() {
            @Override
            public void resolve(Report result) {
                if (!result.getStatus().equals("canceled")) fail();
                if (result.getClientOrderId().equals(oldClientOrderId)) fail();
            }

            @Override
            public void reject(Throwable exception) {exception.printStackTrace();fail("could not cancel");}
        });

        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
    }

    @Test
    public void testGetTradingBalance() {
        wsClient.getTradingBalance(
             new Callback<List<Balance>>() {
            @Override
            public void resolve(List<Balance> result) {result.forEach(Checker.checkBalance);}

            @Override
            public void reject(Throwable exception) {fail();}
        });
        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
    }
}
