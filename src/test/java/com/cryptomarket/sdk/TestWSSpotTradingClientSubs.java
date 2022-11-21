package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Side;
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
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }

    String clientOrderID = String.format("%d", System.currentTimeMillis());
    wsClient.createSpotOrder(
        new ParamsBuilder()
            .clientOrderID(clientOrderID)
            .symbol("EOSETH")
            .side(Side.SELL)
            .price("1000")
            .quantity("0.01"),
        null);
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
    wsClient.cancelSpotOrder(clientOrderID, null);
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
    wsClient.unsubscribeToReports((result, exception) -> {
      if (!result) {
        fail();
      }
    });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }
}
