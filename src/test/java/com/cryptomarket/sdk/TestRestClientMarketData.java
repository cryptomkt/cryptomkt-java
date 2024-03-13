package com.cryptomarket.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import com.cryptomarket.sdk.rest.CryptomarketRestClient;
import com.cryptomarket.sdk.rest.CryptomarketRestClientImpl;
import com.cryptomarket.params.SortBy;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Sort;

import org.junit.Test;

public class TestRestClientMarketData {
  CryptomarketRestClient client = new CryptomarketRestClientImpl();

  @Test
  public void testGetAllCurrencies() throws CryptomarketSDKException {
    Map<String, Currency> currencies = client.getCurrencies(null, null);
    assertTrue(currencies.size() > 0);
    currencies.values().forEach(Checker.checkCurrency);
  }

  @Test
  public void testGet2Currencies() throws CryptomarketSDKException {
    List<String> currencyIds = new ArrayList<String>(Arrays.asList("EOS", "eth"));
    Map<String, Currency> currencies = client.getCurrencies(currencyIds, null);
    assertTrue(currencies.size() == 2);
    currencies.values().forEach(Checker.checkCurrency);

  }

  @Test
  public void testGetCurrency() throws CryptomarketSDKException {
    Currency curr = client.getCurrency("EOS");
    Checker.checkCurrency.accept(curr);
  }

  @Test
  public void testGetAllSymbols() throws CryptomarketSDKException {
    Map<String, Symbol> symbols = client.getSymbols(null);
    assertTrue(symbols.size() > 0);
    symbols.values().forEach(Checker.checkSymbol);
  }

  @Test
  public void testGetASymbol() throws CryptomarketSDKException {
    List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH"));
    Map<String, Symbol> symbols = client.getSymbols(symbolIds);
    assertTrue(symbols.size() == 1);
    symbols.values().forEach(Checker.checkSymbol);
  }

  @Test
  public void testGet2Symbols() throws CryptomarketSDKException {
    List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
    Map<String, Symbol> symbols = client.getSymbols(symbolIds);
    assertTrue(symbols.size() == 2);
    symbols.values().forEach(Checker.checkSymbol);
  }

  @Test
  public void testGetSymbol() throws CryptomarketSDKException {
    Symbol symbol = client.getSymbol("EOSETH");
    Checker.checkSymbol.accept(symbol);
  }

  @Test
  public void testGetAllTickers() throws CryptomarketSDKException {
    Map<String, Ticker> tickers = client.getTickers(null);
    assertTrue(tickers.size() > 0);
    tickers.values().forEach(Checker.checkTicker);
  }

  @Test
  public void testGet2Tickers() throws CryptomarketSDKException {
    List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
    Map<String, Ticker> tickers = client.getTickers(symbolIds);
    assertTrue(tickers.size() == 2);
    tickers.values().forEach(Checker.checkTicker);
  }

  @Test
  public void testGetAllPrices() throws CryptomarketSDKException {
    Map<String, Price> prices = client.getPrices("ETH", null);
    assertTrue(prices.size() > 2);
    prices.values().forEach(Checker.checkPrice);
  }

  @Test
  public void testGetPrice() throws CryptomarketSDKException {
    Map<String, Price> prices = client.getPrices("ETH", "XLM");
    assertTrue(prices.size() == 1);
    prices.values().forEach(Checker.checkPrice);
  }

  @Test
  public void testGetAllPricesHistory() throws CryptomarketSDKException {
    Map<String, PriceHistory> prices = client.getPricesHistory("ETH", null, null, null, null, null, null);
    assertTrue(prices.size() > 2);
    prices.values().forEach(Checker.checkPriceHistory);
  }

  @Test
  public void testGetSomePricesHistory() throws CryptomarketSDKException {
    Map<String, PriceHistory> prices = client.getPricesHistory("ETH", "XLM", null, null, 3, null, null);
    assertTrue(prices.size() == 1);
    prices.values().forEach(Checker.checkPriceHistory);
  }

  @Test
  public void testGetTickerPrice() throws CryptomarketSDKException {
    List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
    Map<String, TickerPrice> prices = client.getTickerLastPrices(symbols);
    assertTrue(prices.size() > 1);
    prices.values().forEach(Checker.checkTickerPrice);
  }

  @Test
  public void testGetTickerPriceOfSymbol() throws CryptomarketSDKException {
    TickerPrice price = client.getTickerLastPriceBySymbol("EOSETH");
    Checker.checkTickerPrice.accept(price);
  }

  @Test
  public void getTrades() throws CryptomarketSDKException {
    List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
    Map<String, List<PublicTrade>> trades = client.getTrades(symbols, null, null, null, null, "2");
    assertTrue(trades.keySet().size() == 2);
    trades.forEach((key, list) -> {
      assertTrue(list.size() == 2);
    });
    trades.forEach((key, tradeList) -> {
      tradeList.forEach(Checker.checkPublicTrade);
    });
  }

  @Test
  public void getTradesSortByIdWithLimit() throws CryptomarketSDKException {
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
  }

  @Test
  public void getOrderbooks() throws CryptomarketSDKException {
    List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH", "ETHBTC"));
    Map<String, OrderBook> orderbooks = client.getOrderBooks(symbols, 5);
    assertTrue(orderbooks.keySet().size() == 2);
    orderbooks.forEach((key, ob) -> {
      Checker.checkOB.accept(ob);
    });
  }

  @Test
  public void getOneOrderbook() throws CryptomarketSDKException {
    List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH"));
    OrderBook orderbook = client.getOrderBooks(symbols, 5).get("EOSETH");
    Checker.checkOB.accept(orderbook);
  }

  @Test
  public void getOrderbook() throws CryptomarketSDKException {
    OrderBook orderbook = client.getOrderBookBySymbol("EOSETH", 3);
    Checker.checkOB.accept(orderbook);
  }

  @Test
  public void getCandles() throws CryptomarketSDKException {
    List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH"));
    Map<String, List<Candle>> candles = client.getCandles(symbols, Period._4_HOURS, Sort.ASC, null, null, null);
    assertTrue(candles.keySet().size() == 1);
    candles.forEach((key, candleList) -> {
      candleList.forEach(Checker.checkCandle);
    });
  }

  @Test
  public void getCandlesBySymbol() throws CryptomarketSDKException {
    var candles = client.getCandlesBySymbol("EOSETH", Period._4_HOURS, Sort.ASC, null, null, null, null);
    candles.forEach(Checker.checkCandle);
  }

  @Test
  public void getConvertedCandles() throws CryptomarketSDKException {
    var symbols = new ArrayList<String>(Arrays.asList("EOSETH", "CROETH", "CROBTC"));
    var targetCurrency = "BTC";
    var convertedCandles = client.getConvertedCandles(targetCurrency, symbols, Period._4_HOURS, Sort.ASC, null, null,
        null);
    System.out.println(convertedCandles);
    assertEquals(targetCurrency, convertedCandles.getTargetCurrency());
    assertTrue(convertedCandles.getData().keySet().size() == 3);
    convertedCandles.getData().forEach((key, candleList) -> {
      candleList.forEach(Checker.checkCandle);
    });
  }

  @Test
  public void getConvertedCandlesBySymbol() throws CryptomarketSDKException {
    var targetCurrency = "BTC";
    var convertedCandles = client.getConvertedCandlesBySymbol(targetCurrency, "EOSETH", Period._4_HOURS, Sort.ASC, null,
        null, null, null);
    System.out.println(convertedCandles);
    assertEquals(targetCurrency, convertedCandles.getTargetCurrency());
    convertedCandles.getData().forEach(Checker.checkCandle);
  }
}
