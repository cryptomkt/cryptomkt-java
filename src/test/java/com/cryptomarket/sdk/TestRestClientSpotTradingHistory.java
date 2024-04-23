package com.cryptomarket.sdk;

import java.util.List;

import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Sort;
import com.cryptomarket.params.SortBy;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.Trade;
import com.cryptomarket.sdk.rest.CryptomarketRestClient;
import com.cryptomarket.sdk.rest.CryptomarketRestClientImpl;

import org.junit.Test;

public class TestRestClientSpotTradingHistory {
  CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

  @Test
  public void testGetOrderHistoryAndGetOrders() throws CryptomarketSDKException {
    List<Order> orders = client.getSpotOrderHistory(new ParamsBuilder()
        .symbol("EOSETH"));
    orders.forEach(Checker.checkOrder);
  }

  @Test
  public void testGetOrderHistoryAndGetOrdersWithParams() throws CryptomarketSDKException {
    List<Order> orders = client.getSpotOrderHistory(new ParamsBuilder()
        .by(SortBy.TIMESTAMP)
        .sort(Sort.DESC)
        .limit(1000)
        .offset(0)
        .from("1610701510"));
    orders.forEach(Checker.checkOrder);
  }

  @Test
  public void testGetTradingHistory() throws CryptomarketSDKException {
    List<Trade> trades = client.getSpotTradesHistory(new ParamsBuilder().limit(12));
    trades.forEach(Checker.checkTrade);
  }


  @Test
  public void testGetTradingHistoryWithParamsWithParams() throws CryptomarketSDKException {
    List<Trade> trades = client.getSpotTradesHistory(new ParamsBuilder()
        .by(SortBy.TIMESTAMP)
        .sort(Sort.DESC)
        .limit(1000)
        .offset(0)
        .from("1610701510"));
    trades.forEach(Checker.checkTrade);
  }
}
