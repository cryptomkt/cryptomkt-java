package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.params.Pagination;
import com.cryptomarket.params.Sort;
import com.cryptomarket.sdk.Callback;
import com.cryptomarket.sdk.exceptions.CryptomarketAPIException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.Transaction;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;

public class CryptomarketWSAccountClientImpl extends AuthClient implements CryptomarketWSAccountClient {

    public CryptomarketWSAccountClientImpl(String apiKey, String apiSecret) throws IOException {
        super("wss://api.exchange.cryptomkt.com/api/2/ws/account", apiKey, apiSecret);
        Map<String, String> subsKeys = this.getSubscritpionKeys();
        // transactions
        subsKeys.put("unsubscribeTransactions","transactions");
        subsKeys.put("subscribeTransactions","transactions");
        subsKeys.put("updateTransaction","transactions");
        //balance
        subsKeys.put("unsubscribeBalance","balance");
        subsKeys.put("subscribeBalance","balance");
        subsKeys.put("balance","balance");
    }

    @Override
    public void getAccountBalance(Callback<List<Balance>> callback) {
        Map<String, Object> params = new HashMap<>();
        Interceptor interceptor = (callback == null) ? null
                : InterceptorFactory.newOfWSResponseList(callback, Balance.class);
        sendById("getBalance", params, interceptor);
    }

    private void getTransactionList(String method, String currency, Sort sort, String from, String till, Integer limit,
    Integer offset, Boolean showSenders, Callback<List<Transaction>> callback) {
        Map<String, Object> params = new HashMap<>();
        if (currency != null) params.put("currency", currency);
        if (sort != null) params.put("sort", sort.name());
        if (from != null) params.put("from", from);
        if (till != null) params.put("till", till);
        if (limit != null) params.put("limit", limit);
        if (offset != null) params.put("offset", offset);
        if (showSenders != null) params.put("showSenders", showSenders.toString());
    
        Interceptor interceptor = (callback == null) ? null
                : InterceptorFactory.newOfWSResponseList(callback, Transaction.class);
        sendById(method, params, interceptor);
    }

    @Override
    public void findTransactions(String currency, Sort sort, String from, String till, Integer limit,
            Integer offset, Boolean showSenders, Callback<List<Transaction>> callback) {
        getTransactionList("findTransactions", currency, sort, from, till, limit, offset, showSenders, callback);
    }

    @Override
    public void findTransactions(String currency, Pagination pagination, Boolean showSenders, Callback<List<Transaction>> callback) {
        if (pagination == null) pagination = new Pagination.Builder().build();
        findTransactions(
            currency, 
            pagination.sort,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset,
            showSenders,
            callback);
    }

    @Override
    public void loadTransactions(String currency, Sort sort, String from, String till, Integer limit,
            Integer offset, Boolean showSenders, Callback<List<Transaction>> callback) {
        getTransactionList("loadTransactions", currency, sort, from, till, limit, offset, showSenders, callback);

    }

    @Override
    public void loadTransactions(String currency, Pagination pagination, Boolean showSenders, Callback<List<Transaction>> callback) {
        if (pagination == null) pagination = new Pagination.Builder().build();
        loadTransactions(
            currency, 
            pagination.sort,
            pagination.from,
            pagination.till,
            pagination.limit,
            pagination.offset,
            showSenders,
            callback);

    }

    @Override
    public void subscribeToTransactions(Callback<Transaction> callback, Callback<Boolean> resultCallback) {
        Map<String, Object> params = new HashMap<>();
        Interceptor interceptor = new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    Transaction transaction = adapter.objectFromValue(response.getParams(), Transaction.class);
                    callback.resolve(transaction);
                }
            }
        };
        Interceptor resultInterceptor = (resultCallback == null) ? null
                : InterceptorFactory.newOfWSResponseObject(resultCallback, Boolean.class);
        sendSubscription("subscribeTransactions", params, interceptor, resultInterceptor);
    }

    @Override
    public void unsubscribeToTransactions(Callback<Boolean> callback) {
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Boolean.class);
        sendUnsubscription("unsubscribeTicker", null, interceptor);
    }

    @Override
    public void subscribeToBalance(Callback<List<Balance>> callback, Callback<Boolean> resultCallback) {
        Map<String, Object> params = new HashMap<>();
        Interceptor interceptor = new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    List<Balance> balances = adapter.listFromValue(response.getParams(), Balance.class);
                    callback.resolve(balances);
                }
            }
        };
        Interceptor resultInterceptor = (resultCallback == null) ? null
                : InterceptorFactory.newOfWSResponseObject(resultCallback, Boolean.class);
        sendSubscription("subscribeBalance", params, interceptor, resultInterceptor);
    }

    @Override
    public void unsubscribeToBalance(Callback<Boolean> callback) {
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Boolean.class);
        sendUnsubscription("unsubscribeBalance", null, interceptor);
    }
}
