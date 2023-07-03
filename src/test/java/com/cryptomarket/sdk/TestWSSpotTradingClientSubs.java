package com.cryptomarket.sdk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;

import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.SubscriptionMode;
import com.cryptomarket.sdk.Helpers.FailChecker;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSSpotTradingClientSubs {
  CryptomarketWSSpotTradingClient wsClient;
  
  @Before
  public void before() throws IOException {
    wsClient = new CryptomarketWSSpotTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
    wsClient.connect();
    Helpers.sleep(3);
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
  public void testReportSubscription() {
    var failChecker = new FailChecker();
    wsClient.subscribeToReports(
        Helpers.notificationListChecker(failChecker, Checker.checkReport),
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBooleanTrue));
    Helpers.sleep(3);

    String clientOrderId = String.format("%d", System.currentTimeMillis());
    makeSampleOrder(clientOrderId);
    cancelOrder(clientOrderId);
    wsClient.unsubscribeToReports((result, exception) -> {
      if (!result) {
        fail();
      }
    });

    Helpers.sleep(3);
    assertFalse(failChecker.failed());
  }

  private void cancelOrder(String clientOrderId) {
    wsClient.cancelSpotOrder(clientOrderId, null);

    Helpers.sleep(3);
  }

  private void makeSampleOrder(String clientOrderId) {
    wsClient.createSpotOrder(
        new ParamsBuilder()
            .clientOrderId(clientOrderId)
            .symbol("EOSETH")
            .side(Side.SELL)
            .price("1000")
            .quantity("0.01"),
        null);
    Helpers.sleep(3);
  }

  @Test
  public void testSpotBalanceSubscriptions() {
    var failChecker = new FailChecker();
    wsClient.subscribeToSpotBalances(
        SubscriptionMode.UPDATES,
        Helpers.notificationListChecker(failChecker, Checker.checkBalance),
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBooleanTrue));

    Helpers.sleep(4);
    String clientOrderId = String.format("%d", System.currentTimeMillis());
    makeSampleOrder(clientOrderId);

    Helpers.sleep(4);
    cancelOrder(clientOrderId);

    // three updates, the first, one creation and one cancel.

    wsClient.unsubscribeToSpotBalances(
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBooleanTrue));
    Helpers.sleep(4);
    assertFalse(failChecker.failed());

  }
}
