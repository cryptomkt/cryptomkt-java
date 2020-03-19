package com.cryptomkt.api;


import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.exception.CryptoMarketException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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

    PricesResponse getPrices(String market, String timeframe) throws  IOException, CryptoMarketException;
    PricesResponse getPrices(String market, String timeframe, int page) throws  IOException, CryptoMarketException;
    PricesResponse getPrices(String market, String timeframe, int page, int limit) throws  IOException, CryptoMarketException;



    /* Authenticated Endpoints*/

    AccountResponse getAccount() throws  IOException, CryptoMarketException;

    OrdersResponse getActiveOrders(String market) throws  IOException, CryptoMarketException;
    OrdersResponse getActiveOrders(String market, int page) throws  IOException, CryptoMarketException;
    OrdersResponse getActiveOrders(String market, int page, int limit) throws  IOException, CryptoMarketException;

    OrdersResponse getExecutedOrders(String market) throws  IOException, CryptoMarketException;
    OrdersResponse getExecutedOrders(String market, int page) throws  IOException, CryptoMarketException;
    OrdersResponse getExecutedOrders(String market, int page, int limit) throws  IOException, CryptoMarketException; // orders/executed

    OrderResponse createOrder(String market, String price, String side, String type, String amount) throws  IOException, CryptoMarketException; // orders/create

    OrdersResponse createMultiOrders(List<Order> orderList); // orders/create/bulk

    OrderResponse getOrderStatus(String id) throws  IOException, CryptoMarketException; // orders/status

    OrderResponse cancelOrder(String id) throws  IOException, CryptoMarketException; // orders/cancel

    OrdersResponse cancelMultiOrder(List<String> idList) throws  IOException, CryptoMarketException; // orders/cancel/bulk

    // OrderResponse getInstant(String market, String side, String amount) throws  IOException, CryptoMarketException;
    //      implement from book

    BalanceResponse getBalance() throws  IOException, CryptoMarketException; // balance

    SocAuthResponse getAuthSocket() throws IOException, CryptoMarketException;
    SocketIo getSocket() throws IOException, CryptoMarketException, URISyntaxException;

    TransactionsResponse getTransactions(String currency) throws  IOException, CryptoMarketException;
    TransactionsResponse getTransactions(String currency, int page) throws  IOException, CryptoMarketException;
    TransactionsResponse getTransactions(String currency, int page, int limit) throws  IOException, CryptoMarketException; // transactions

    Response notifyDeposit(String amount, String bankAccount) throws  IOException, CryptoMarketException;  // deposit
    Response notifyDeposit(String amount, String bankAccount, File voucher) throws  IOException, CryptoMarketException;  // deposit
    Response notifyDeposit(String amount, String bankAccount, String date, String trakingCode, File voucher) throws  IOException, CryptoMarketException;  // deposit

    Response notifyWithdrawal(String amount, String bankAccount) throws  IOException, CryptoMarketException; // withdrawal

    Response transfer(String address, String amount, String currency, String memo) throws  IOException, CryptoMarketException; // transfer
    Response transfer(String address, String amount, String currency) throws  IOException, CryptoMarketException; // transfer

    // getAuthSocket() throws  IOException, CryptoMarketException; // socket/auth

    // getSocket() throws  IOException, CryptoMarketException;

}
