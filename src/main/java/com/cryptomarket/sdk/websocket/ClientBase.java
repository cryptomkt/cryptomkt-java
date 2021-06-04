package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.Map;

import com.cryptomarket.sdk.Adapter;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorCache;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

public class ClientBase implements Handler {
    InterceptorCache interceptorCache = new InterceptorCache();
    OrderbookCache OBCache = new OrderbookCache();
    public Adapter adapter = new Adapter();
    private WebSocketConnection websocket;

    static class Payload {
        String method;
        Map<String, Object> params;
        int id;

        public Payload(String method, Map<String, Object> params) {
            this.method = method;
            this.params = params;
        }
    }

    JsonAdapter<Payload> payloadAdapter;

    protected ClientBase(String url) {
        Moshi moshi = new Moshi.Builder().build();
        payloadAdapter = moshi.adapter(Payload.class);
        websocket = new WebSocketConnection(this, url);
    }

    protected void sendSubscription(String method, Map<String, Object> params, Interceptor feedInterceptor,
            Interceptor resultInterceptor) {
        String key = buildKey(method, params);
        interceptorCache.storeSubscriptionInterceptor(key, feedInterceptor);
        sendById(method, params, resultInterceptor);
    }

    protected void sendUnsubscription(String method, Map<String, Object> params, Interceptor interceptor) {
        String key = buildKey(method, params);
        interceptorCache.deleteSubscriptionInterceptor(key);
        sendById(method, params, interceptor);
    }

    protected void sendById(String method, Map<String, Object> params, Interceptor interceptor) {
        Payload payload = new Payload(method, params);
        if (interceptor != null) {
            Integer id = interceptorCache.storeInterceptor(interceptor);
            payload.id = id;
        }
        String json = payloadAdapter.toJson(payload);
        websocket.send(json);
    }

    @Override
    public void handle(String json) throws CryptomarketSDKException {
        WSJsonResponse response = adapter.objectFromJson(json, WSJsonResponse.class);
        if (response.getMethod() != null) handleNotification(response);
        else if (response.getId() != null) handleResponse(response);
    }

    protected void handleNotification(WSJsonResponse response) {
        String key = "subscription";
        Interceptor interceptor = interceptorCache.getSubscriptionInterceptor(key);
        if (interceptor != null) interceptor.makeCall(response);
    }

    protected void handleResponse(WSJsonResponse response) {
        Interceptor interceptor = interceptorCache.popInterceptor(response.getId());
        if (interceptor != null) interceptor.makeCall(response);
    }

    protected String buildKey(String method, Map<String, Object> params) {
        return "subscription";
    }

    @Override
    public void onClose(String reason) {}

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void close() {
        websocket.close();
    }

    @Override
    public void onOpen() {
        this.onConnect();
    }

    @Override
    public void onConnect() {}

    @Override
    public void connect() throws IOException {
        websocket.run();
    }
}
