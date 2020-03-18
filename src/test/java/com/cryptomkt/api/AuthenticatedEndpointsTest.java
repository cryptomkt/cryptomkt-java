package com.cryptomkt.api;

import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AuthenticatedEndpointsTest extends TestCase {

    protected CryptoMarket cryptoMarket;
    private ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    protected void printObject(Object object) {
        try {
            String jsonString = this.mapper.writeValueAsString(object);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    protected void setUp() {
        String apiKey = "";
        String apiSecret = "";
        try {
            List<String> allLines = Files.readAllLines(Paths.get("/home/ismael/cptmkt/keys.txt"));
            apiKey = allLines.get(0);
            apiSecret = allLines.get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cryptoMarket = new CryptoMarketImpl(apiKey, apiSecret);

    }

    public void testGetAccount() {
        try {
            Account account = cryptoMarket.getAccount().getAccount();
            this.printObject(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testCreateOrder() {
        try {
            Order order = cryptoMarket.createOrder("XLMCLP", "104", "sell", "limit", "1")
                    .getOrder();
            this.printObject(order);
        } catch (IOException | CryptoMarketException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testGetActiveOrders() {
        try {
            List<Order> orders = cryptoMarket.getActiveOrders("XLMCLP")
                    .getOrders();
            this.printObject(orders);
        } catch (IOException | CryptoMarketException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testExecutedOrders() {
        try {
            List<Order> orders = cryptoMarket.getExecutedOrders("XLMCLP")
                    .getOrders();
            this.printObject(orders);
        } catch (IOException | CryptoMarketException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testGetBalance() {
        try {
            List<Balance> balance = cryptoMarket.getBalance().getBalances();
            this.printObject(balance);
        } catch (IOException | CryptoMarketException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testGetTransactions() {
        try {
            List<Transaction> transactions = cryptoMarket.getTransactions("XLM").getTransactions();
            this.printObject(transactions);
        } catch (IOException | CryptoMarketException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }
}
