package com.cryptomarket.sdk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.cryptomarket.params.Depth;
import com.cryptomarket.params.OBSpeed;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.PriceSpeed;
import com.cryptomarket.params.TickerSpeed;
import com.cryptomarket.sdk.Helpers.FailChecker;
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
  public void before() throws IOException {
    wsClient = new CryptomarketWSMarketDataClientImpl();
    wsClient.connect();

    Helpers.sleep(1);
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
    FailChecker failChecker = new FailChecker();
    
    wsClient.subscribeToTrades(
        Helpers.mapListChecker(failChecker, Checker.checkWSPublicTrade),
        symbols,
        null,
        (result, exception) -> {
          if (exception != null) {
            System.out.println(exception.toString());
            fail();
          }
        });

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToCandles() {
    List<String> symbols = Arrays.asList("EOSETH", "ETHBTC");
    FailChecker failChecker = new FailChecker();

    wsClient.subscribeToCandles(
        Helpers.mapListChecker(failChecker, Checker.checkWSCandle),
        Period._1_MINUTES,
        symbols,
        null,
        null);

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToPriceRates() {
    List<String> currencies = Arrays.asList("BTC", "ETH");
    FailChecker failChecker = new FailChecker();

    wsClient.subscribeToPriceRates(
        Helpers.mapChecker(failChecker, Checker.checkWSPriceRate),
        PriceSpeed._1_SECONDS,
        "USDT",
        currencies,
        null);

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToMiniTicker() {
    List<String> symbols = Arrays.asList("EOSETH");
    FailChecker failChecker = new FailChecker();

    wsClient.subscribeToMiniTicker(
        Helpers.mapChecker(failChecker, Checker.checkWSCandle),
        TickerSpeed._1_SECONDS,
        symbols,
        null);

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToTicker() {
    List<String> symbols = Arrays.asList("EOSETH");
    FailChecker failChecker = new FailChecker();

    wsClient.subscribeToTicker(
        Helpers.mapChecker(failChecker, Checker.checkWSTicker),
        TickerSpeed._1_SECONDS,
        symbols,
        null);

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testFullOrderbook() {
    List<String> symbols = Arrays.asList("EOSETH");
    FailChecker failChecker = new FailChecker();

    wsClient.subscribeToFullOrderBook(
        Helpers.mapChecker(failChecker, Checker.checkWSOrderBook),
        symbols,
        null);

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testPartialOrderbook() {
    List<String> symbols = Arrays.asList("EOSETH");
    FailChecker failChecker = new FailChecker();
    
    wsClient.subscribeToPartialOrderBook(
        Helpers.mapChecker(failChecker, Checker.checkWSOrderBook),
        Depth._5,
        OBSpeed._500_MILISECONDS,
        symbols,
        null);

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testOrderbookTop() {
    List<String> symbols = Arrays.asList("EOSETH");
    FailChecker failChecker = new FailChecker();
    
    wsClient.subscribeToTopOfOrderBook(
        Helpers.mapChecker(failChecker, Checker.checkWSOrderBookTop),
        OBSpeed._500_MILISECONDS,
        symbols,
        null);

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

}
