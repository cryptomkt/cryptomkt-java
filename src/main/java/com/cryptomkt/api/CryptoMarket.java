package com.cryptomkt.api;


import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.exception.CryptoMarketException;

import java.io.IOException;

public interface CryptoMarket {

    /* Public Endpoints */

    MarketsResponse getMarkets() throws IOException, CryptoMarketException;

    TickersResponse getTickers() throws  IOException, CryptoMarketException;
    TickersResponse getTickers(String market) throws  IOException, CryptoMarketException;

    BookResponse getBook(String market, String side) throws  IOException, CryptoMarketException;
    BookResponse getBook(String market, String side, int page) throws  IOException, CryptoMarketException;
    BookResponse getBook(String market, String side, int page, int limit) throws  IOException, CryptoMarketException;

    TradeResponse getTrades(String market) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start, String end) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start, String end, int page) throws  IOException, CryptoMarketException;
    TradeResponse getTrades(String market, String start, String end, int page, int limit) throws  IOException, CryptoMarketException;

    PricesResponse getPrices(String market, String timeframe, int page, int limit) throws  IOException, CryptoMarketException;



    /* Authenticated Endpoints*/

    AccountResponse getAccount() throws  IOException, CryptoMarketException;

    OrderListResponse getActiveOrders(String market) throws  IOException, CryptoMarketException;
    OrderListResponse getActiveOrders(String market, int page) throws  IOException, CryptoMarketException;
    OrderListResponse getActiveOrders(String market, int page, int limit) throws  IOException, CryptoMarketException;

    OrderListResponse getExecutedOrders(String market, int page, int limit) throws  IOException, CryptoMarketException; // orders/executed

    OrderResponse createOrder(String market, String price, String side, String type, String amount) throws  IOException, CryptoMarketException; // orders/create

    OrderListResponse createMultiOrders(...); // orders/create/bulk

    OrderResponse getOrderStatus(String id) throws  IOException, CryptoMarketException; // orders/status

    OrderResponse cancelOrder(String id) throws  IOException, CryptoMarketException; // orders/cancel

    OrderListResponse cancelMultiOrder(a list of ids) throws  IOException, CryptoMarketException; // orders/cancel/bulk

    OrderResponse getInstant(String market, Sting side, Strimg amount) throws  IOException, CryptoMarketException; // complicated

    BalanceResponse getBalance() throws  IOException, CryptoMarketException; // balance

    getTransactions(String currency, int page, int limit) throws  IOException, CryptoMarketException; // transactions

    notifyDeposit(amount, bankAccount, date, trakingCode, voucher) throws  IOException, CryptoMarketException;  // deposit

    notifyWithdrawal(amount, bankAccount) throws  IOException, CryptoMarketException; // withdrawal

    transfer(address, amount, currency, memo) throws  IOException, CryptoMarketException; // transfer

    getAuthSocket() throws  IOException, CryptoMarketException; // socket/auth

    getSocket() throws  IOException, CryptoMarketException;

}
