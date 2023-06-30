package com.cryptomarket.sdk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

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
      wsClient = new CryptomarketWSSpotTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
      wsClient.connect();

      Helpers.sleep(3);
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
  public void testReportSubscription() {
    wsClient.subscribeToReports(
        (data, notificationType) -> {
          data.forEach(report -> Checker.checkReport.accept(report));
        },
        (result, exception) -> {
          if (!result) {
            fail();
          }
        });
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
    FailChecker failChecker = new FailChecker();
    wsClient.subscribeToSpotBalances(
        SubscriptionMode.UPDATES,
        Helpers.listChecker(failChecker, Checker.checkBalance),
        null);

    Helpers.sleep(4);
    String clientOrderId = String.format("%d", System.currentTimeMillis());
    makeSampleOrder(clientOrderId);

    Helpers.sleep(4);
    cancelOrder(clientOrderId);

    // three updates, the first, one creation and one cancel.

    wsClient.unsubscribeToSpotBalances((result, exception) -> {
      if (exception != null) {
        fail(exception.toString());
      }
      if (!result) {
        fail();
      }
    });
    Helpers.sleep(4);
    assertFalse(failChecker.failed());

  }
}
