package com.cryptomkt.api;

import com.cryptomkt.api.utils.Callable;

import java.util.List;

public interface Socket {

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
     * onBalance is triggered every time an update to the user balance arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable a class implementing a call method
     */
    void onBalance(Callable callable);

    /**
     * onOpenOrders is triggered every time an update to the user open orders arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable a class implementing a call method
     */
    void onOpenOrders(Callable callable);

    /**
     * onHistoricalOrders is triggered every time an update to the user historicalOrders arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable a class implementing a call method
     */
    void onHistoricalOrders(Callable callable);

    /**
     * onOperated is triggered every time an update to the user operated trades arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable a class implementing a call method
     */
    void onOperated(Callable callable);

    /**
     * onOpenBook is triggered every time an update to a subscribed market arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable a class implementing a call method
     */
    void onOpenBook(Callable callable);

    /**
     * onHistoricalBook is triggered every time an update to the historical open book arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable a class implementing a call method
     */
    void onHistoricalBook(Callable callable);

    /**
     * onCandles is triggered every time an update to a subscribed market arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable a class implementing a call method
     */
    void onCandles(Callable callable);

    /**
     * onTicker is triggered every time an update from the markets arrives, then calling the call method in the
     * provided class. Is a non blocking method, supports lambda notation.
     *
     * @param callable
     */
    void onTicker(Callable callable);
}

