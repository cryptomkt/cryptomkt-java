package com.cryptomkt.api;


import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.entity.orders.*;
import com.cryptomkt.api.exception.CryptoMarketException;

import java.util.List;

public interface Client {

    /* Public Endpoints */

    /**
     * Get a list of markets available at Cryptomarket.
     *
     * @return the markets available at Cryptomarket
     * @throws  CryptoMarketException
     */
    MarketsResponse getMarkets() throws CryptoMarketException;

    /**
     * Get a list of tickers, one for every market available at Cryptomarket.
     *
     * @return a list of all tickers of all active markets in Cryptomarket
     * @throws CryptoMarketException
     */
    TickersResponse getTickers() throws  CryptoMarketException;

    /**
     * Get the ticker of one market.
     *
     * @param market the tickers market
     * @return a list of one item, the ticker of the specified market
     * @throws CryptoMarketException
     */
    TickersResponse getTickers(String market) throws  CryptoMarketException;

    /**
     * Get a list of open orders of a market side.
     *
     * @see #getBook(String, String, int, int)
     */
    BooksResponse getBook(String market, String side) throws  CryptoMarketException;

    /**
     * Get a list of open orders of a market side.
     *
     * @see #getBook(String, String, int, int)
     */
    BooksResponse getBook(String market, String side, int page) throws  CryptoMarketException;

    /**
     * Get a list of open orders of a market side.
     *
     * @param market the open orders market
     * @param side   the market side, "buy" or "sell".
     * @param page   the requested page of the query. Default:0 (optional)
     * @param limit  the number of entries per page. Default:20, Max:100 (optional)
     * @return a page of all the open orders of a market side
     * @throws CryptoMarketException
     */
    BooksResponse getBook(String market, String side, int page, int limit) throws  CryptoMarketException;

    /**
     * Get a list of all trades of a market.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market) throws  CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until the
     * current date.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market, String start) throws  CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until
     * <code>end</code> date.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market, String start, String end) throws  CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until
     * <code>end</code> date.
     *
     * @see #getTrades(String, String, String, int, int)
     */
    TradeResponse getTrades(String market, String start, String end, int page) throws  CryptoMarketException;

    /**
     * Get a list of all trades of a market, since <code>start</code> until
     * <code>end</code> date.
     *
     * @param market the trades market
     * @param start  the first date to get trades, inclusive. Format: YYYY-MM-DD (optional)
     * @param end    the last date to get trades, exclusive. Format: YYYY-MM-DD (optional)
     * @param page   the requested page of the query. Default:0 (optional)
     * @param limit  the number of entries per page. Default:20, Max:100 (optional)
     * @return a page of a list with trades between two dates
     * @throws CryptoMarketException
     */
    TradeResponse getTrades(String market, String start, String end, int page, int limit) throws  CryptoMarketException;

    /**
     * Get the candles of a market, with <code>timeframe</code> interval between candles.
     *
     * @see #getPrices(String, String, int, int)
     */
    PricesResponse getPrices(String market, String timeframe) throws  CryptoMarketException;

    /**
     * Get the candles of a market, with <code>timeframe</code> interval between candles.
     *
     * @see #getPrices(String, String, int, int)
     */
    PricesResponse getPrices(String market, String timeframe, int page) throws  CryptoMarketException;

    /**
     * Get the market candles, with <code>timeframe</code> interval between candles.
     *
     * @param market the prices market
     * @param timeframe time lapse between each candle: 1, 5, 15, 60, 240, 1440 or 10080
     * @param page   the requested page of the query. Default:0 (optional)
     * @param limit  the number of entries per page. Default:20, Max:100 (optional)
     * @return a PricesResponse with two lists of candles, one of ask prices and one of bid prices.
     * @throws CryptoMarketException
     */
    PricesResponse getPrices(String market, String timeframe, int page, int limit) throws  CryptoMarketException;



    /* Authenticated Endpoints*/

    /**
     * Get the user account.
     *
     * @return the account information of the user.
     * @throws CryptoMarketException
     */
    AccountResponse getAccount() throws  CryptoMarketException;

    /**
     * Get the user active orders in a market.
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getActiveOrders(String markedatat) throws  CryptoMarketException;

    /**
     * Get the user active orders in a market.
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getActiveOrders(String market, int page) throws  CryptoMarketException;

    /**
     * Get the user active orders in a market.
     *
     * @param market the market of the active orders
     * @param page   the requested page of the query. Default:0 (optional)
     * @param limit  the number of entries per page. Default:20, Max:100 (optional)
     * @return an OrderResponse with a list of user active orders in a market
     * @throws CryptoMarketException
     */
    OrdersResponse getActiveOrders(String market, int page, int limit) throws  CryptoMarketException;

    /**
     * Get the user executed orders in a market.
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getExecutedOrders(String market) throws  CryptoMarketException;

    /**
     * Get the user executed orders in a market.
     *
     * @see #getActiveOrders(String, int, int)
     */
    OrdersResponse getExecutedOrders(String market, int page) throws  CryptoMarketException;

