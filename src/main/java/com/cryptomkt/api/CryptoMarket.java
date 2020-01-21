package com.cryptomkt.api;


import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.exception.CryptoMarketException;

import java.io.IOException;

public interface CryptoMarket {

    /* Public Endpoints */

    MarketsResponse getMarkets() throws IOException, CryptoMarketException;

    TickersResponse getTickers() throws  IOException, CryptoMarketException;
    TickersResponse getTickers(String market) throws  IOException, CryptoMarketException;

    BookResponse getBook(String market, String type) throws  IOException, CryptoMarketException;
    BookResponse getBook(String market, String type, int page) throws  IOException, CryptoMarketException;
    BookResponse getBook(String market, String type, int page, int limit) throws  IOException, CryptoMarketException;

    TradeResponse getTrades(String market) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start, String end) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start, String end, int page) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start, String end, int page, int limit) throws  IOException, CryptoMarketException;

    /* Authenticated Endpoints*/

    OrderResponse getActiveOrders(String market) throws  IOException, CryptoMarketException;
    OrderResponse getActiveOrders(String market, int page) throws  IOException, CryptoMarketException;
    OrderResponse getActiveOrders(String market, int page, int limit) throws  IOException, CryptoMarketException;
}
