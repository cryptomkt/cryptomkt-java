package com.cryptomarket.sdk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderBuilder;
import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.TimeInForce;
import com.cryptomarket.sdk.Helpers.FailChecker;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClientImpl;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;

public class TestWSSpotTradingClient {

  CryptomarketWSSpotTradingClient wsClient;
  Boolean authenticated = false;

  @Before
  public void before() throws IOException, InterruptedException, ExecutionException {
    wsClient = new CryptomarketWSSpotTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret(), 60_000);
    var ft = new FutureTask<Object>(() -> {
    }, new Object());
    wsClient.onConnect(ft);
    wsClient.connect();
    ft.get();
  }

  @After
  public void after() {
    wsClient.close();
  }

  @Test
  public void testGetSpotTradingBalances() {
    FailChecker failChecker = new FailChecker();
    wsClient.getSpotTradingBalances(
        Helpers.listAndExceptionChecker(failChecker, Checker.checkBalance));
    Helpers.sleep(3);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSpotTradingBalance() {
    FailChecker failChecker = new FailChecker();
    wsClient.getSpotTradingBalanceOfCurrency("EOS",
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBalance));
    Helpers.sleep(3);
    if (failChecker.failed()) {
      fail(failChecker.getErrMsg().get());
    }
    assertFalse(failChecker.failed());
  }

  @Test
  public void testOrderFlow() {
    String oldClientOrderId = String.format("%d", System.currentTimeMillis()) + "11";
    String newClientOrderId = String.format("%d", System.currentTimeMillis()) + "22";

    // create
    wsClient.createSpotOrder(
        new ParamsBuilder()
            .side(Side.SELL)
            .symbol("EOSETH")
            .price("10000")
            .quantity("0.01")
            .clientOrderId(oldClientOrderId),
        (report, exception) -> {
          Checker.checkReport.accept(report);
        });

    Helpers.sleep(3);
    // check
    wsClient.getAllActiveOrders(
        (reportList, exception) -> {
          if (exception != null) {
            fail();
          }
          Boolean present = false;
          for (Report order : reportList) {
            if (order.getClientOrderId().equals(oldClientOrderId))
              present = true;
          }
          if (!present)
            fail("could not find");
        });

    Helpers.sleep(3);

    // replace
    wsClient.replaceSpotOrder(
        oldClientOrderId,
        newClientOrderId,
        "0.02",
        "2000",
        null,
        null,
        (report, exception) -> {
          if (exception != null) {
            fail();
          }
          if (!report.getOriginalClientOrderId().equals(oldClientOrderId)) {
            fail();
          }
        });

    Helpers.sleep(3);

    // cancel
    wsClient.cancelSpotOrder(
        newClientOrderId,
        (report, exception) -> {
          if (exception != null) {
            fail();
          }
          if (!report.getStatus().equals(OrderStatus.CANCELED))
            fail();
          if (report.getClientOrderId().equals(oldClientOrderId))
            fail();
        });

    Helpers.sleep(3);
  }

  @Test
  public void testGetActiveSpotOrdersAndCancelAllSpotOrders() {
    wsClient.cancelAllSpotOrders(null);
    for (int i = 0; i < 5; i++) {
      wsClient.createSpotOrder(
          new ParamsBuilder()
              .symbol("EOSETH")
              .side(Side.SELL)
              .price("1000")
              .quantity("0.01"),
          null);
    }
    BiConsumer<List<Report>, CryptomarketSDKException> checkReportListSizeAndValidity = (reportList, exception) -> {
      if (exception != null) {
        fail();
      }
      if (reportList.size() != 5) {
        fail();
      }
      reportList.forEach(Checker.checkReport);
    };

    Helpers.sleep(3);
    wsClient.getAllActiveOrders(checkReportListSizeAndValidity);

    Helpers.sleep(3);
    wsClient.cancelAllSpotOrders(checkReportListSizeAndValidity);

    Helpers.sleep(3);
  }

  @Test
  public void testGetSpotTradingCommissions() {
    wsClient.getSpotCommissions((result, exception) -> {
      result.forEach(Checker.checkCommission);
    });
    Helpers.sleep(3);
  }

  @Test
  public void testGetSpotTradingCommission() {
    wsClient.getSpotCommissionOfSymbol("EOSETH", (result, exception) -> {
      Checker.checkCommission.accept(result);
    });
    Helpers.sleep(3);
  }

  @Test
  public void testCreateOrderList() {
    String orderListId = String.format("%d", System.currentTimeMillis());
    String secondClientOrderId = String.format("%d", System.currentTimeMillis()) + "2";
    Side side = Side.SELL;
    String quantity = "0.01";
    String price = "10000";
    var failChecker = new FailChecker();
    wsClient.createSpotOrderList(
        ContingencyType.ALL_OR_NONE,
        Arrays.asList(
            new OrderBuilder()
                .clientOrderId(orderListId)
                .symbol("EOSETH")
                .side(side)
                .timeInForce(TimeInForce.FOK)
                .quantity(quantity)
                .price(price),
            new OrderBuilder()
                .clientOrderId(secondClientOrderId)
                .symbol("EOSBTC")
                .side(side)
                .timeInForce(TimeInForce.FOK)
                .quantity(quantity)
                .price(price)),
        orderListId,
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkReport));

    Helpers.sleep(12);
    if (failChecker.failed()) {
      fail(failChecker.getErrMsg().get());
    }
    assertFalse(failChecker.failed());
  }
}
