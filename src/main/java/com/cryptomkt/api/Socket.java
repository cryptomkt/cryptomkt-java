package com.cryptomkt.api;

import org.json.JSONObject;

import java.io.Closeable;
import java.util.List;
import java.util.function.Consumer;

public interface Socket extends Closeable {

    /**
     * subscribe to start receiving candle data and open book data from a market pair
     *
     * @param marketPair
     */
    void subscribe(String marketPair);

    /**
     * subscribe to a many markets at the same time
     *
     * @see #subscribe(String)
     */
    void subscribe(List<String> marketPairs);

    /**
     * unsubscribe to stop receiving data from a market
     *
     * @param marketPairs
     */
    void unsubscribe(String marketPairs);

    /**
     * unsubscribe to may markets at the same time
     *
     * @see #unsubscribe(String)
     */
    void unsubscribe(List<String> marketPairs);

    /**
     * onBalance is triggered every time an update to the user balance arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer a class implementing a call method
     */
    void onBalance(Consumer<JSONObject> consumer);

    /**
     * onOpenOrders is triggered every time an update to the user open orders arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer a class implementing a call method
     */
    void onOpenOrders(Consumer<JSONObject> consumer);

    /**
     * onHistoricalOrders is triggered every time an update to the user historicalOrders arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer a class implementing a call method
     */
    void onHistoricalOrders(Consumer<JSONObject> consumer);

    /**
     * onOperated is triggered every time an update to the user operated trades arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer a class implementing a call method
     */
    void onOperated(Consumer<JSONObject> consumer);

    /**
     * onOpenBook is triggered every time an update to a subscribed market arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer a class implementing a call method
     */
    void onOpenBook(Consumer<JSONObject> consumer);

    /**
     * onHistoricalBook is triggered every time an update to the historical open book arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer a class implementing a call method
     */
    void onHistoricalBook(Consumer<JSONObject> consumer);

    /**
     * onCandles is triggered every time an update to a subscribed market arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer a class implementing a call method
     */
    void onCandles(Consumer<JSONObject> consumer);

    /**
     * onTicker is triggered every time an update from the markets arrives, then calling the accept method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param consumer
     */
    void onTicker(Consumer<JSONObject> consumer);
}

