package com.cryptomkt.api;

import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.exception.CryptoMarketException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptoMarketImpl implements CryptoMarket {
    private static String apiUrl = "https://api.cryptomkt.com";
    private static String apiVersion = "v2";
    private HTTPClient httpClient;

    public CryptoMarketImpl() {
        this.httpClient = new HTTPClientImpl(apiUrl, apiVersion, "", "");
    }

    public CryptoMarketImpl(String apiKey, String apiSecret) {
        this.httpClient = new HTTPClientImpl(apiUrl, apiVersion, apiKey, apiSecret);
    }

    @Override
    public MarketsResponse getMarkets() throws IOException, CryptoMarketException{
        return httpClient.get("market", null, MarketsResponse.class);
    }

    @Override
    public TickersResponse getTickers() throws IOException, CryptoMarketException {
        return httpClient.get("ticker", null, TickersResponse.class);
    }

    @Override
    public TickersResponse getTickers(String market) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        return httpClient.get("ticker", payload, TickersResponse.class);
    }

    @Override
    public BookResponse getBook(String market, String type) throws IOException, CryptoMarketException {
        return this.getBook(market, type, 0, 20);
    }

    @Override
    public BookResponse getBook(String market, String type, int page) throws IOException, CryptoMarketException {
        return this.getBook(market, type, page, 20);
    }

    @Override
    public BookResponse getBook(String market, String side, int page, int limit) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("side", side);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("book", payload, BookResponse.class);
    }

    @Override
    public TradeResponse getTrades(String market) throws IOException, CryptoMarketException {
        return this.getTrades(market, "", "", 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start) throws IOException, CryptoMarketException {
        return this.getTrades(market, start, "", 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end) throws IOException, CryptoMarketException {
        return this.getTrades(market, start, end, 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end, int page) throws IOException, CryptoMarketException {
        return this.getTrades(market, start, end, page, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end, int page, int limit) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        String _start = start.isEmpty() ? "" : start;
        String _end = end.isEmpty() ? "" : end;
        payload.put("start", _start);
        payload.put("end", _end);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("trades", payload, TradeResponse.class);
    }

    @Override
    public PricesResponse getPrices(String market, String timeframe) throws IOException, CryptoMarketException {
        return this.getPrices(market, timeframe, 0, 20);
    }

    @Override
    public PricesResponse getPrices(String market, String timeframe, int page) throws IOException, CryptoMarketException {
        return this.getPrices(market, timeframe, page, 20);
    }

    @Override
    public PricesResponse getPrices(String market, String timeframe, int page, int limit) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("timeframe", timeframe);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("prices", payload, PricesResponse.class);
    }

    @Override
    public AccountResponse getAccount() throws IOException, CryptoMarketException {
        return httpClient.get("prices", null, AccountResponse.class);
    }

    @Override
    public OrdersResponse getActiveOrders(String market) throws IOException, CryptoMarketException {
        return this.getActiveOrders(market, 0, 20);
    }

    @Override
    public OrdersResponse getActiveOrders(String market, int page) throws IOException, CryptoMarketException {
        return this.getActiveOrders(market, page, 20);
    }

    @Override
    public OrdersResponse getActiveOrders(String market, int page, int limit) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("orders/active", payload, OrdersResponse.class);
    }

    @Override
    public OrdersResponse getExecutedOrders(String market) throws IOException, CryptoMarketException {
        return this.getExecutedOrders(market, 0, 20);
    }

    @Override
    public OrdersResponse getExecutedOrders(String market, int page) throws IOException, CryptoMarketException {
        return this.getExecutedOrders(market, page, 20);
    }

    @Override
    public OrdersResponse getExecutedOrders(String market, int page, int limit) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("orders/executed", payload, OrdersResponse.class);
    }

    @Override
    public OrderResponse createOrder(String market, String price, String side, String type, String amount) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("price", amount);
        payload.put("side", side);
        payload.put("type", type);
        payload.put("amount", amount);
        return httpClient.post("orders/create", payload, OrderResponse.class);
    }

    @Override
    public OrdersResponse createMultiOrders(List<Order> orderList) {
        return null;
    }

    @Override
    public OrderResponse getOrderStatus(String id) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public OrderResponse cancelOrder(String id) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public OrdersResponse cancelMultiOrder(List<String> idList) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public BalanceResponse getBalance() throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public TransactionsResponse getTransactions(String currency) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public TransactionsResponse getTransactions(String currency, int page) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public TransactionsResponse getTransactions(String currency, int page, int limit) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public Response notifyDeposit(String amount, String bankAccount) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public Response notifyDeposit(String amount, String bankAccount, File voucher) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public Response notifyDeposit(String amount, String bankAccount, String date, String trakingCode, File voucher) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public Response notifyWithdrawal(String amount, String bankAccount) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public Response transfer(String address, String amount, String currency, String memo) throws IOException, CryptoMarketException {
        return null;
    }

    @Override
    public Response transfer(String address, String amount, String currency) throws IOException, CryptoMarketException {
        return null;
    }
}
