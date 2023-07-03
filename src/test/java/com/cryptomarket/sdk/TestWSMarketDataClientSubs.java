package com.cryptomarket.sdk;

import static org.junit.Assert.assertFalse;

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
  List<String> symbols = Arrays.asList("EOSETH");
  FailChecker failChecker;

  @Before
  public void before() throws IOException {
    wsClient = new CryptomarketWSMarketDataClientImpl();
    wsClient.connect();
    failChecker = new FailChecker();
    Helpers.sleep(1);
  }

  @After
  public void after() {
    wsClient.close();
  }

  @Test
  public void testTradesSubscription() {
    wsClient.subscribeToTrades(
        Helpers.notificationMapListChecker(failChecker, Checker.checkWSPublicTrade),
        symbols,
        null,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToCandles() {
    symbols = Arrays.asList("EOSETH", "ETHBTC");

    wsClient.subscribeToCandles(
        Helpers.notificationMapListChecker(failChecker, Checker.checkWSCandle),
        Period._1_MINUTES,
        symbols,
        null,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToPriceRates() {
    List<String> currencies = Arrays.asList("BTC", "ETH");

    wsClient.subscribeToPriceRates(
        Helpers.notificationMapChecker(failChecker, Checker.checkWSPriceRate),
        PriceSpeed._1_SECONDS,
        "USDT",
        currencies,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToMiniTicker() {
    List<String> symbols = Arrays.asList("EOSETH");
    
    wsClient.subscribeToMiniTicker(
        Helpers.notificationMapChecker(failChecker, Checker.checkWSCandle),
        TickerSpeed._1_SECONDS,
        symbols,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToTicker() {
    List<String> symbols = Arrays.asList("EOSETH");
    
    wsClient.subscribeToTicker(
        Helpers.notificationMapChecker(failChecker, Checker.checkWSTicker),
        TickerSpeed._1_SECONDS,
        symbols,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testFullOrderbook() {
    List<String> symbols = Arrays.asList("EOSETH");
    
    wsClient.subscribeToFullOrderBook(
        Helpers.notificationMapChecker(failChecker, Checker.checkWSOrderBook),
        symbols,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testPartialOrderbook() {
    List<String> symbols = Arrays.asList("EOSETH");
    
    wsClient.subscribeToPartialOrderBook(
        Helpers.notificationMapChecker(failChecker, Checker.checkWSOrderBook),
        Depth._5,
        OBSpeed._500_MILISECONDS,
        symbols,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testOrderbookTop() {
    List<String> symbols = Arrays.asList("EOSETH");
    
    wsClient.subscribeToTopOfOrderBook(
        Helpers.notificationMapChecker(failChecker, Checker.checkWSOrderBookTop),
        OBSpeed._500_MILISECONDS,
        symbols,
        Helpers.listAndExceptionChecker(failChecker, Checker.checkString));

    Helpers.sleep(30);
    assertFalse(failChecker.failed());
  }

}
