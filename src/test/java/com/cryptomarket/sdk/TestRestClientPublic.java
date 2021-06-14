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
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.params.Pagination;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Sort;

import org.junit.Test;

public class TestRestClientPublic {
    CryptomarketRestClient client = new CryptomarketRestClientImpl();
    

    @Test
    public void testGetAllSymbols() {
        try {
            List<Symbol> symbols = client.getSymbols(null);
            assertTrue(symbols.size() > 0);
            symbols.forEach(Checker.checkSymbol);
        } catch (CryptomarketSDKException e) {
            fail();
        }
    }


    @Test
    public void testGetASymbol() {
        try {
            List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH"));
            List<Symbol> symbols = client.getSymbols(symbolIds);
            assertTrue(symbols.size() == 1);
            symbols.forEach(Checker.checkSymbol);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testGet2Symbols() {
        try {
            List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH","ETHBTC"));
            List<Symbol> symbols = client.getSymbols(symbolIds);
            assertTrue(symbols.size() == 2);
            symbols.forEach(Checker.checkSymbol);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testGetSymbol() {
        try {
            Symbol symbol = client.getSymbol("EOSETH");
            Checker.checkSymbol.accept(symbol);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testGetAllCurrencies() {
        try {
            List<Currency> currencies = client.getCurrencies(null);
            assertTrue(currencies.size() > 0);
            currencies.forEach(Checker.checkCurrency);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGet2Currencies() {
        try {
            List<String> currencyIds = new ArrayList<String>(Arrays.asList("EOS", "eth"));
            List<Currency> currencies = client.getCurrencies(currencyIds);
            assertTrue(currencies.size() == 2);
            currencies.forEach(Checker.checkCurrency);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetCurrency() {
        try {
            Currency curr = client.getCurrency("EOS");
            Checker.checkCurrency.accept(curr);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetAllTickers() {
        List<String> nullTickers = new ArrayList<>(Arrays.asList("BTCEUR", "ETHCLP", "ETHMXN", "ETHUYU", "ETHBRL", "BTCARS", "ETHCOP", "BTCUYU", "BTCCOP", "ETHVEF", "ETHARS", "ETHPEN", "BTCCLP", "BTCMXN", "BTCBRL", "ETHEUR", "BTCPEN", "BTCVEF"));
        try {
            List<Ticker> tickers = client.getTickers(null);
            assertTrue(tickers.size() > 0);

            System.out.print(tickers.size());

            for (String symbol: nullTickers) {
                for (Ticker ticker: tickers) {
                    if (ticker.getSymbol().equals(symbol)) {
                        tickers.remove(ticker);
                        break;
                    }
                }
            };
            System.out.print(tickers.size());
            tickers.forEach(Checker.checkTicker);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGet2Tickers() {
        try {
            List<String> symbolIds = new ArrayList<String>(Arrays.asList("EOSETH","ETHBTC"));
            List<Ticker> tickers = client.getTickers(symbolIds);
            assertTrue(tickers.size() == 2);
            tickers.forEach(Checker.checkTicker);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test 
    public void getTrades() {
        try {
            List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH","ETHBTC"));
            Map<String, List<PublicTrade>>  trades = client.getTrades(symbols, null, null, null, 2, 2);
            assertTrue(trades.keySet().size() == 2);
            trades.forEach((key, list) -> {assertTrue(list.size() == 2);});
            trades.forEach((key, tradeList) -> {tradeList.forEach(Checker.checkPublicTrade);});
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test 
    public void getTradesWithIdPagination() {
        try {
            List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH","ETHBTC"));
            Map<String, List<PublicTrade>>  trades = client.getTrades(symbols, new Pagination.Builder()
            .sort(Sort.DESC)
            // .by(By.ID)
            .from("1000000000")
            .till("2000000000")
            .limit(20)
            .offset(40)
            .build());
            assertTrue(trades.keySet().size() == 2);
            trades.forEach((key, list) -> {assertTrue(list.size() <= 20);});
            trades.forEach((key, tradeList) -> {tradeList.forEach(Checker.checkPublicTrade);});
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }

    }


    @Test 
    public void getTradesWithPagination() {
        try {
            List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH","ETHBTC"));
            Map<String, List<PublicTrade>>  trades = client.getTrades(symbols, new Pagination.Builder().limit(3).offset(2).build());
            assertTrue(trades.keySet().size() == 2);
            trades.forEach((key, list) -> {assertTrue(list.size() <= 3);});
            trades.forEach((key, tradeList) -> {tradeList.forEach(Checker.checkPublicTrade);});
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test 
    public void getOrderbooks() {
        try {
            List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH","ETHBTC"));
            Map<String, OrderBook>  orderbooks = client.getOrderBooks(symbols, 5);
            assertTrue(orderbooks.keySet().size() == 2);
            orderbooks.forEach((key, ob) -> {Checker.checkOB.accept(ob);});
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test 
    public void getOneOrderbook() {
        try {
            List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH"));
            OrderBook  orderbook = client.getOrderBooks(symbols, 5).get("EOSETH");
            Checker.checkOB.accept(orderbook);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test 
    public void getOrderbook() {
        try {
            OrderBook  orderbook = client.getOrderBook("EOSETH", 5);
            Checker.checkOB.accept(orderbook);
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test 
    public void getCandles() {
        try {
            List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH"));
            Map<String, List<Candle>> candles = client.getCandles(symbols, Period._4_HOURS, Sort.ASC, null, null, null, null);
            assertTrue(candles.keySet().size() == 1);
            candles.forEach((key, candleList) -> {candleList.forEach(Checker.checkCandle);});
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test 
    public void getCandlesWithPagination() {
        try {
            List<String> symbols = new ArrayList<String>(Arrays.asList("EOSETH"));
            Map<String, List<Candle>> candles = client.getCandles(symbols,  null, new Pagination.Builder().sort(Sort.ASC).limit(2).build());
            assertTrue(candles.keySet().size() == 1);
            candles.forEach((key, cList) -> {assertTrue(cList.size() <= 2);});
            candles.forEach((key, candleList) -> {candleList.forEach(Checker.checkCandle);});
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }
}