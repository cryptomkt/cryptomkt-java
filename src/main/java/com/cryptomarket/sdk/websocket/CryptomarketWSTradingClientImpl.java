package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.params.OrderRequest;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.TimeInForce;
import com.cryptomarket.sdk.Callback;
import com.cryptomarket.sdk.exceptions.CryptomarketAPIException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;
import com.squareup.moshi.JsonDataException;

public class CryptomarketWSTradingClientImpl extends AuthClient implements CryptomarketWSTradingClient {
    
    public CryptomarketWSTradingClientImpl(String apiKey, String apiSecret) throws IOException {
        super("wss://api.exchange.cryptomkt.com/api/2/ws/trading", apiKey, apiSecret);
    }

    @Override
    public void createOrder(OrderRequest orderRequest, Callback<Report> callback) {
        createOrder(
            orderRequest.clientOrderId,
            orderRequest.symbol,
            orderRequest.side,
            orderRequest.quantity,
            orderRequest.orderType,
            orderRequest.price,
            orderRequest.stopPrice,
            orderRequest.timeInForce,
            orderRequest.expireTime,
            orderRequest.strictValidate,
            orderRequest.postOnly,
            callback);
    }

    @Override
    public void createOrder(String clientOrderId, String symbol, Side side, String quantity, OrderType orderType,
            String price, String stopPrice, TimeInForce timeInForce, String expireTime, Boolean strictValidate,
            Boolean postOnly, Callback<Report> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientOrderId", clientOrderId);
        params.put("symbol", symbol);
        params.put("side", side.toString());
        params.put("quantity", quantity);
        if (orderType != null) params.put("orderType", orderType.toString());
        params.put("price", price);
        params.put("stopPirce", stopPrice);
        if (timeInForce != null) params.put("timeInForce", timeInForce.toString());
        params.put("expireTime", expireTime);
        if (strictValidate != null) params.put("strictValidate", strictValidate.toString());
        if (postOnly != null) params.put("postOnly", postOnly.toString());
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Report.class);
        sendById("newOrder", params, interceptor);
    }

    @Override
    public void cancelOrder(String clientOrderId, Callback<Report> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientOrderId", clientOrderId);
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Report.class);
        sendById("cancelOrder", params, interceptor);
    }

    @Override
    public void replaceOrder(String clientOrderId, String requestClientId, String quantity, String price,
            Boolean strictValidate, Callback<Report> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientOrderId", clientOrderId);
        params.put("requestClientId", requestClientId);
        params.put("quantity", quantity);
        params.put("price", price);
        if (strictValidate != null) params.put("strictValidate", strictValidate.toString());
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Report.class);
        sendById("cancelReplaceOrder", params, interceptor);
    }

    @Override
    public void getActiveOrders(Callback<List<Report>> callback) {
        Map<String, Object> params = new HashMap<>();
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseList(callback, Report.class);
        sendById("getOrders", params, interceptor);
    }

    @Override
    public void getTradingBalance(Callback<List<Balance>> callback) {
        Map<String, Object> params = new HashMap<>();
        Interceptor interceptor = 
            (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseList(callback, Balance.class);
        sendById("getTradingBalance", params, interceptor);
    }


    @Override
    public void subscribeToReports(Callback<Report> callback, Callback<Boolean> resultCallback) {
        Map<String, Object> params = new HashMap<>();
        Interceptor interceptor = new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    try {
                        List<Report> reports = adapter.listFromValue(response.getParams(), Report.class);
                        if (reports != null) {
                            reports.forEach(report -> callback.resolve(report));
                        }
                    } catch (JsonDataException e) {
                        Report report = adapter.objectFromValue(response.getParams(), Report.class);
                        callback.resolve(report);
                    }
                }
            }
        };
        Interceptor resultInterceptor = 
            (resultCallback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(resultCallback, Boolean.class);
        sendSubscription("subscribeReports", params, interceptor, resultInterceptor);
    }
}
