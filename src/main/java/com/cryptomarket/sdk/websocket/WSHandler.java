package com.cryptomarket.sdk.websocket;

import java.util.function.Consumer;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;

public interface WSHandler extends CryptomarketWS {
    public void handle(String json) throws CryptomarketSDKException;

    /**
     * get the consumer called on failure with a throwable when the websocket fails
     * 
     * @return the throwable consumer
     */
    public Consumer<Throwable> getOnFailure();

    /**
     * get the consumer called on close with the reason as its string parameter
     * 
     * @return the string consumer
     */
    public Consumer<String> getOnClose();

    /**
     * get the runnable called on websocket connection
     * 
     * @return the runnable called on websocket connection
     */
    public Runnable getOnConnect();

}
