package com.cryptomkt.api;

import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.entity.orders.*;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientImpl implements Client {
    private static String apiUrl = "https://api.cryptomkt.com";
    private static String apiVersion = "v2";
    private HTTPClient httpClient;


    public ClientImpl() {
        this.httpClient = new HTTPClientImpl(apiUrl, apiVersion, "", "");
    }

    public ClientImpl(String apiKey, String apiSecret) {
        this.httpClient = new HTTPClientImpl(apiUrl, apiVersion, apiKey, apiSecret);
    }

    @Override
    public MarketsResponse getMarkets() throws CryptoMarketException{
        return httpClient.get("market", null, MarketsResponse.class);
    }

    @Override
    public TickersResponse getTickers() throws CryptoMarketException {
        return httpClient.get("ticker", null, TickersResponse.class);
    }

    @Override
    public TickersResponse getTickers(String market) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        return httpClient.get("ticker", payload, TickersResponse.class);
    }

    @Override
    public BookResponse getBook(String market, String type) throws CryptoMarketException {
        return this.getBook(market, type, 0, 20);
    }

    @Override
    public BookResponse getBook(String market, String type, int page) throws CryptoMarketException {
        return this.getBook(market, type, page, 20);
    }

    @Override
    public BookResponse getBook(String market, String side, int page, int limit) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("side", side);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("book", payload, BookResponse.class);
    }

    @Override
    public TradeResponse getTrades(String market) throws CryptoMarketException {
        return this.getTrades(market, "", "", 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start) throws CryptoMarketException {
        return this.getTrades(market, start, "", 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end) throws CryptoMarketException {
        return this.getTrades(market, start, end, 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end, int page) throws CryptoMarketException {
        return this.getTrades(market, start, end, page, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end, int page, int limit) throws CryptoMarketException {
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
    public PricesResponse getPrices(String market, String timeframe) throws CryptoMarketException {
        return this.getPrices(market, timeframe, 0, 20);
    }

    @Override
    public PricesResponse getPrices(String market, String timeframe, int page) throws CryptoMarketException {
        return this.getPrices(market, timeframe, page, 20);
    }

    @Override
    public PricesResponse getPrices(String market, String timeframe, int page, int limit) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("timeframe", timeframe);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("prices", payload, PricesResponse.class);
    }

    @Override
    public AccountResponse getAccount() throws CryptoMarketException {
        return httpClient.get("account", null, AccountResponse.class);
    }

    @Override
    public OrdersResponse getActiveOrders(String market) throws CryptoMarketException {
        return this.getActiveOrders(market, 0, 20);
    }

    @Override
    public OrdersResponse getActiveOrders(String market, int page) throws CryptoMarketException {
        return this.getActiveOrders(market, page, 20);
    }

    @Override
    public OrdersResponse getActiveOrders(String market, int page, int limit) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("orders/active", payload, OrdersResponse.class);
    }

    @Override
    public OrdersResponse getExecutedOrders(String market) throws CryptoMarketException {
        return this.getExecutedOrders(market, 0, 20);
    }

    @Override
    public OrdersResponse getExecutedOrders(String market, int page) throws CryptoMarketException {
        return this.getExecutedOrders(market, page, 20);
    }

    @Override
    public OrdersResponse getExecutedOrders(String market, int page, int limit) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("orders/executed", payload, OrdersResponse.class);
    }

    @Override
    public OrderResponse createOrder(String market, String price, String side, String type, String amount) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("market", market);
        payload.put("price", price);
        payload.put("side", side);
        payload.put("type", type);
        payload.put("amount", amount);
        return httpClient.post("orders/create", payload, OrderResponse.class);
    }

    @Override
    public CreateMultiOrderResponse createMultiOrders(MultiOrderRequest multiOrderRequest) throws CryptoMarketException {
        List<Map<String, String>> orders = multiOrderRequest.getOrders();
        Map<String, String> payload = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, false);
        String jsonOrders;
        try {
            jsonOrders = mapper.writeValueAsString(orders);
        } catch (JsonProcessingException e) {
            throw new CryptoMarketException(e.getMessage());
        }
        payload.put("orders", jsonOrders);
        return httpClient.post("orders/create/bulk", payload, CreateMultiOrderResponse.class);
    }

    @Override
    public OrderResponse getOrderStatus(String id) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("id", id);
        return httpClient.post("orders/status", payload, OrderResponse.class);
    }

    @Override
    public OrderResponse cancelOrder(String id) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("id", id);
        return httpClient.post("orders/cancel", payload, OrderResponse.class);
    }

    @Override
    public CancelMultiOrderResponse cancelMultiOrder(List<String> ids) throws CryptoMarketException {
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
    public BalanceResponse getBalance() throws CryptoMarketException {
        return httpClient.get("balance", null, BalanceResponse.class);
    }

    @Override
    public TransactionsResponse getTransactions(String currency) throws CryptoMarketException {
        return this.getTransactions(currency, 0, 20);
    }

    @Override
    public TransactionsResponse getTransactions(String currency, int page) throws CryptoMarketException {
        return this.getTransactions(currency, page, 20);
    }

    @Override
    public TransactionsResponse getTransactions(String currency, int page, int limit) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("currency", currency);
        payload.put("page", Integer.toString(page));
        payload.put("limit", Integer.toString(limit));
        return httpClient.get("transactions", payload, TransactionsResponse.class);
    }

    @Override
    public Response notifyDeposit(String amount, String bankAccount) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        return httpClient.post("deposit", payload, Response.class);
    }


    @Override
    public Response notifyDeposit(String amount, String bankAccount, String voucher) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        payload.put("voucher", voucher);
        return httpClient.post("deposit", payload, Response.class);
    }

    @Override
    public Response notifyDeposit(String amount, String bankAccount, String date, String trakingCode, String voucher) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        payload.put("date", date);
        payload.put("traking_code", trakingCode);
        payload.put("voucher", voucher);
        return httpClient.post("deposit", payload, Response.class);
    }

    @Override
    public Response notifyWithdrawal(String amount, String bankAccount) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("amount", amount);
        payload.put("bank_account", bankAccount);
        return httpClient.post("withdrawal", payload, Response.class);
    }

    @Override
    public Response transfer(String address, String amount, String currency, String memo) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("address", address);
        payload.put("amount", amount);
        payload.put("currency", currency);
        payload.put("memo", memo);
        return httpClient.post("transfer", payload, Response.class);
    }

    @Override
    public Response transfer(String address, String amount, String currency) throws CryptoMarketException {
        Map<String, String> payload = new HashMap<>();
        payload.put("address", address);
        payload.put("amount", amount);
        payload.put("currency", currency);
        return httpClient.post("transfer", payload, Response.class);
    }

    @Override
    public SocAuthResponse getAuthSocket() throws CryptoMarketException{
        return httpClient.get("socket/auth",null, SocAuthResponse.class);
    }

    @Override
    public SocketIoImpl getSocket() throws CryptoMarketException {
        SocAuthResponse auth =  getAuthSocket();
        SocketIoImpl socket;
        try {
            socket = new SocketIoImpl(auth);
        } catch (URISyntaxException e) {
            throw new CryptoMarketException();
        }
        return socket;
    }
}