    /**
     * Get the user executed orders in a market.
     *
     * @param market a market to get the list of executed orders
     * @param page   the requested page of the query. Default:0 (optional)
     * @param limit  the number of entries per page. Default:20, Max:100 (optional)
     * @return an OrderResopnse with a list of the user executed orders in a market
     * @throws CryptoMarketException
     */
    OrdersResponse getExecutedOrders(String market, int page, int limit) throws  CryptoMarketException; // orders/executed

    /**
     *  Create an order at market price.
     *
     * @param market the market to make the order
     * @param side   "buy" or "sell"
     * @param amount the amount to buy or sell
     * @return an OrderResopnse with the order status
     * @throws CryptoMarketException
     */
    OrderResponse createMarketOrder(String market, String side, String amount) throws  CryptoMarketException; // orders/create

    /**
     *  Creates a limit order.
     *
     * @param market the market to make the order
     * @param side   "buy" or "sell"
     * @param amount the amount to buy or sell
     * @param limit the limit price of the order
     * @return an OrderResopnse with the order status
     * @throws CryptoMarketException
     */
    OrderResponse createLimitOrder(String market, String side, String amount, String limit) throws  CryptoMarketException; // orders/create

    /**
     *  Create a stop limit order.
     *
     * @param market the market to make the order
     * @param side   "buy" or "sell"
     * @param amount the amount to buy or sell
     * @param limit the limit price of the order
     * @param stop the stop price of the order
     * @return an OrderResopnse with the order status
     * @throws CryptoMarketException
     */
    OrderResponse createStopLimitOrder(String market, String side, String amount, String limit, String stop) throws  CryptoMarketException; // orders/create

    /**
     * Create multiple orders.
     *
     * @param multiOrderRequest the description of the orders to make
     * @return a CreateMultiOrderResponse with two lists. A list with the created orders, and a list with the not created orders.
     * @throws CryptoMarketException
     */
    CreateMultiOrderResponse createMultiOrders(MultiOrderRequest multiOrderRequest) throws CryptoMarketException; // orders/create/bulk

    /**
     * Get the order status.
     *
     * @param id the order id
     * @return the order status
     * @throws CryptoMarketException
     */
    OrderResponse getOrderStatus(String id) throws  CryptoMarketException; // orders/status

    /**
     * Cancel an order
     *
     * @param id the order id
     * @return the cancelled order status
     * @throws CryptoMarketException
     */
    OrderResponse cancelOrder(String id) throws  CryptoMarketException; // orders/cancel

    /**
     * Cancel multiple orders
     *
     * @param idList a list of order ids
     * @return a CancelMultiOrderResponse with two lists. A list with the cancelled orders, and a list with the not cancelled order ids.
     * @throws CryptoMarketException
     */
    CancelMultiOrderResponse cancelMultiOrder(List<String> idList) throws  CryptoMarketException;


    /**
     * Get all the client wallets.
     *
     * @return a list of wallets
     * @throws CryptoMarketException
     */
    BalanceResponse getBalance() throws  CryptoMarketException; // balance

    /**
     * Get the user transactions of a currency
     *
     * @see #getTransactions(String, int, int)
     */
    TransactionsResponse getTransactions(String currency) throws  CryptoMarketException;

    /**
     * Get the user transactions of a currency
     *
     * @see #getTransactions(String, int, int)
     */
    TransactionsResponse getTransactions(String currency, int page) throws  CryptoMarketException;

    /**
     * Get the user transactions of a currency
     *
     * @param currency the currency of the transactions
     * @param page     the requested page of the query. Default:0 (optional)
     * @param limit    the number of entries per page. Default:20, Max:100 (optional)
     * @return a Transaction response with a list of transactions
     * @throws CryptoMarketException
     */
    TransactionsResponse getTransactions(String currency, int page, int limit) throws  CryptoMarketException; // transactions

    /*
    Response notifyDeposit(String amount, String bankAccount) throws  CryptoMarketException;  // deposit
    Response notifyDeposit(String amount, String bankAccount, String voucher) throws  CryptoMarketException;  // deposit
    Response notifyDeposit(String amount, String bankAccount, String date, String trakingCode, String voucher) throws  CryptoMarketException;  // deposit
    */

    Response notifyWithdrawal(String amount, String bankAccount) throws  CryptoMarketException; // withdrawal

    /**
     * Transfer currency between wallets
     *
     * @see #transfer(String, String, String, String)
     */
    Response transfer(String address, String amount, String currency) throws  CryptoMarketException; // transfer

    /**
     * Transfer currency between wallets
     *
     * @param address  the receiving wallet of the transfer
     * @param amount   the quantity of currency
     * @param currency the currency to transfer
     * @param memo     the receiving wallet memo (optional)
     * @return the result of the transfer
     * @throws CryptoMarketException
     */
    Response transfer(String address, String amount, String currency, String memo) throws  CryptoMarketException; // transfer

    /**
     * Get the authentication information to create a socket
     *
     * @return the authentication information to create a socket
     * @throws CryptoMarketException
     */
    SocAuthResponse getAuthSocket() throws CryptoMarketException;

    /**
     * Get a socket
     *
     * @return a socket
     * @throws CryptoMarketException
     */
    Socket getSocket() throws CryptoMarketException;
}
