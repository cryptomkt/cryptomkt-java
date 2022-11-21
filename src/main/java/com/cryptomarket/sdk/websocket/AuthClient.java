package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.cryptomarket.sdk.HMAC;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;

public class AuthClient extends ClientBase {
  private String apiSecret;
  private String apiKey;
  private Integer window;

  public AuthClient(String url, String apiKey, String apiSecret, Integer window) throws IOException {
    super(url);
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    this.window = window;
  }

  public AuthClient(String url, String apiKey, String apiSecret) throws IOException {
    this(url, apiKey, apiSecret, 0);
  }

  @Override
  public void onOpen() {
    CryptomarketWS client = this;
    this.authenticate((success, exception) -> {
      if (exception != null) {
        client.onFailure(exception);
        return;
      }
      if (!success) {
        client.onFailure(new CryptomarketSDKException("authorization failed: unsuccessful authorization request"));
      }
      client.onConnect();
    });
  }

  @Override
  public void onConnect() {
  }

  public void authenticate(BiConsumer<Boolean, CryptomarketSDKException> resultBiConsumer) {
    Map<String, Object> params = new HashMap<>();

    Long timestamp = System.currentTimeMillis();
    String strTimestamp = String.format("%d", timestamp);
    params.put("type", "HS256");
    params.put("api_key", apiKey);
    params.put("timestamp", timestamp);
    if (window != 0)
      params.put("window", window.toString());
    params.put("signature", HMAC.sign(apiSecret, (window != 0) ? strTimestamp + window : strTimestamp));

    Interceptor interceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Boolean.class);
    sendById("login", params, interceptor);
  }
}