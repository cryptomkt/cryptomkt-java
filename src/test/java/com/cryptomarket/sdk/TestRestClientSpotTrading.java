package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderBuilder;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Side;

import org.junit.Test;

public class TestRestClientSpotTrading {
  CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

  @Test
  public void testGetSpotTradingBalance() {
    try {
      List<Balance> balances = client.getSpotTradingBalances();
      balances.forEach(Checker.checkBalance);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetSpotTradingBalanceOfCurrency() {
    try {
      Balance balance = client.getSpotTradingBalanceOfCurrency("EOS");
      Checker.checkBalance.accept(balance);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetAllActiveSpotOrders() {
    try {
      List<Order> orders = client.getAllActiveSpotOrders(null);
      orders.forEach(Checker.checkOrder);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testCancelAllOrders() {
    try {
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
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testOrderFlow() {
    try {
      String clientOrderID = String.format("%d", System.currentTimeMillis());
      Order order = client.createSpotOrder(new ParamsBuilder()
          .clientOrderID(clientOrderID)
          .symbol("EOSETH")
          .side(Side.SELL)
          .quantity("0.01")
          .orderType(OrderType.LIMIT)
          .price("1000"));
      Checker.checkOrder.accept(order);
      order = client.getActiveSpotOrder(order.getClientOrderID());
      Checker.checkOrder.accept(order);
      order = client.cancelSpotOrder(order.getClientOrderID());
      Checker.checkOrder.accept(order);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetAllTradingCommission() {
    try {
      List<Commission> commissions = client.getAllTradingCommissions();
      commissions.forEach(Checker.checkCommission);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetTradingCommission() {
    try {
      Commission commission = client.getTradingCommission("EOSETH");
      Checker.checkCommission.accept(commission);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testCreateOrderList() {
    try {
      List<Order> orders = client.createSpotOrderList(ContingencyType.ONE_CANCEL_OTHER, Arrays.asList(
          new OrderBuilder()
              .symbol("EOSETH")
              .side(Side.SELL)
              .price("10000")
              .quantity("0.01"),
          new OrderBuilder()
              .symbol("EOSETH")
              .side(Side.SELL)
              .orderType(OrderType.STOP_LIMIT)
              .stopPrice("5000")
              .price("10000")
              .quantity("0.01")),
          null);
      System.out.println(orders);
      orders.forEach(Checker.checkOrder);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }
}