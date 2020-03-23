package com.cryptomkt.api;


import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.entity.orders.*;
import com.cryptomkt.api.exception.CryptoMarketException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CryptoMarket {

    /* Public Endpoints */

    /**
     * Get a list of markets available at Cryptomarket.
     *
     * @return the markets available at Cryptomarket
     * @throws IOException
     * @throws  CryptoMarketException
     */
    MarketsResponse getMarkets() throws IOException, CryptoMarketException;

    /**
     * Get a list of tickers, one for every market available at Cryptomarket.
     *
     * @return a list of all tickers of all active markets in Cryptomarket
     * @throws IOException
     * @throws CryptoMarketException
     */
    TickersResponse getTickers() throws  IOException, CryptoMarketException;

    /**
     * Get a list of one item, with the ticker of the specified market.
     *
     * @param market a marketpair to get the ticker from
     * @return a list of one item, the ticker of the specified market
     * @throws IOException
     * @throws CryptoMarketException
     */
    TickersResponse getTickers(String market) throws  IOException, CryptoMarketException;

    /**
     * Get a list of the active orders of a side of a market.
     *
     * @see #getBook(String, String, int, int)
     */
    BookResponse getBook(String market, String side) throws  IOException, CryptoMarketException;

    /**
     * Get a list of the active orders of a side of a market.
     *
     * @see #getBook(String, String, int, int)
     */
    BookResponse getBook(String market, String side, int page) throws  IOException, CryptoMarketException;

    /**
     * Get a list of the active orders of a side of a market.
     *
     * @param market a market pair to get the books from
     * @param side   "buy" or "sell" the side of the market to get the books from
     * @param page   the page of all the orders to get. Default:0 (optional)
     * @param limit  the number of orders per page. Default:20, Max:100 (optional)
     * @return a page of all the active orders of a given side of a given market
     * @throws IOException
     * @throws CryptoMarketException
     */
    BookResponse getBook(String market, String side, int page, int limit) throws  IOException, CryptoMarketException;

    /**
     * Get a list of all trades of a market.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market) throws  IOException, CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until the
     * current date.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market, String start) throws  IOException, CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until
     * <code>end</code> date.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market, String start, String end) throws  IOException, CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until
     * <code>end</code> date.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market, String start, String end, int page) throws  IOException, CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until
     * <code>end</code> date.
     *
     * @param market a market pair to get trades from
     * @param start  the earlier date to get trades from. Format: YYYY-MM-DD (optional)
     * @param end    the later date to get trades from, exclusive. Format: YYYY-MM-DD (optional)
     * @param page   the page of all the orders to get. Default:0 (optional)
     * @param limit  the number of orders per page. Default:20, Max:100 (optional)
     * @return a page of 20 trades of a list with trades between two dates
     * @throws IOException
     * @throws CryptoMarketException
     */
    TradeResponse getTrades(String market, String start, String end, int page, int limit) throws  IOException, CryptoMarketException;

    /**
     * Get the candles of a market, with <code>timeframe</code> interval between candles.
     *
     * @see #getPrices(String, String, int, int)
     */
    PricesResponse getPrices(String market, String timeframe) throws  IOException, CryptoMarketException;

    /**
     * Get the candles of a market, with <code>timeframe</code> interval between candles.
     *
     * @see #getPrices(String, String, int, int)
     */
    PricesResponse getPrices(String market, String timeframe, int page) throws  IOException, CryptoMarketException;

    /**
     * Get the candles of a market, with <code>timeframe</code> interval between candles.
     *
     * @param market    a market pair to get the prices from
     * @param timeframe time lapse between each candle: 1, 5, 15, 60, 240, 1440 or 10080
     * @param page   the page of all the orders to get. Default:0 (optional)
     * @param limit  the number of orders per page. Default:20, Max:100 (optional)
     * @return two list of candles, one of ask prices and one of bid prices. From the market and timeframe
     * @throws IOException
     * @throws CryptoMarketException
     */
    PricesResponse getPrices(String market, String timeframe, int page, int limit) throws  IOException, CryptoMarketException;



    /* Authenticated Endpoints*/

    /**
     * Get the account of the user.
     *
     * @return the account information of the user.
     * @throws IOException
     * @throws CryptoMarketException
     */
    AccountResponse getAccount() throws  IOException, CryptoMarketException;

    /**
     * Get the user's active orders in a market
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getActiveOrders(String market) throws  IOException, CryptoMarketException;

    /**
     * Get the user's active orders in a market
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getActiveOrders(String market, int page) throws  IOException, CryptoMarketException;

    /**
     * Get the user's active orders in a market
     *
     * @param market a market pair to get the orders from
     * @param page   the page of all the orders to get. Default:0 (optional)
     * @param limit  the number of orders per page. Default:20, Max:100 (optional)
     * @return a list of active orders of the user in a market
     * @throws IOException
     * @throws CryptoMarketException
     */
    OrdersResponse getActiveOrders(String market, int page, int limit) throws  IOException, CryptoMarketException;

    /**
     * Get the user's executed orders in a market
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getExecutedOrders(String market) throws  IOException, CryptoMarketException;

    /**
     * Get the user's executed orders in a market
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getExecutedOrders(String market, int page) throws  IOException, CryptoMarketException;

    /**
     * Get the user's executed orders in a market
     *
     * @param market a market pair to get the orders from
     * @param page   the page of all the orders to get. Default:0 (optional)
     * @param limit  the number of orders per page. Default:20, Max:100 (optional)
     * @return a list of executed orders of the user in a market
     * @throws IOException
     * @throws CryptoMarketException
     */
    OrdersResponse getExecutedOrders(String market, int page, int limit) throws  IOException, CryptoMarketException; // orders/executed

    /**
     *  Creates an order in a market,
     *
     * @param market the market pair to put the order
     * @param price  the price of a unit of crypto to buy or sell
     * @param side   "buy" or "sell", the side of the market on which to crete the order
     * @param type   "market", "limit" or "stop-limit", the type of order to make
     * @param amount the amount of crypto to buy or sell
     * @return the order status
     * @throws IOException
     * @throws CryptoMarketException
     */
    OrderResponse createOrder(String market, String price, String side, String type, String amount) throws  IOException, CryptoMarketException; // orders/create

    /**
     * Creates multiple orders in a single market.
     *
     * @param multiOrderRequest the description of the orders to make
     * @return a list with the data of the requested orders and the published order, also returns a list with the orders not created, with an explanatory message.
     * @throws IOException
     * @throws CryptoMarketException
     */
    CreateMultiOrderResponse createMultiOrders(MultiOrderRequest multiOrderRequest) throws IOException, CryptoMarketException; // orders/create/bulk

    /**
     * Get the status of an order
     *
     * @param id the id of the order
     * @return the current status of an order
     * @throws IOException
     * @throws CryptoMarketException
     */
    OrderResponse getOrderStatus(String id) throws  IOException, CryptoMarketException; // orders/status

    /**
     * Cancel an order
     *
     * @param id the id of the order to cancel
     * @return the status of the order after is cancelled
     * @throws IOException
     * @throws CryptoMarketException
     */
    OrderResponse cancelOrder(String id) throws  IOException, CryptoMarketException; // orders/cancel

    /**
     * cancel multiple orders
     *
     * @param idList a list of the ids of the orders to cancel
     * @return a list with the orders cancelled and their ids, and a list with the orders ids not cancelled, and an explanatory message
     * @throws IOException
     * @throws CryptoMarketException
     */
    CancelMultiOrderResponse cancelMultiOrder(List<String> idList) throws  IOException, CryptoMarketException;


    /**
     * Get all the wallets of the client.
     *
     * @return a list of wallets
     * @throws IOException
     * @throws CryptoMarketException
     */
    BalanceResponse getBalance() throws  IOException, CryptoMarketException; // balance

    /**
     * Get the authentication information to create a socket
     *
     * @return the authentication information to create a socket
     * @throws IOException
     * @throws CryptoMarketException
     */
    SocAuthResponse getAuthSocket() throws IOException, CryptoMarketException;

    /**
     * Get a socket
     *
     * @return a socket
     * @throws IOException
     * @throws CryptoMarketException
     * @throws URISyntaxException
     */
    SocketIo getSocket() throws IOException, CryptoMarketException, URISyntaxException;

    /**
     *
     * @param currency
     * @return
     * @throws IOException
     * @throws CryptoMarketException
     */
    TransactionsResponse getTransactions(String currency) throws  IOException, CryptoMarketException;
    TransactionsResponse getTransactions(String currency, int page) throws  IOException, CryptoMarketException;
    TransactionsResponse getTransactions(String currency, int page, int limit) throws  IOException, CryptoMarketException; // transactions

    Response notifyDeposit(String amount, String bankAccount) throws  IOException, CryptoMarketException;  // deposit
    Response notifyDeposit(String amount, String bankAccount, File voucher) throws  IOException, CryptoMarketException;  // deposit
    Response notifyDeposit(String amount, String bankAccount, String date, String trakingCode, File voucher) throws  IOException, CryptoMarketException;  // deposit

    Response notifyWithdrawal(String amount, String bankAccount) throws  IOException, CryptoMarketException; // withdrawal

    /**
     * Transfer currency between wallets
     *
     * @param address  of the wallet to transfer
     * @param amount   the quantity of currency to transfer
     * @param currency the crypto to transfer
     * @param memo     of the wallet to transfer (optional)
     * @return the status of the transfer
     * @throws IOException
     * @throws CryptoMarketException
     */
    Response transfer(String address, String amount, String currency, String memo) throws  IOException, CryptoMarketException; // transfer

    /**
     * Transfer currency between wallets
     *
     * @see #transfer(String, String, String, String)
     */
    Response transfer(String address, String amount, String currency) throws  IOException, CryptoMarketException; // transfer

}
