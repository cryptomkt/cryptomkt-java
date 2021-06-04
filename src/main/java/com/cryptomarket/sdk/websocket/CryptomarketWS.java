package com.cryptomarket.sdk.websocket;

import java.io.IOException;

public interface CryptomarketWS {
    /**
     * connect the client to the exchange
     */
    public void connect() throws IOException;

    /**
     * method called when the client connects with the exchange.
     * if is not a public client, the also authenticates before the call.
     */
    public void onConnect();

    /**
     * called when the websocket connection is closed. Override this method if needed
     * @param reason the reason for the closing
     */
    public void onClose(String reason);

    /**
     * called when the websocket has an error. Override this method if needed
     * also called when the authorization fails.
     * @param t
     */
    public void onFailure(Throwable t);

    /**
     * closes the websocket connection
     */
    public void close();
}
