package com.cryptomkt.api;

import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.entity.orders.*;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
        return httpClient.get("account", null, AccountResponse.class);
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
        payload.put("price", price);
        payload.put("side", side);
        payload.put("type", type);
        payload.put("amount", amount);
        return httpClient.post("orders/create", payload, OrderResponse.class);
    }

    @Override
    public CreateMultiOrderResponse createMultiOrders(MultiOrderRequest multiOrderRequest) throws IOException, CryptoMarketException {
        List<Map<String, String>> orders = multiOrderRequest.getOrders();
        Map<String, String> payload = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, false);
        String jsonOrders = mapper.writeValueAsString(orders);
        payload.put("orders", jsonOrders);
        return httpClient.post("orders/create/bulk", payload, CreateMultiOrderResponse.class);
    }

    @Override
    public OrderResponse getOrderStatus(String id) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("id", id);
        return httpClient.post("orders/status", payload, OrderResponse.class);
    }

    @Override
    public OrderResponse cancelOrder(String id) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("id", id);
        return httpClient.post("orders/cancel", payload, OrderResponse.class);
    }

    @Override
    public CancelMultiOrderResponse cancelMultiOrder(List<String> ids) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        StringBuilder strBuilder = new StringBuilder("[");
        for (String id : ids) {
            strBuilder.append("{\"id\":\"");
            strBuilder.append(id);
            strBuilder.append("\"},");
        }
        strBuilder.deleteCharAt(strBuilder.length()-1);
        strBuilder.append("]");
        payload.put("ids", strBuilder.toString());
        return httpClient.post("orders/cancel/bulk", payload, CancelMultiOrderResponse.class);
    }

    @Override
    public BalanceResponse getBalance() throws IOException, CryptoMarketException {
        return httpClient.get("balance", null, BalanceResponse.class);
    }

    @Override
    public TransactionsResponse getTransactions(String currency) throws IOException, CryptoMarketException {
        return this.getTransactions(currency, 0, 20);
    }

    @Override
    public TransactionsResponse getTransactions(String currency, int page) throws IOException, CryptoMarketException {
        return this.getTransactions(currency, page, 20);
    }

    @Override
    public TransactionsResponse getTransactions(String currency, int page, int limit) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("currency", currency);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("transactions", payload, TransactionsResponse.class);
    }

    @Override
    public Response notifyDeposit(String amount, String bankAccount) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        return httpClient.post("deposit", payload, Response.class);
    }


    @Override
    public Response notifyDeposit(String amount, String bankAccount, File voucher) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        payload.put("voucher", "voucher");
        return httpClient.post("deposit", payload, Response.class);
    }

    @Override
    public Response notifyDeposit(String amount, String bankAccount, String date, String trakingCode, File voucher) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        payload.put("date", date);
        payload.put("traking_code", trakingCode);
        payload.put("voucher", "voucher");
        return httpClient.post("deposit", payload, Response.class);
    }

    @Override
    public Response notifyWithdrawal(String amount, String bankAccount) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        return httpClient.post("withdrawal", payload, Response.class);
    }

    @Override
    public Response transfer(String address, String amount, String currency, String memo) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("address", address);
        payload.put("amount", amount);
        payload.put("currency", currency);
        payload.put("memo", memo);
        return httpClient.post("transfer", payload, Response.class);
    }

    @Override
    public Response transfer(String address, String amount, String currency) throws IOException, CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("address", address);
        payload.put("amount", amount);
        payload.put("currency", currency);
        return httpClient.post("transfer", payload, Response.class);
    }

    @Override
    public SocAuthResponse getAuthSocket() throws IOException, CryptoMarketException{
        return httpClient.get("socket/auth",null, SocAuthResponse.class);
    }

    @Override
    public SocketIoImpl getSocket() throws IOException, CryptoMarketException, URISyntaxException {
        SocAuthResponse auth =  getAuthSocket();
        return new SocketIoImpl(auth);
    }
}
