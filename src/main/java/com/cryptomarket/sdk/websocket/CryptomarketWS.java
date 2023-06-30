package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.function.Consumer;

public interface CryptomarketWS {
    /**
     * connect the client to the exchange
     */
    public void connect() throws IOException;

    /**
     * sets a runnable to be called on websocket connection with the exchange
     * 
     * @param onConnect the runnable
     */
    public void onConnect(Runnable onConnect);

    /**
     * sets a consumer to be called when the websocket connection is closed. called
     * with a string with the reason of the closing
     * 
     * @param onClose a string consumer called with the reason for the closing
     */
    public void onClose(Consumer<String> onClose);

    /**
     * sets a consumer to be called when the websocket connection (or
     * authentication) fails. Called with a throwable
     * 
     * @param onFailure the consumer called with the throwable responsible of the
     *                  failure
     */
    public void onFailure(Consumer<Throwable> onFailure);

    /**
     * closes the websocket connection
     */
    public void close();
}
