package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;

import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.Trade;

import org.junit.Test;

public class TestRestClientSpotTradingHistory {
    CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

    @Test
    public void testGetOrderHistoryAndGetOrders() {
        try {
            List<Order> orders = client.getSpotOrderHistory(new ParamsBuilder()
            .symbol("EOSETH"));
            orders.forEach(Checker.checkOrder);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetTradingHistory() {
        try {
            List<Trade> trades = client.getSpotTradesHistory(new ParamsBuilder());
            trades.forEach(Checker.checkTrade);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }
}
