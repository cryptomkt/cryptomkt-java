package com.cryptomarket.sdk.websocket.interceptor;

import java.util.List;

import com.cryptomarket.sdk.Callback;
import com.cryptomarket.sdk.exceptions.CryptomarketAPIException;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.WSJsonResponse;

public class InterceptorFactory {
    public static <T> Interceptor newOfWSResponseObject(Callback<T> callback, Class<T> cls) {
        return new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    T result = adapter.objectFromValue(response.getResult(), cls);
                    callback.resolve(result);
                }
            }
        };
    }

    public static <T> Interceptor newOfWSResponseList(Callback<List<T>> callback, Class<T> cls) {
        return new Interceptor() {
            @Override
            public void makeCall(WSJsonResponse response) {
                ErrorBody error = response.getError();
                if (error != null) {
                    callback.reject(new CryptomarketAPIException(error));
                } else {
                    List<T> result = adapter.listFromValue(response.getResult(), cls);
                    callback.resolve(result);
                }
            }
        };
    }
}
