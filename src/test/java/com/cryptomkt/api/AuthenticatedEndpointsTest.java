package com.cryptomkt.api;

import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.entity.orders.*;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AuthenticatedEndpointsTest extends TestCase {

    protected Client client;
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
        client = new ClientImpl(apiKey, apiSecret);

    }

    public void testGetAccount() {
        try {
            Account account = client.getAccount().getAccount();
            this.printObject(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCreateOrder() {
        try {
            Order order = client.createOrder("XLMCLP", "124", "sell", "limit", "1")
                    .getOrder();
            this.printObject(order);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testOrderStatus() {
        try {
            Order order = client.getOrderStatus("O5792153")
                    .getOrder();
            this.printObject(order);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testCancelOrder() {
        try {
            Order order = client.cancelOrder("O5596915")
                    .getOrder();
            this.printObject(order);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testGetActiveOrders() {
        try {
            List<Order> orders = client.getActiveOrders("XLMCLP")
                    .getOrders();
            this.printObject(orders);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testExecutedOrders() {
        try {
            List<Order> orders = client.getExecutedOrders("XLMCLP")
                    .getOrders();
            this.printObject(orders);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testGetBalance() {
        try {
            List<Balance> balance = client.getBalance().getBalances();
            this.printObject(balance);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testGetTransactions() {
        try {
            List<Transaction> transactions = client.getTransactions("XLM").getTransactions();
            this.printObject(transactions);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testCreateMultiOrder() {
        try {
            MultiOrderRequest multiOrderRequest = new MultiOrderRequest()
                    .add("XLMCLP", "limit", "sell", "110", "1")
                    .add("XLMCLP", "limit", "sell", "111", "1")
                    .add("XLMCLP", "limit", "sell", "112", "1")
                    .add("XLMCLP", "limit", "sell", "112", "1000000000");
            CreateMultiOrderResponse createMultiOrderResponse = client.createMultiOrders(multiOrderRequest);
            printObject(createMultiOrderResponse);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    public void testCancelMultiOrders() {
        try {
            List<Order> orders = client.getActiveOrders("XLMCLP").getOrders();
            List<String> ordersIds = new ArrayList<>();
            for (Order order : orders) {
                ordersIds.add(order.getId());
            }
            ordersIds.add("12121212");
            CancelMultiOrderResponse cancelMultiOrderResponse = client.cancelMultiOrder(ordersIds);
            printObject(cancelMultiOrderResponse);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    /*
    public void testDepositWithVoucher() {
        // get a pdf path
        String path = "/home/ismael/cptmkt/scripts-and-tests/pdf_test.pdf";
        try {
            Response response = client.notifyDeposit("100", "63514", path);
            System.out.println(response);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }
    */

    public void testTransfer() {
        Response result = null;
        try {
            result = client.transfer("GDMXNQBJMS3FYI4PFSYCCB4XODQMNMTKPQ5HIKOUWBOWJ2P3CF6WASBE", "1", "XLM", "25767435");
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        System.out.println(result.getStatus());
    }

}
