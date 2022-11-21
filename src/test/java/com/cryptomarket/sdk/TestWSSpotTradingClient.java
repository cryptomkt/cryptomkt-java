package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderBuilder;
import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Side;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSSpotTradingClient {

  CryptomarketWSSpotTradingClient wsClient;
  Boolean authenticated = false;

  @Before
  public void before() {
    try {
      wsClient = new CryptomarketWSSpotTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret(), 10);
      wsClient.connect();
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @After
  public void after() {
    wsClient.close();
  }

  @Test
  public void testGetSpotTradingBalances() {
    wsClient.getSpotTradingBalances((result, exception) -> {
      if (exception != null) {
        fail();
      }
      System.out.println(result);
      result.forEach(Checker.checkBalance);
    });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testSpotTradingBalance() {
    wsClient.getSpotTradingBalanceOfCurrency("EOS", (result, exception) -> {
      if (exception != null) {
        System.out.println(exception);
        fail();
      }
      Checker.checkBalance.accept(result);
    });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testOrderFlow() {
    String oldClientOrderID = String.format("%d", System.currentTimeMillis()) + "11";
    String newClientOrderID = String.format("%d", System.currentTimeMillis()) + "22";

    // create
    wsClient.createSpotOrder(
        new ParamsBuilder()
            .side(Side.SELL)
            .symbol("EOSETH")
            .price("10000")
            .quantity("0.01")
            .clientOrderID(oldClientOrderID),
        (report, exception) -> {
          Checker.checkReport.accept(report);
        });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
    // check
    wsClient.getAllActiveOrders(
        (reportList, exception) -> {
          if (exception != null) {
            fail();
          }
          Boolean present = false;
          for (Report order : reportList) {
            if (order.getClientOrderID().equals(oldClientOrderID))
              present = true;
          }
          if (!present)
            fail("could not find");
        });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }

    // replace
    wsClient.replaceSpotOrder(
        oldClientOrderID,
        newClientOrderID,
        "0.02",
        "2000",
        null,
        (report, exception) -> {
          if (exception != null) {
            fail();
          }
          if (!report.getOriginalClientOrderID().equals(oldClientOrderID)) {
            fail();
          }
        });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }

    // cancel
    wsClient.cancelSpotOrder(
        newClientOrderID,
        (report, exception) -> {
          if (exception != null) {
            fail();
          }
          if (!report.getStatus().equals(OrderStatus.CANCELED))
            fail();
          if (report.getClientOrderID().equals(oldClientOrderID))
            fail();
        });

    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
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
    BiConsumer<List<Report>, CryptomarketSDKException> resultBiConsumer = (reportList, exception) -> {
      if (exception != null) {
        fail();
      }
      if (reportList.size() != 5) {
        fail();
      }
      reportList.forEach(Checker.checkReport);
    };
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
    wsClient.getAllActiveOrders(resultBiConsumer);
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
    wsClient.cancelAllSpotOrders(resultBiConsumer);
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testGetSpotTradingCommissions() {
    wsClient.getSpotCommissions((result, exception) -> {
      result.forEach(Checker.checkCommission);
    });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testGetSpotTradingCommission() {
    wsClient.getSpotCommissionOfSymbol("EOSETH", (result, exception) -> {
      Checker.checkCommission.accept(result);
    });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testCreateOrderList() {
    String orderListID = String.format("%d", System.currentTimeMillis());
    String secondClientOrderID = String.format("%d", System.currentTimeMillis()) + "2";
    String symbol = "EOSETH";
    Side side = Side.SELL;
    String quantity = "0.01";
    String price = "10000";
    wsClient.createSpotOrderList(
        ContingencyType.ALL_OR_NONE,
        Arrays.asList(
            new OrderBuilder()
                .clientOrderID(orderListID)
                .symbol(symbol)
                .side(side)
                .quantity(quantity)
                .price(price),
            new OrderBuilder()
                .clientOrderID(secondClientOrderID)
                .symbol(symbol)
                .side(side)
                .quantity(quantity)
                .price(price)),
        orderListID,
        (result, exception) -> {
          if (exception != null) {
            System.out.println(exception);
          }
          System.out.println(result);
          result.forEach(Checker.checkReport);
        });
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      fail();
    }
  }
}
