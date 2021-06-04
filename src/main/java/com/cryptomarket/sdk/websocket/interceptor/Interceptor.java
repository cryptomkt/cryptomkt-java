package com.cryptomarket.sdk.websocket.interceptor;

import com.cryptomarket.sdk.Adapter;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.WSJsonResponse;

public abstract class Interceptor {
    public Adapter adapter = new Adapter();

    public void makeCall(WSJsonResponse response) {}

    public void makeCall(OrderBook orderBook) {}

    public void makeCall(ErrorBody error) {}
}
