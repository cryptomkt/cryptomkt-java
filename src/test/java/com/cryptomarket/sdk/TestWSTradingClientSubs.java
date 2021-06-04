package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.OrderRequest;
import com.cryptomarket.params.Side;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.websocket.CryptomarketWSTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSTradingClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSTradingClientSubs {
    CryptomarketWSTradingClient wsClient;
    Boolean authenticated = false;
    Callback<Boolean> resultCallback = new Callback<Boolean>() {
        @Override
        public void resolve(Boolean result) {
            ;
        }

        @Override
        public void reject(Throwable exception) {
            fail();
        }
    };

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
    public void TestTimeFlow() {
        TimeFlow.reset();
        Boolean goodFLow;
        goodFLow = TimeFlow.checkNextTimestamp("2021-01-27T15:47:54.418Z");
        if (!goodFLow) {
            fail();
        }
        goodFLow = TimeFlow.checkNextTimestamp("2021-01-27T15:47:55.118Z");
        if (!goodFLow) {
            fail();
        }
        goodFLow = TimeFlow.checkNextTimestamp("2021-01-27T15:47:54.418Z");
        if (goodFLow) {
            fail();
        }
    }

    @Test
    public void testSubscribeToReports() {

        Callback<Report> callback = new Callback<Report>() {
			@Override
			public void resolve(Report result) {
                Checker.checkReport.accept(result);
			}
        };
        wsClient.subscribeToReports(callback, resultCallback);
        
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}

        String clientOrderId = String.format("%d", System.currentTimeMillis());
        wsClient.createOrder(
            new OrderRequest
            .Builder()
            .side(Side.SELL)
            .symbol("eoseth")
            .price("10000")
            .quantity("0.01")
            .clientOrderId(clientOrderId)
            .build(),
            null
        );

        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}

        wsClient.cancelOrder(clientOrderId, null);

        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}

        wsClient.close();
    }
}
