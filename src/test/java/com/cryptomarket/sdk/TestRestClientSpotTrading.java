package com.cryptomarket.sdk;

import java.util.Arrays;
import java.util.List;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.rest.CryptomarketRestClient;
import com.cryptomarket.sdk.rest.CryptomarketRestClientImpl;
import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderBuilder;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.TimeInForce;

import org.junit.Test;

public class TestRestClientSpotTrading {
  CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

  @Test
  public void testGetSpotTradingBalance() throws CryptomarketSDKException {
    List<Balance> balances = client.getSpotTradingBalances();
    balances.forEach(Checker.checkBalance);
  }

  @Test
  public void testGetSpotTradingBalanceOfCurrency() throws CryptomarketSDKException {
    Balance balance = client.getSpotTradingBalanceByCurrency("EOS");
    Checker.checkBalance.accept(balance);
  }

  @Test
  public void testGetAllActiveSpotOrders() throws CryptomarketSDKException {
    List<Order> orders = client.getAllActiveSpotOrders(null);
    orders.forEach(Checker.checkOrder);
  }

  @Test
  public void testCancelAllOrders() throws CryptomarketSDKException {
    client.createSpotOrder(new ParamsBuilder()
        .symbol("EOSBTC")
        .side(Side.SELL)
        .quantity("0.01")
        .orderType(OrderType.LIMIT)
        .price("1000"));
    client.createSpotOrder(new ParamsBuilder()
        .symbol("EOSETH")
        .side(Side.SELL)
        .quantity("0.01")
        .orderType(OrderType.LIMIT)
        .price("1000"));
    List<Order> orders = client.cancelAllSpotOrders();
    orders.forEach(Checker.checkOrder);
  }

  @Test
  public void testOrderFlow() throws CryptomarketSDKException {
    String clientOrderId = String.format("%d", System.currentTimeMillis());
    Order order = client.createSpotOrder(new OrderBuilder()
        .clientOrderId(clientOrderId)
        .symbol("EOSETH")
        .side(Side.SELL)
        .quantity("0.01")
        .timeInForce(TimeInForce.DAY)
        .orderType(OrderType.LIMIT)
        .price("1000"));
    Checker.checkOrder.accept(order);
    order = client.getActiveSpotOrder(order.getClientOrderId());
    Checker.checkOrder.accept(order);
    order = client.cancelSpotOrder(order.getClientOrderId());
    Checker.checkOrder.accept(order);
  }

  @Test
  public void testGetAllTradingCommission() throws CryptomarketSDKException {
    List<Commission> commissions = client.getAllTradingCommissions();
    commissions.forEach(Checker.checkCommission);
  }

  @Test
  public void testGetTradingCommission() throws CryptomarketSDKException {
    Commission commission = client.getTradingCommission("EOSETH");
    Checker.checkCommission.accept(commission);
  }

  @Test
  public void testCreateOrderList() throws CryptomarketSDKException {
    List<Order> orders = client.createSpotOrderList(ContingencyType.ALL_OR_NONE, Arrays.asList(
        new OrderBuilder()
            .symbol("EOSETH")
            .side(Side.SELL)
            .timeInForce(TimeInForce.FOK)
            .quantity("0.02")
            .price("10000"),
        new OrderBuilder()
            .symbol("EOSUSDT")
            .side(Side.SELL)
            .timeInForce(TimeInForce.FOK)
            .stopPrice("5000")
            .quantity("0.01")
            .price("10000")),
        null);
    orders.forEach(Checker.checkOrder);
  }
}
