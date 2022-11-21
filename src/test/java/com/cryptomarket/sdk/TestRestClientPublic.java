package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.Price;
import com.cryptomarket.sdk.models.PriceHistory;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.models.TickerPrice;
import com.cryptomarket.params.SortBy;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Sort;

import org.junit.Test;

public class TestRestClientPublic {
  CryptomarketRestClient client = new CryptomarketRestClientImpl();

  @Test
  public void testGetAllCurrencies() {
    try {
      Map<String, Currency> currencies = client.getCurrencies(null);
      assertTrue(currencies.size() > 0);
      currencies.values().forEach(Checker.checkCurrency);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGet2Currencies() {
    try {
      List<String> currencyIds = new ArrayList<String>(Arrays.asList("EOS", "eth"));
      Map<String, Currency> currencies = client.getCurrencies(currencyIds);
      assertTrue(currencies.size() == 2);
      currencies.values().forEach(Checker.checkCurrency);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetCurrency() {
    try {
      Currency curr = client.getCurrency("EOS");
      Checker.checkCurrency.accept(curr);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetAllSymbols() {
    try {
      Map<String, Symbol> symbols = client.getSymbols(null);
      assertTrue(symbols.size() > 0);
      symbols.values().forEach(Checker.checkSymbol);
    } catch (CryptomarketSDKException e) {
      fail();
    }
  }

  @Test
  public void testGetASymbol() {
    try {
      List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH"));
      Map<String, Symbol> symbols = client.getSymbols(symbolIds);
      assertTrue(symbols.size() == 1);
      symbols.values().forEach(Checker.checkSymbol);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGet2Symbols() {
    try {
      List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
      Map<String, Symbol> symbols = client.getSymbols(symbolIds);
      assertTrue(symbols.size() == 2);
      symbols.values().forEach(Checker.checkSymbol);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetSymbol() {
    try {
      Symbol symbol = client.getSymbol("EOSETH");
      Checker.checkSymbol.accept(symbol);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetAllTickers() {
    try {
      Map<String, Ticker> tickers = client.getTickers(null);
      assertTrue(tickers.size() > 0);
      tickers.values().forEach(Checker.checkTicker);

    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGet2Tickers() {
    try {
      List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
      Map<String, Ticker> tickers = client.getTickers(symbolIds);
      assertTrue(tickers.size() == 2);
      tickers.values().forEach(Checker.checkTicker);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetAllPrices() {
    try {
      Map<String, Price> prices = client.getPrices("ETH", null);
      assertTrue(prices.size() > 2);
      prices.values().forEach(Checker.checkPrice);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetPrice() {
    try {
      Map<String, Price> prices = client.getPrices("ETH", "XLM");
      assertTrue(prices.size() == 1);
      prices.values().forEach(Checker.checkPrice);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetAllPricesHistory() {
    try {
      Map<String, PriceHistory> prices = client.getPricesHistory("ETH", null, null, null, null, null, null);
      assertTrue(prices.size() > 2);
      prices.values().forEach(Checker.checkPriceHistory);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetSomePricesHistory() {
    try {
      Map<String, PriceHistory> prices = client.getPricesHistory("ETH", "XLM", null, null, 3, null, null);
      assertTrue(prices.size() == 1);
      prices.values().forEach(Checker.checkPriceHistory);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetTickerPrice() {
    try {
      List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
      Map<String, TickerPrice> prices = client.getTickerPrices(symbols);
      assertTrue(prices.size() > 1);
      prices.values().forEach(Checker.checkTickerPrice);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetTickerPriceOfSymbol() {
    try {
      TickerPrice price = client.getTickerPriceOfSymbol("EOSETH");
      Checker.checkTickerPrice.accept(price);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void getTrades() {
    try {
      List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
      Map<String, List<PublicTrade>> trades = client.getTrades(symbols, null, null, null, null, "2");
      assertTrue(trades.keySet().size() == 2);
      trades.forEach((key, list) -> {
        assertTrue(list.size() == 2);
      });
      trades.forEach((key, tradeList) -> {
        tradeList.forEach(Checker.checkPublicTrade);
      });
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void getTradesSortByIDWithLimit() {
    try {
      Map<String, List<PublicTrade>> trades = client.getTrades(
          Arrays.asList("EOSETH"),
          null,
          SortBy.ID,
          null,
          "1632334875",
          "2");
      trades.forEach((key, list) -> {
        assertTrue(list.size() == 2);
      });
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void getOrderbooks() {
    try {
      List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
      Map<String, OrderBook> orderbooks = client.getOrderBooks(symbols, 5);
      assertTrue(orderbooks.keySet().size() == 2);
      orderbooks.forEach((key, ob) -> {
        Checker.checkOB.accept(ob);
      });
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void getOneOrderbook() {
    try {
      List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH"));
      OrderBook orderbook = client.getOrderBooks(symbols, 5).get("EOSETH");
      Checker.checkOB.accept(orderbook);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void getOrderbook() {
    try {
      OrderBook orderbook = client.getOrderBookOfSymbol("EOSETH", 5);
      Checker.checkOB.accept(orderbook);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void getCandles() {
    try {
      List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH"));
      Map<String, List<Candle>> candles = client.getCandles(symbols, Period._4_HOURS, Sort.ASC, null, null, null,
          null);
      assertTrue(candles.keySet().size() == 1);
      candles.forEach((key, candleList) -> {
        candleList.forEach(Checker.checkCandle);
      });
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }
}