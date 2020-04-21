package com.cryptomkt.api;

import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PublicEndpointsTest {

    protected Client client;

    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    protected void printObject(Object object) {
        try {
            String jsonString = this.mapper.writeValueAsString(object);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        String apiKey = "";
        String apiSecret = "";
        try {
            List<String> allLines = Files.readAllLines(Paths.get("/home/ismael/cptmkt/keys.txt"));
            apiKey = allLines.get(0);
            apiSecret = allLines.get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = new ClientImpl(apiKey, apiSecret);

    }

    @Test
    public void testGetMarkets() {
        try {
            List<String> markets = client.getMarkets().getMarkets();
            this.printObject(markets);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllTickers() {
        try {
            List<Ticker> tickers = client.getTickers().getTickers();
            this.printObject(tickers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOneTicker() {
        try {
            List<Ticker> tickers = client.getTickers("BTCBRL").getTickers();
            this.printObject(tickers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = CryptoMarketException.class)
    public void testGetTickerWrongMarket() throws CryptoMarketException {
        List<Ticker> tickers = client.getTickers("BBBBB").getTickers();
        this.printObject(tickers);

    }

    @Test
    public void testBook() {
        try {
            List<Book> books = client.getBook("ETHCLP", "buy").getBooks();
            this.printObject(books);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTrades() {
        try {
            List<Trade> trades = client.getTrades("ETHARS").getTrades();
            this.printObject(trades);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPrices() {
        try {
            Prices prices = client.getPrices("XLMCLP", "240", 1, 5).getPrices();
            this.printObject(prices);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

}
