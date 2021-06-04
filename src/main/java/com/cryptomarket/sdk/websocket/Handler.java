package com.cryptomarket.sdk.websocket;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;

public interface Handler extends CryptomarketWS {
    public void handle(String json) throws CryptomarketSDKException;
    public void onOpen();
}
