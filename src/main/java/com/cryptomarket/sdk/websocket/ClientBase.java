package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.cryptomarket.sdk.Adapter;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorCache;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

public class ClientBase implements WSHandler {
  InterceptorCache interceptorCache = new InterceptorCache();
  OrderbookCache OBCache = new OrderbookCache();
  public Adapter adapter = new Adapter();
  protected WebSocketConnection websocket;
  private Map<String, String> subscriptionKeys;
  protected Runnable onConnectR = () -> {
  };
  private Consumer<String> onCloseC = (reason) -> {
  };
  private Consumer<Throwable> onFailureC = (exception) -> {
  };

  static class Payload {
    String method;
    @Json(name = "ch")
    String channel;
    Map<String, Object> params;
    int id;

    public Payload(String method, Map<String, Object> params) {
      this.method = method;
      this.params = params;
    }

    public Payload(String method, String channel, Map<String, Object> params) {
      this.method = method;
      this.channel = channel;
      this.params = params;
    }
  }

  JsonAdapter<Payload> payloadAdapter;

  protected ClientBase(String url) {
    Moshi moshi = new Moshi.Builder().build();
    payloadAdapter = moshi.adapter(Payload.class);
    this.subscriptionKeys = new HashMap<String, String>();
    websocket = new WebSocketConnection(this, url);
  }

  protected Map<String, String> getSubscritpionKeys() {
    return this.subscriptionKeys;
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
    sendById(method, params, interceptor, 1);
  }

  protected void sendById(String method, Map<String, Object> params, Interceptor interceptor, Integer callCount) {
    Payload payload = new Payload(method, params);
    if (interceptor != null) {
      Integer id = interceptorCache.saveInterceptor(interceptor, callCount);
      payload.id = id;
    }
    String json = payloadAdapter.toJson(payload);
    websocket.send(json);
  }

  @Override
  public void handle(String json) throws CryptomarketSDKException {
    WSJsonResponse response = adapter.objectFromJson(json, WSJsonResponse.class);
    if (response.getId() != null) {
      handleResponse(response);
    } else if (response.getMethod() != null) {
      handleNotification(response);
    }
  }

  protected void handleNotification(WSJsonResponse response) {
    String key = buildKey(response.getMethod());
    Interceptor interceptor = interceptorCache.getSubscriptionInterceptor(key);
    if (interceptor != null)
      interceptor.makeCall(response);
  }

  protected void handleResponse(WSJsonResponse response) {
    var interceptor = interceptorCache.getInterceptor(response.getId());
    if (interceptor.isEmpty()) {
      return;
    }
    interceptor.get().makeCall(response);
  }

  protected String buildKey(String method) {
    if (subscriptionKeys.containsKey(method)) {
      return this.subscriptionKeys.get(method);
    }
    return "suscription";
  }

  protected String buildKey(String method, Map<String, Object> params) {
    return buildKey(method);
  }

  @Override
  public void close() {
    websocket.close();
  }

  @Override
  public void connect() throws IOException {
    websocket.run();
  }

  @Override
  public void onConnect(Runnable onConnect) {
    this.onConnectR = onConnect;
  }

  @Override
  public Runnable getOnConnect() {
    return onConnectR;
  }

  @Override
  public void onClose(Consumer<String> onClose) {
    this.onCloseC = onClose;
  }

  @Override
  public Consumer<String> getOnClose() {
    return onCloseC;
  }

  @Override
  public void onFailure(Consumer<Throwable> onFailure) {
    this.onFailureC = onFailure;
  }

  @Override
  public Consumer<Throwable> getOnFailure() {
    return onFailureC;
  }

}
