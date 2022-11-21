package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.Depth;
import com.cryptomarket.params.OBSpeed;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.TickerSpeed;
import com.cryptomarket.sdk.websocket.CryptomarketWSMarketDataClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSMarketDataClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSMarketDataClientSubs {
  CryptomarketWSMarketDataClient wsClient;
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
      wsClient = new CryptomarketWSMarketDataClientImpl();
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
  public void testTradesSubscription() {
    List<String> symbols = Arrays.asList("EOSETH");
    wsClient.subscribeToTrades(
        (trades, notificatonType) -> {
          System.out.println(trades);
        },
        symbols,
        null,
        (result, exception) -> {
          if (exception != null) {
            System.out.println(exception.toString());
            fail();
          }
        });

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      fail();
    }
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testSubscribeToCandles() {
    List<String> symbols = Arrays.asList("EOSETH", "ETHBTC");

    wsClient.subscribeToCandles(
        (data, notificationType) -> {
          assertTrue(data.size() != 0);
          data.forEach((k, v) -> v.forEach(Checker.checkWSCandle));
        },
        Period._1_MINUTES,
        symbols,
        null,
        null);

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testSubscribeToMiniTicker() {
    List<String> symbols = Arrays.asList("EOSETH");

    wsClient.subscribeToMiniTicker(
        (data, notificationType) -> {
          System.out.println(notificationType);
          System.out.println(data);
          data.forEach((k, v) -> Checker.checkWSCandle.accept(v));
        },
        TickerSpeed._1_SECONDS,
        symbols,
        null);

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testSubscribeToTicker() {
    List<String> symbols = Arrays.asList("EOSETH");
    wsClient.subscribeToTicker(
        (data, notificationType) -> {
          System.out.println(data);
          data.forEach((k, v) -> Checker.checkWSTicker.accept(v));
        },
        TickerSpeed._1_SECONDS,
        symbols,
        null);

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testFullOrderbook() {
    List<String> symbols = Arrays.asList("EOSETH");

    wsClient.subscribeToFullOrderBook(
        (data, notificationType) -> {
          System.out.println(notificationType);
          System.out.println(data);
          data.forEach((k, v) -> Checker.checkWSOrderBook.accept(v));
        },
        symbols,
        null);

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testPartialOrderbook() {
    List<String> symbols = Arrays.asList("EOSETH");
    wsClient.subscribeToPartialOrderBook(
        (data, notificationType) -> {
          System.out.println(notificationType);
          System.out.println(data);
          data.forEach((k, v) -> Checker.checkWSOrderBook.accept(v));
        },
        Depth._5,
        OBSpeed._500_MILISECONDS,
        symbols,
        null);

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testOrderbookTop() {
    List<String> symbols = Arrays.asList("EOSETH");

    wsClient.subscribeToTopOfOrderBook(
        (data, notification) -> {
          System.out.println(notification);
          data.forEach((k, v) -> Checker.checkWSOrderBookTop.accept(v));
        },
        OBSpeed._500_MILISECONDS,
        symbols,
        null);

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      fail();
    }
  }

}
