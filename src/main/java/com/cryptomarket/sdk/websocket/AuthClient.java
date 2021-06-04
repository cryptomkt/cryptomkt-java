package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.cryptomarket.sdk.Callback;
import com.cryptomarket.sdk.HMAC;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;

public class AuthClient extends ClientBase {
    private String apiSecret;
    private String apiKey;

    public AuthClient(String url, String apiKey, String apiSecret) throws IOException {
        super(url);
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Override
    public void onOpen() {
        CryptomarketWS client = this;
        this.authenticate(new Callback<Boolean>() {
            @Override
            public void resolve(Boolean result) {
                client.onConnect();
            }

            @Override
            public void reject(Throwable exception) {
                client.onFailure(exception);
            }
        });
    }

    @Override
    public void onConnect() {}

    public void authenticate(Callback<Boolean> callback) {
        Map<String, Object> params = new HashMap<>();
        String nonce = randomString();

        params.put("algo", "HS256");
        params.put("pKey", apiKey);
        params.put("nonce", nonce);
        params.put("signature", HMAC.sign(apiSecret, nonce));
        
        Interceptor interceptor = 
        (callback == null) ? 
            null : 
            InterceptorFactory.newOfWSResponseObject(callback, Boolean.class);
        sendById("login", params, interceptor);
    }

    private String randomString() {
        int leftLimit = 48, rightLimit = 122, targetStringLength = 30;
        return new Random().ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}