package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.params.By;
import com.cryptomarket.params.Pagination;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Sort;
import com.cryptomarket.sdk.Adapter;
import com.cryptomarket.sdk.Callback;
import com.cryptomarket.sdk.exceptions.CryptomarketAPIException;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;
import com.squareup.moshi.JsonDataException;

public class CryptomarketWSPublicClientImpl extends ClientBase implements CryptomarketWSPublicClient {
    OrderbookCache OBCache = new OrderbookCache();
    protected Adapter adapter = new Adapter();

    static class SP {
        String symbol = "";
        String period = "";
    }

    static class PublicTradeListInData {
        List<PublicTrade> data;
    }

    static class CandleListInData {
        List<Candle> data;
    }

    public CryptomarketWSPublicClientImpl() throws IOException {
        super("wss://api.exchange.cryptomkt.com/api/2/ws/public");
    }

    @Override
    protected void handleNotification(WSJsonResponse response) {
        String method = response.getMethod();
        SP sp;
        try {
            sp = adapter.objectFromValue(response.getParams(), SP.class);
        } catch (JsonDataException e) { // if is a list instead, as in activeOrders
            sp = new SP();
        }
        String key = buildKey(method, sp.symbol, sp.period);
        Interceptor interceptor = interceptorCache.getSubscriptionInterceptor(key);
        if (MethodMap.isOrderbookFeed(method)) {
            OBCache.update(method, key, response);
            if (OBCache.orderbookBroken(key)) {
                OBCache.waitOrderbook(key);
                Map<String, Object> params = new HashMap<>();
                params.put("symbol", sp.symbol);
                this.sendById("subscribeOrderbook", params, null);
                return;
            }
            if (OBCache.orderbookWaiting(key)) return;
            if (interceptor != null) interceptor.makeCall(OBCache.getOrderbook(key));
            return;
        }
        if (interceptor != null) interceptor.makeCall(response);
    }

    @Override
    protected String buildKey(String method, Map<String, Object> params) {
        String symbol = params.containsKey("symbol") ? String.valueOf(params.get("symbol")) : "";
        String period = params.containsKey("period") ? String.valueOf(params.get("period")) : "";
        return buildKey(method, symbol, period);
    }

    private String buildKey(String method, String symbol, String period) {
        String methodKey = MethodMap.getMethodKey(method);
        if (method.equals("report") || method.equals("activeOrders")) return methodKey + "::";
        String key = methodKey + ":" + symbol + ":" + period;
        return key.toUpperCase();
    }

    @Override
    public void getCurrencies(Callback<List<Currency>> callback) {
        Map<String, Object> params = new HashMap<>();
        sendById(
            "getCurrencies", 
            params, 
            InterceptorFactory.newOfWSResponseList(callback, Currency.class));
    }

    @Override
    public void getCurrency(String currency, Callback<Currency> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("currency", currency);
        sendById("getCurrency", params, InterceptorFactory.newOfWSResponseObject(callback, Currency.class));
    }

    @Override
    public void getSymbols(Callback<List<Symbol>> callback) {
        Map<String, Object> params = new HashMap<>();
        sendById("getSymbols", params, InterceptorFactory.newOfWSResponseList(callback, Symbol.class));
    }

    @Override
    public void getSymbol(String symbol, Callback<Symbol> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        sendById("getSymbol", params, InterceptorFactory.newOfWSResponseObject(callback, Symbol.class));
    }

    @Override
    public void subscribeToTicker(String symbol, Callback<Ticker> callback, Callback<Boolean> resultCallback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        Interceptor interceptor = new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    Ticker ticker = adapter.objectFromValue(response.getParams(), Ticker.class);
                    callback.resolve(ticker);
                }
            }
        };
        Interceptor resultInterceptor = 
            (resultCallback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(resultCallback, Boolean.class);

        sendSubscription("subscribeTicker", params, interceptor, resultInterceptor);
    }

    @Override
    public void unsubscribeToTicker(String symbol, Callback<Boolean> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Boolean.class);
        sendUnsubscription("unsubscribeTicker", params, interceptor);
    }

    @Override
    public void subscribeToOrderbook(String symbol, Callback<OrderBook> callback, Callback<Boolean> resultCallback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        Interceptor interceptor = new Interceptor() {
            @Override
            public void makeCall(OrderBook orderBook) {
                callback.resolve(orderBook);
            }
        };
        Interceptor resultInterceptor = 
            (resultCallback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(resultCallback, Boolean.class);
        sendSubscription("subscribeOrderbook", params, interceptor, resultInterceptor);

    }

    @Override
    public void unsubscribeToOrderbook(String symbol, Callback<Boolean> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Boolean.class);
        sendUnsubscription("unsubscribeOrderbook", params, interceptor);
    }

    @Override
    public void subscribeToTrades(String symbol, Integer limit, Callback<List<PublicTrade>> callback, Callback<Boolean> resultCallback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        if (limit != null) params.put("limit", limit.toString());
        Interceptor interceptor = new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    PublicTradeListInData trades = adapter.objectFromValue(response.getParams(), PublicTradeListInData.class);
                    callback.resolve(trades.data);
                }
            }
        };
        Interceptor resultInterceptor = 
            (resultCallback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(resultCallback, Boolean.class);
        sendSubscription("subscribeTrades", params, interceptor, resultInterceptor);
    }

    @Override
    public void unsubscribeToTrades(String symbol, Callback<Boolean> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Boolean.class);
        sendUnsubscription("unsubscribeTrades", params, interceptor);
    }

    @Override
    public void getTrades(String symbol, Sort sort, By by, String from, String till, Integer limit, Integer offset,
            Callback<List<PublicTrade>> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        if (sort != null) params.put("sort", sort.toString());
        if (by != null) params.put("by", by.toString());
        if (from != null) params.put("from", from);
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit.toString());
        if (offset != null) params.put("offset", offset.toString());
        sendById("getTrades", params, new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    PublicTradeListInData trades = adapter.objectFromValue(response.getResult(), PublicTradeListInData.class);
                    callback.resolve(trades.data);
                }
            }
        });
    }

    @Override
    public void getTrades(String symbol, Pagination pagination, Callback<List<PublicTrade>> callback) {
        if (pagination == null) pagination = new Pagination.Builder().build();
        getTrades(
            symbol,
            pagination.sort,
            pagination.by,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset,
            callback);
    }

    @Override
    public void subscribeToCandles(String symbol, Period period, Integer limit, Callback<List<Candle>> callback,
            Callback<Boolean> resultCallback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        if (period == null) period = Period._30_MINUTES;
        params.put("period", period.toString());
        if (limit != null) params.put("limit", limit.toString());
        Interceptor interceptor = new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    CandleListInData candles = adapter.objectFromValue(response.getParams(), CandleListInData.class);
                    callback.resolve(candles.data);
                }
            }
        };
        Interceptor resultInterceptor = 
            (resultCallback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(resultCallback, Boolean.class);
        sendSubscription("subscribeCandles", params, interceptor, resultInterceptor);
    }

    @Override
    public void unsubscribeToCandles(String symbol, Period period, Callback<Boolean> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        if (period == null) period = Period._30_MINUTES;
        params.put("period", period.toString());
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Boolean.class);
        sendUnsubscription("unsubscribeCandles", params, interceptor);
    }
}
