package com.cryptomarket.sdk;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.params.By;
import com.cryptomarket.params.Margin;
import com.cryptomarket.params.OrderRequest;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.Pagination;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.Sort;
import com.cryptomarket.params.TimeInForce;
import com.cryptomarket.params.TransferBy;
import com.cryptomarket.params.TransferType;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Address;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.models.Trade;
import com.cryptomarket.sdk.models.Transaction;

public class CryptomarketRestClientImpl implements CryptomarketRestClient {
    HttpClient httpClient;
    Adapter adapter = new Adapter();

    public CryptomarketRestClientImpl(String apiKey, String apiSecret) {
        // httpClient = new HttpClientImpl(apiKey, apiSecret);
        // httpClient = new HttpClientApacheWithBasic(apiKey, apiSecret);
        httpClient = new HttpClientImpl(apiKey, apiSecret);
    }

    public CryptomarketRestClientImpl() {
        this("","");
    }

    // PUBLIC

    @Override
    public List<Currency> getCurrencies(List<String> currencies) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (currencies != null && currencies.size() > 0) params.put("currencies", String.join(",", currencies));
        String jsonResponse = httpClient.publicGet("public/currency", params);
        return adapter.listFromJson(jsonResponse, Currency.class);
    }

    @Override
    public Currency getCurrency(String currency) throws CryptomarketSDKException {
        String jsonResponse = httpClient.publicGet(String.format("public/currency/%s", currency), null);
        return adapter.objectFromJson(jsonResponse, Currency.class);
    }

    @Override
    public List<Symbol> getSymbols(List<String> symbols) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbols != null && symbols.size() > 0) params.put("symbols", String.join(",", symbols));
        String jsonResponse = httpClient.publicGet("public/symbol", params);
        return adapter.listFromJson(jsonResponse, Symbol.class);
    }

    @Override
    public Symbol getSymbol(String symbol) throws CryptomarketSDKException {
        String jsonResponse = httpClient.publicGet(String.format("public/symbol/%s", symbol), null);
        return adapter.objectFromJson(jsonResponse, Symbol.class);
    }

    @Override
    public List<Ticker> getTickers(List<String> symbols) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbols != null && symbols.size() > 0) params.put("symbols", String.join(",", symbols));
        String jsonResponse = httpClient.publicGet("public/ticker", params);
        return adapter.listFromJson(jsonResponse, Ticker.class);
    }

    @Override
    public Ticker getTicker(String symbol) throws CryptomarketSDKException {
        String jsonResponse = httpClient.publicGet(String.format("public/ticker/%s", symbol), null);
        return adapter.objectFromJson(jsonResponse, Ticker.class);
    }

    @Override
    public Map<String, List<PublicTrade>> getTrades(
    List<String> symbols, 
    Sort sort, 
    String from, 
    String till, 
    Integer limit,
    Integer offset) 
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbols != null && symbols.size() > 0) params.put("symbols", String.join(",", symbols));
        if (sort != null) params.put("sort", sort.name());
        if (from != null) params.put("from", from);
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        String jsonResponse = httpClient.publicGet("public/trades", params);
        return adapter.listMapFromJson(jsonResponse, PublicTrade.class);
    }

    @Override
    public Map<String, List<PublicTrade>> getTrades(List<String> symbols, Pagination pagination) throws CryptomarketSDKException {
        if (pagination == null) pagination = new Pagination.Builder().build();
        return getTrades(
            symbols, 
            pagination.sort,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset);
    }

    @Override
    public List<PublicTrade> getTradesOfSymbol(String symbol,Sort sort, String from, String till, Integer limit,Integer offset) 
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (sort != null) params.put("sort", sort.name());
        if (from != null) params.put("from", from);
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        String jsonResponse = httpClient.publicGet(String.format("public/trades/%s", symbol), params);
        return adapter.listFromJson(jsonResponse, PublicTrade.class);
    }

    @Override
    public List<PublicTrade> getTradesOfSymbol(String symbol, Pagination pagination) throws CryptomarketSDKException {
        if (pagination == null) pagination = new Pagination.Builder().build();
        return getTradesOfSymbol(
            symbol, 
            pagination.sort,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset);
    }

    @Override
    public Map<String, OrderBook> getOrderBooks(List<String> symbols, Integer limit) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbols != null && symbols.size() > 0) params.put("symbols", String.join(",", symbols));
        if (limit != null) params.put("limit", limit.toString());
        String jsonResponse = httpClient.publicGet("public/orderbook", params);
        return adapter.mapFromJson(jsonResponse, OrderBook.class);
    }

    @Override
    public OrderBook getOrderBook(String symbol, Integer limit) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (limit != null) params.put("limit", limit.toString());
        String jsonResponse = httpClient.publicGet(String.format("public/orderbook/%s", symbol), params);
        return adapter.objectFromJson(jsonResponse, OrderBook.class);
    }

    public OrderBook getMarketDepth(String symbol, Integer volume) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("volume", volume.toString());
        String jsonResponse = httpClient.publicGet(String.format("public/orderbook/%s", symbol), params);
        return adapter.objectFromJson(jsonResponse, OrderBook.class);
    }

    @Override
    public Map<String, List<Candle>> getCandles(
    List<String> symbols, 
    Period period, 
    Sort sort, 
    String from, 
    String till,
    Integer limit, 
    Integer offset) 
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbols != null && symbols.size() > 0) params.put("symbols", String.join(",", symbols));
        if (period != null) params.put("period", period.toString());
        if (sort != null) params.put("sort", sort.name());
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        String jsonResponse = httpClient.publicGet("public/candles", params);
        return adapter.listMapFromJson(jsonResponse, Candle.class);
    }


    public Map<String, List<Candle>> getCandles(List<String> symbols, Period period, Pagination pagination) throws CryptomarketSDKException {
        if (pagination == null) pagination = new Pagination.Builder().build();
        return getCandles(
            symbols,
            period,
            pagination.sort,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset);
    }

    @Override
    public List<Candle> getCandlesOfSymbol(
    String symbol, 
    Period period, 
    Sort sort, 
    String from, 
    String till,
    Integer limit, 
    Integer offset) 
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (period != null) params.put("period", period.toString());
        if (sort != null) params.put("sort", sort.name());
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        String jsonResponse = httpClient.publicGet(String.format("public/candles/%s", symbol), params);
        return adapter.listFromJson(jsonResponse, Candle.class);
    }


    public List<Candle> getCandlesOfSymbol(String symbol, Period period, Pagination pagination) throws CryptomarketSDKException {
        if (pagination == null) pagination = new Pagination.Builder().build();
        return getCandlesOfSymbol(
            symbol,
            period,
            pagination.sort,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset);
    }

    // TRADING

    @Override
    public List<Balance> getTradingBalance() throws CryptomarketSDKException {
        String jsonResponse = httpClient.get("trading/balance", null);
        return adapter.listFromJson(jsonResponse, Balance.class);
    }

    @Override
    public List<Order> getActiveOrders(String symbol) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbol != null) params.put("symbol", symbol);
        String jsonResponse = httpClient.get("order", params);
        return adapter.listFromJson(jsonResponse, Order.class);
    }

    @Override
    public Order getActiveOrder(String clientOrderId, Integer wait) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (wait != null) params.put("wait", wait.toString());
        String jsonResponse = httpClient.get(String.format("order/%s", clientOrderId), params);
        return adapter.objectFromJson(jsonResponse, Order.class);
    }

    @Override
    public Order createOrder(
    String symbol, 
    Side side, 
    String quantity, 
    String clientOrderId, 
    OrderType orderType,
    String price, 
    String stopPrice, 
    TimeInForce timeInForce, 
    String expireTime, 
    Boolean strictValidate,
    Boolean postOnly) 
    throws CryptomarketSDKException {
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("symbol", symbol);
        params.put("side", side.toString());
        params.put("quantity", quantity);
        if (orderType != null) params.put("type", orderType.toString());
        if (timeInForce != null) params.put("timeInForce", timeInForce.toString());
        if (price != null) params.put("price", price);
        if (stopPrice != null) params.put("stopPrice", stopPrice);
        if (strictValidate != null) params.put("strictValidate", strictValidate.toString());
        if (postOnly != null) params.put("postOnly", postOnly.toString());
        
        String endpoint = "order";
        String jsonResponse;
        if (clientOrderId != null) {
            endpoint += "/" + clientOrderId;
            jsonResponse = httpClient.put(endpoint, params);
        }
        else {
            jsonResponse = httpClient.post(endpoint, params);
        }
        return adapter.objectFromJson(jsonResponse, Order.class);
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) throws CryptomarketSDKException {
        return createOrder(
            orderRequest.symbol,
            orderRequest.side,
            orderRequest.quantity,
            orderRequest.clientOrderId,
            orderRequest.orderType,
            orderRequest.price,
            orderRequest.stopPrice,
            orderRequest.timeInForce,
            orderRequest.expireTime,
            orderRequest.strictValidate,
            orderRequest.postOnly);
    }

    @Override
    public List<Order> cancelAllOrders() throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        String jsonResponse = httpClient.delete("order", params);
        return adapter.listFromJson(jsonResponse, Order.class);
    }

    @Override
    public Order cancelOrder(String clientOrderId) throws CryptomarketSDKException {
        String jsonResponse = httpClient.delete(String.format("order/%s", clientOrderId), null);
        return adapter.objectFromJson(jsonResponse, Order.class);
    }

    @Override
    public Commission getTradingCommission(String symbol) throws CryptomarketSDKException {
        String jsonResponse = httpClient.get(String.format("trading/fee/%s", symbol), null);
        return adapter.objectFromJson(jsonResponse, Commission.class);
    }

    // TRADING HISTORY

    @Override
    public List<Order> getOrderHistory(String symbol, String from, String till, Integer limit, Integer offset)
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbol != null) params.put("symbol", symbol);
        if (from != null) params.put("from", from);
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        String jsonResponse = httpClient.get("history/order", params);
        return adapter.listFromJson(jsonResponse, Order.class);
    }

    @Override
    public List<Order> getOrderHistory(String symbol, Pagination pagination) throws CryptomarketSDKException {
        if (pagination == null) pagination = new Pagination.Builder().build();
        return getOrderHistory(
            symbol, 
            pagination.from, 
            pagination.till, 
            pagination.limit, 
            pagination.offset);
    }

    @Override
    public List<Order> getOrders(String clientOrderId) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("clientOrderId", clientOrderId);
        String jsonResponse = httpClient.get("history/order", params);
        List<Order> orderList = adapter.listFromJson(jsonResponse, Order.class);
        return orderList;
    }

    @Override
    public List<Trade> getTradingHistory(
    String symbol, 
    Sort sort, 
    By by, 
    String from, 
    String till, 
    Integer limit,
    Integer offset,
    Margin margin) 
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (symbol != null) params.put("symbol", symbol);
        if (sort != null) params.put("sort", sort.toString());
        if (by != null) params.put("by", by.toString());
        if (from != null) params.put("from", from);
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        if (margin != null) params.put("margin", margin.toString());
        String jsonResponse = httpClient.get("history/trades", params);
        return adapter.listFromJson(jsonResponse, Trade.class);
    }

    @Override
    public List<Trade> getTradingHistory(String symbol, Margin margin, Pagination pagination) throws CryptomarketSDKException {
        if (pagination == null) pagination = new Pagination.Builder().build();
        return getTradingHistory(
            symbol,
            pagination.sort,
            pagination.by,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset,
            margin);
    }


    @Override
    public List<Trade> getTradesByOrder(String id) throws CryptomarketSDKException {
        String jsonResponse = httpClient.get(String.format("history/order/%s/trades", id), null);
        return adapter.listFromJson(jsonResponse, Trade.class);
    }

    @Override
    public List<Balance> getAccountBalance() throws CryptomarketSDKException {
        String jsonResponse = httpClient.get("account/balance", null);
        return adapter.listFromJson(jsonResponse, Balance.class);
    }

    @Override
    public Address getDepositCryptoAddress(String currency) throws CryptomarketSDKException {
        String jsonResponse = httpClient.get(String.format("account/crypto/address/%s", currency), null);
        return adapter.objectFromJson(jsonResponse, Address.class);
    }

    @Override
    public Address createDepositCryptoAddress(String currency) throws CryptomarketSDKException {
        String jsonResponse = httpClient.post(String.format("account/crypto/address/%s", currency), null);
        return adapter.objectFromJson(jsonResponse, Address.class);
    }

    @Override
    public List<Address> getLast10DepositCryptoAddresses(String currency) throws CryptomarketSDKException {
        String jsonResponse = httpClient.get(String.format("account/crypto/addresses/%s", currency), null);
        return adapter.listFromJson(jsonResponse, Address.class);
    }

    @Override
    public List<Address> get10UsedCryptoAddresses(String currency) throws CryptomarketSDKException {
        String jsonResponse = httpClient.get(String.format("account/crypto/used-addresses/%s", currency), null);
        return adapter.listFromJson(jsonResponse, Address.class);
    }

    @Override
    public String withdrawCrypto(
    String currency, 
    String amount, 
    String address, 
    String paymentId, 
    Boolean includeFee,
    Boolean autoCommit) 
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("currency", currency);
        params.put("amount", amount);
        params.put("address", address);
        if (paymentId != null) params.put("paymentId", paymentId);
        if (includeFee != null) params.put("includeFee", includeFee.toString());
        if (autoCommit != null) params.put("autoCommit", autoCommit.toString());
        String jsonResponse = httpClient.post("account/crypto/withdraw", params);
        return adapter.objectFromJsonValue(jsonResponse, "id", String.class);
    }

    @Override
    public List<String> transferConvert(String fromCurrency, String toCurrency, String amount)
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("fromCurrency", fromCurrency);
        params.put("toCurrency", toCurrency);
        params.put("amount", amount);
        String jsonResponse = httpClient.post("account/crypto/withdraw", params);
        return adapter.listFromJsonValue(jsonResponse, "result", String.class);
    }

    @Override
    public boolean commitWithdrawCrypto(String transactionId) throws CryptomarketSDKException {
        String jsonResponse = httpClient.put(String.format("account/crypto/withdraw/%s", transactionId), null);
        return adapter.objectFromJsonValue(jsonResponse, "result", boolean.class);
    }

    @Override
    public boolean rollbackWithdrawCrypto(String transactionId) throws CryptomarketSDKException {
        String jsonResponse = httpClient.delete(String.format("account/crypto/withdraw/%s", transactionId), null);
        return adapter.objectFromJsonValue(jsonResponse, "result", boolean.class);
    }

    @Override
    public String getEstimatesWithdrawFee(String currency, String amount) throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("currency", currency);
        params.put("amount", amount);
        String jsonResponse = httpClient.get("account/crypto/estimate-withdraw", params);
        return adapter.objectFromJsonValue(jsonResponse, "fee", String.class);
    }

    @Override
    public boolean checkIfCryptoAddressIsMine(String address) throws CryptomarketSDKException {
        String jsonResponse = httpClient.get(String.format("account/crypto/is-mine/%s", address), null);
        return adapter.objectFromJsonValue(jsonResponse, "result", boolean.class);
    }

    
    private String transferBetweenTradingAndAccountBalance(String currency, String amount, TransferType transferType)
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("currency", currency);
        params.put("amount", amount);
        params.put("type", transferType.toString());
        String jsonResponse = httpClient.post("account/transfer", params);
        return adapter.objectFromJsonValue(jsonResponse, "id", String.class);
    }

    @Override
    public String transferMoneyFromTradingBalanceToAccountBalance(String currency, String amount) throws CryptomarketSDKException {
        return transferBetweenTradingAndAccountBalance(currency, amount, TransferType.EXCHANGE_TO_BANK);
    }

    
    @Override
    public String transferMoneyFromAccountBalanceToTradingBalance(String currency, String amount) throws CryptomarketSDKException {
        return transferBetweenTradingAndAccountBalance(currency, amount, TransferType.BANK_TO_EXCHANGE);
    }


    @Override
    public String transferMonyToAnotherUser(String currency, String amount, TransferBy transferBy, String identifier)
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("currency", currency);
        params.put("amount", amount);
        params.put("by", transferBy.toString());
        params.put("identifier", identifier);
        String jsonResponse = httpClient.post("account/transfer/internal", params);
        return adapter.objectFromJsonValue(jsonResponse, "result", String.class);
    }

    @Override
    public List<Transaction> getTransactionHistory(
    String currency, 
    Sort sort, 
    By by, 
    String from,
    String till, 
    Integer limit, 
    Integer offset,
    Boolean showSenders) 
    throws CryptomarketSDKException {
        Map<String, String> params = new HashMap<String, String>();
        if (currency != null) params.put("currency", currency);
        if (sort != null) params.put("sort", sort.toString());
        if (by != null) params.put("by", by.toString());
        if (from != null) params.put("from", from);
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        if (showSenders != null) params.put("showSenders", showSenders.toString());
        String jsonResponse = httpClient.get("account/transactions", params);
        return adapter.listFromJson(jsonResponse, Transaction.class);
    }


    @Override
    public List<Transaction> getTransactionHistory(String symbol, Pagination pagination, Boolean showSenders) throws CryptomarketSDKException {
        if (pagination == null) pagination = new Pagination.Builder().build();
        return getTransactionHistory(
            symbol,
            pagination.sort,
            pagination.by,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset,
            showSenders);
    }

    @Override
    public Transaction getTransaction(String id) throws CryptomarketSDKException {
        String jsonResponse = httpClient.get(String.format("account/transactions/%s", id), null);
        return adapter.objectFromJson(jsonResponse, Transaction.class);
    }
}
