package com.cryptomarket.sdk;

import java.util.List;
import java.util.Map;

import com.cryptomarket.params.By;
import com.cryptomarket.params.Margin;
import com.cryptomarket.params.OrderRequest;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.Pagination;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.Sort;
import com.cryptomarket.params.TimeInForce;
import com.cryptomarket.params.TransferBy;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Address;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.models.Trade;
import com.cryptomarket.sdk.models.Transaction;

import org.jetbrains.annotations.Nullable;

/**
 * Client
 */
public interface CryptomarketRestClient {

    /**
     * Get a list of all currencies or specified currencies.
     * <p>
     * https://api.exchange.cryptomkt.com/#currencies
     * 
     * @param currencies Optional. A list of symbol ids
     * @return A list of available currencies
     * @throws CryptomarketSDKException
     */
    public List<Currency> getCurrencies(@Nullable List<String> currencies) throws CryptomarketSDKException;

    /**
     * Get the data of a currency.
     * <p>
     * https://api.exchange.cryptomkt.com/#currencies
     * 
     * @param currency A currency id
     * @return A Currency
     * @throws CryptomarketSDKException
     */
    public Currency getCurrency(String currency) throws CryptomarketSDKException;
    
    /**
     * Get a list of all symbols or for specified symbols.
     * <p>
     * A symbol is the combination of the base currency (first one) and quote currency (second one).
     * <p>
     * https://api.exchange.cryptomkt.com/#symbols
     * 
     * @param symbols Optional. A list of symbol ids
     * @return A list of symbols traded on the exchange
     * @throws CryptomarketSDKException
     */
    public List<Symbol> getSymbols(@Nullable List<String> symbols) throws CryptomarketSDKException;

    /**
     * Get a symbol by its id.
     * <p>
     * A symbol is the combination of the base currency (first one) and quote currency (second one).
     * <p>
     * https://api.exchange.cryptomkt.com/#symbols
     * 
     * @param symbol A symbol id
     * @return A symbol traded on the exchange
     * @throws CryptomarketSDKException
     */
    public Symbol getSymbol(String symbol) throws CryptomarketSDKException;

    /**
     * Get tickers for all symbols or for specified symbols.
     * <p>
     * https://api.exchange.cryptomkt.com/#tickers
     * 
     * @param symbols Optional. A list of symbol ids
     * @return A list of tickers
     * @throws CryptomarketSDKException
     */
    public List<Ticker> getTickers(@Nullable List<String> symbols) throws CryptomarketSDKException;

    /**
     * Get the ticker of a symbol.
     * <p>
     * https://api.exchange.cryptomkt.com/#tickers
     * 
     * @param symbol A symbol id
     * @return The ticker of a symbol
     * @throws CryptomarketSDKException
     */
    public Ticker getTicker(String symbol) throws CryptomarketSDKException;
    
    /**
     * Get trades for all symbols or for specified symbols.
     * <p>
     * 'from' param and 'till' param must have the same format, both index of both timestamp.
     * <p>
     * https://api.exchange.cryptomkt.com/#trades
     * 
     * @param symbols Optional. A list of symbol ids
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @return A list of trades for each symbol of the query in a map
     * @throws CryptomarketSDKException
     */
    public Map<String, List<PublicTrade>> getTrades(
        @Nullable List<String> symbols, 
        @Nullable Sort sort, 
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset) 
        throws CryptomarketSDKException;

    /**
     * Get trades for all symbols or for specified symbols.
     * <p>
     * 'from' param and 'till' param must have the same format, both index of both timestamp.
     * <p>
     * https://api.exchange.cryptomkt.com/#trades
     * 
     * @param symbols Optional. A list of symbol ids
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @return Trades for each symbol
     * @throws CryptomarketSDKException
     */
    public Map<String, List<PublicTrade>> getTrades(@Nullable List<String> symbols, @Nullable Pagination pagination) 
        throws CryptomarketSDKException;

    /**
     * Get trades of a symbol
     * <p>
     * 'from' param and 'till' param must have the same format, both index of both timestamp.
     * <p>
     * https://api.exchange.cryptomkt.com/#trades
     * 
     * @param symbol A symbol id
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @return A list of trades of a symbol
     * @throws CryptomarketSDKException
     */
    public List<PublicTrade> getTradesOfSymbol(
        @Nullable String symbol, 
        @Nullable Sort sort, 
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset) 
        throws CryptomarketSDKException;

    /**
     * Get trades of a symbol.
     * <p>
     * 'from' param and 'till' param must have the same format, both index of both timestamp.
     * <p>
     * https://api.exchange.cryptomkt.com/#trades
     * 
     * @param symbol A symbol id
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @return Trades of a symbol
     * @throws CryptomarketSDKException
     */
    public List<PublicTrade> getTradesOfSymbol(@Nullable String symbol, @Nullable Pagination pagination) throws CryptomarketSDKException;

    /**
     * Get order book for all symbols or for the specified symbols.
     * <p>
     * An Order Book is an electronic list of buy and sell orders for a specific symbol, structured by price level.
     * <p>
     * https://api.exchange.cryptomkt.com/#order-book
     * 
     * @param symbols Optional. A list of symbol ids
     * @param limit Optional. Limit of order book levels. Set to 0 to view full list of order book levels
     * @return The order book for each queried symbol
     * @throws CryptomarketSDKException
     */
    public Map<String, OrderBook> getOrderBooks(@Nullable List<String> symbols, @Nullable Integer limit) throws CryptomarketSDKException;

    /**
     * Get order book of a symbol.
     * <p>
     * An Order Book is an electronic list of buy and sell orders for a specific symbol, structured by price level.
     * <p>
     * https://api.exchange.cryptomkt.com/#order-book
     * 
     * @param symbol The symbol id
     * @param limit Optional. Limit of order book levels. Set to 0 to view full list of order book levels
     * @return An order book
     * @throws CryptomarketSDKException
     */
    public OrderBook getOrderBook(String symbol, @Nullable Integer limit) throws CryptomarketSDKException;

    /**
     * Get order book of a symbol with market depth data.
     * <p>
     * An Order Book is an electronic list of buy and sell orders for a specific symbol, structured by price level.
     * <p>
     * https://api.exchange.cryptomkt.com/#order-book
     * 
     * @param symbol The symbol id
     * @param volume Desired volume for market depth search
     * @return An order book with market depth data
     * @throws CryptomarketSDKException
     */
    public OrderBook getMarketDepth(String symbol, Integer volume) throws CryptomarketSDKException;

    /**
     * Get candles for all symbols or for specified symbols.
     * <p>
     * Candels are used for OHLC representation.
     * <p>
     * https://api.exchange.cryptomkt.com/#candles
     * 
     * @param symbols Optional. A list of symbol ids
     * @param period Optional. A Period enum type. default is Period._30_MINUTES, valid intervals are 1, 3, 5, 15 and 30 minutes, 1 and 4 hours, 1 and 7 days and 1 month.
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @return A list of Candles for each symbol of the query
     * @throws CryptomarketSDKException 
     */
    public Map<String, List<Candle>> getCandles(
        @Nullable List<String> symbols, 
        @Nullable Period period, 
        @Nullable Sort sort, 
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset) 
        throws CryptomarketSDKException;
    
    /**
     * Get candles for all symbols or for specified symbols.
     * <p>
     * Candels are used for OHLC representation.
     * <p>
     * https://api.exchange.cryptomkt.com/#candles
     * 
     * @param symbols Optional. A list of symbol ids
     * @param period Optional. A Period enum type. default is Period._30_MINUTES, valid intervals are 1, 3, 5, 15 and 30 minutes, 1 and 4 hours, 1 and 7 days and 1 month.
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @return A list of Candles for each symbol of the query
     * @throws CryptomarketSDKException
     */
    public Map<String, List<Candle>> getCandles(@Nullable List<String> symbols, @Nullable Period period, @Nullable Pagination pagination) 
        throws CryptomarketSDKException;

    /**
     * Get candles of a symbol.
     * <p>
     * Candels are used for OHLC representation.
     * <p>
     * https://api.exchange.cryptomkt.com/#candles
     * 
     * @param symbol A symbol id
     * @param period Optional. A Period enum type. default is Period._30_MINUTES, valid intervals are 1, 3, 5, 15 and 30 minutes, 1 and 4 hours, 1 and 7 days and 1 month.
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @return A list of Candles of a symbol
     * @throws CryptomarketSDKException 
     */
    public List<Candle> getCandlesOfSymbol(
        @Nullable String symbol, 
        @Nullable Period period, 
        @Nullable Sort sort, 
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset) 
        throws CryptomarketSDKException;

    /**
     * Get candles of a symbol.
     * <p>
     * Candels are used for OHLC representation.
     * <p>
     * https://api.exchange.cryptomkt.com/#candles
     * 
     * @param symbols A symbol id
     * @param period Optional. A Period enum type. default is Period._30_MINUTES, valid intervals are 1, 3, 5, 15 and 30 minutes, 1 and 4 hours, 1 and 7 days and 1 month.
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @return A list of Candles of a symbol
     * @throws CryptomarketSDKException
     */
    public List<Candle> getCandlesOfSymbol(@Nullable String symbol, @Nullable Period period, @Nullable Pagination pagination) throws CryptomarketSDKException;

    //// AUTHENTICATED CALLS ////

    // TRADING

    /**
     * Get the account trading balance.
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#trading-balance
     * 
     * @return the account trading balance
     * @throws CryptomarketSDKException
     */
    public List<Balance> getTradingBalance() throws CryptomarketSDKException;

    /**
     * Get the account active orders.
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#get-active-orders
     * 
     * @param symbol Optional. A symbol for filtering active orders
     * @return The account active orders
     * @throws CryptomarketSDKException
     */
    public List<Order> getActiveOrders(@Nullable String symbol) throws CryptomarketSDKException;

    /**
     * Get an order by its client order id.
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#get-active-orders
     * 
     * @param clientOrderId The clientOrderId of the order
     * @param wait Time in milliseconds Max value is 60000 Default value is null. While using long polling request: if order is filled, cancelled or expired order info will be returned instantly. For other order statuses, actual order info will be returned after specified wait time.

     * @return An order of the account.
     * @throws CryptomarketSDKException
     */
    public Order getActiveOrder(String clientOrderId, @Nullable Integer wait) throws CryptomarketSDKException;

    /**
     * Creates a new order
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#create-new-order
     * 
     * @param symbol Trading symbol
     * @param side A Side enum
     * @param quantity Order quantity
     * @param clientOrderId Optional. If given must be unique within the trading day, including all active orders. If not given, is generated by the server.
     * @param orderType Optional. An OrderType enum. default is OrderType.LIMIT
     * @param price Required for OrderType.LIMIT and OrderType.STOPLIMIT. limit price of the order
     * @param stopPrice Required for OrderType.STOPLIMIT and OrderType.STOPMARKET orders. stop price of the order
     * @param timeInForce Optional. A TimeInForce enum. Default to TimeInForce.GTC
     * @param expireTime Required for orders with timeInForce.GDT
     * @param strictValidate Optional. If False, the server rounds half down for tickerSize and quantityIncrement. Example of ETHBTC: tickSize = '0.000001', then price '0.046016' is valid, '0.0460165' is invalid. 
     * @param postOnly Optional. If True, your post_only order causes a match with a pre-existing order as a taker, then the order will be cancelled.
     * @return An order of the account
     * @throws CryptomarketSDKException
     */
    public Order createOrder(
        String symbol, 
        Side side,
        String quantity, 
        @Nullable String clientOrderId, 
        @Nullable OrderType orderType, 
        @Nullable String price, 
        @Nullable String stopPrice, 
        @Nullable TimeInForce timeInForce,
        @Nullable String expireTime, 
        @Nullable Boolean strictValidate, 
        @Nullable Boolean postOnly) 
        throws CryptomarketSDKException;
    
    /**
     * Creates a new order
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#create-new-order
     * 
     * @param orderRequest an OrderRequest instance, buildable with new OrderRequest.Builder().someArg(arg).build()
     * @return An order of the account
     * @throws CryptomarketSDKException
     */
    public Order createOrder(OrderRequest orderRequest) throws CryptomarketSDKException;

    /**
     * Cancel all active orders.
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#cancel-orders
     * 
     * @return All the canceled orders
     * @throws CryptomarketSDKException
     */
    public List<Order> cancelAllOrders() throws CryptomarketSDKException;

    /**
     * Cancel the order with clientOrderId.
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#cancel-order-by-clientorderid
     * 
     * @param clientOrderId the client id of the order to cancel
     * @return The canceled order
     * @throws CryptomarketSDKException
     */
    public Order cancelOrder(String clientOrderId) throws CryptomarketSDKException;
    
    /**
     * Get personal trading commission rates for a symbol
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#get-trading-commission
     * 
     * @param symbol The symbol of the comission rates
     * @return The commission rate for a symbol
     * @throws CryptomarketSDKException
     */
    public Commission getTradingCommission(String symbol) throws CryptomarketSDKException;

    // TRADING HISTORY

    /**
     * Get the account order history
     * <p>
     * All not active orders older than 24 are deleted
     * <p>
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#orders-history
     * 
     * @param symbol Optional. Filter orders by symbol
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Orders per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @return Orders in the interval
     * @throws CryptomarketSDKException
     */
    public List<Order> getOrderHistory(
        @Nullable String symbol, 
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset)
        throws CryptomarketSDKException;

    /**
     * Get the account order history
     * <p>
     * All not active orders older than 24 are deleted
     * <p>
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#orders-history
     * 
     * @param symbol Optional. Filter orders by symbol
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @return
     * @throws CryptomarketSDKException
     */
    public List<Order> getOrderHistory(@Nullable String symbol, @Nullable Pagination pagination) 
        throws CryptomarketSDKException;

    /**
     * Get an order of the account
     * <p>
     * All not active orders older than 24 are deleted
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#orders-history
     * 
     * @param clientOrderId the clientOrderId of the order
     * @return An order
     * @throws CryptomarketSDKException
     */
    public List<Order> getOrders(String clientOrderId) throws CryptomarketSDKException;
    
    /**
     * Get the user's trading history
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#orders-history
     * 
     * @param symbol Optional. Filter trades by symbol
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param by Optional. A By enum type. By.ID or By.TIMESTAMP, default is By.TIMESTAMP, field to use for sorting
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @param margin Optional. Filtering of margin orders. values are Margin.INCLUDE, Margin.ONLY or Margin.IGNORE. value is Margin.INCLUDE
     * @return trades of the account
     * @throws CryptomarketSDKException
     */
    public List<Trade> getTradingHistory(
        @Nullable String symbol,
        @Nullable Sort sort,
        @Nullable By by,
        @Nullable String from,
        @Nullable String till,
        @Nullable Integer limit,
        @Nullable Integer offset,
        @Nullable Margin margin)
        throws CryptomarketSDKException;
 
 
        /**
     * Get the user's trading history
     * <p>
     * Requires authentication
     * <p>
     * https://api.exchange.cryptomkt.com/#orders-history
     * 
     * @param symbol Optional. Filter trades by symbol
     * @param margin Optional. Filtering of margin orders. values are Margin.INCLUDE, Margin.ONLY or Margin.IGNORE. value is Margin.INCLUDE
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @return trades of the account
     * @throws CryptomarketSDKException
     */
    public List<Trade> getTradingHistory(@Nullable String symbol, @Nullable Margin margin, @Nullable Pagination pagination) 
        throws CryptomarketSDKException;

    /**
     * Get the account's trading order with a specified order id
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#trades-by-order
     * 
     * @param id Order unique identifier assigned by exchange
     * @return The trades of an order
     * @throws CryptomarketSDKException
     */
    public List<Trade> getTradesByOrder(String id) throws CryptomarketSDKException;

    // ACCOUNT MANAGEMENT

    /**
     * Get the user account balance
     * 
     *
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#account-balance
     * 
     * @return The user's account balance
     * @throws CryptomarketSDKException
     */
    public List<Balance> getAccountBalance() throws CryptomarketSDKException;

    /**
     * Get the current address of a currency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#deposit-crypto-address
     * 
     * @param currency currency to get the address
     * @return The currenct address for the currency
     * @throws CryptomarketSDKException
     */
    public Address getDepositCryptoAddress(String currency) throws CryptomarketSDKException;

    /**
     * Creates a new address for the currency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#deposit-crypto-address
     * 
     * @param currency currency to create a new address
     * @return The created address for the currency
     * @throws CryptomarketSDKException
     */
    public Address createDepositCryptoAddress(String currency) throws CryptomarketSDKException;

    /**
     * Get the last 10 addresses used for deposit by currency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#last-10-deposit-crypto-address
     * 
     * @param currency currency to get the list of addresses
     * @return A list of addresses
     * @throws CryptomarketSDKException
     */
    public List<Address> getLast10DepositCryptoAddresses(String currency) throws CryptomarketSDKException;

    /**
     * Get the last 10 unique addresses used for withdraw by currency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#last-10-used-crypto-address
     * 
     * @param currency currency to get the list of addresses
     * @return A list of addresses
     * @throws CryptomarketSDKException
     */
    public List<Address> get10UsedCryptoAddresses(String currency) throws CryptomarketSDKException;

    /**
     * Withdraw cryptocurrency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#withdraw-crypto
     * 
     * @param currency currency code of the crypto to withdraw 
     * @param amount the amount to be sent to the specified address
     * @param address the address identifier
     * @param paymentId Optional.
     * @param includeFee Optional. If true then the total spent amount includes fees. Default false
     * @param autoCommit Optional. If false then you should commit or rollback transaction in an hour. Used in two phase commit schema. Default true
     * @return Thre transaction id, asigned by the exchange.
     * @throws CryptomarketSDKException
     */
    public String withdrawCrypto(
        String currency,
        String amount,
        String address,
        @Nullable String paymentId,
        @Nullable Boolean includeFee,
        @Nullable Boolean autoCommit)
        throws CryptomarketSDKException;

    /**
     * Converts between currencies.
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#transfer-convert-between-currencies
     * 
     * @param fromCurrency currency code of origin
     * @param toCurrency currency code of destiny
     * @param amount the amount to be sent
     * @return A list of transaction identifiers
     * @throws CryptomarketSDKException
     */
    public List<String> transferConvert(String fromCurrency, String toCurrency, String amount) throws CryptomarketSDKException;

    /**
     * Commit a withdrawal of cryptocurrency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#withdraw-crypto-commit-or-rollback
     * 
     * @param transactionId the withdrawal transaction identifier
     * @return The transaction result. true if the commit is successful.
     * @throws CryptomarketSDKException
     */
    public boolean commitWithdrawCrypto(String transactionId) throws CryptomarketSDKException;

    /**
     * Rollback a withdrawal of cryptocurrency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#withdraw-crypto-commit-or-rollback
     * 
     * @param transactionId the withdrawal transaction identifier
     * @return The transaction result. true if the rollback is successful.
     * @throws CryptomarketSDKException
     */
    public boolean rollbackWithdrawCrypto(String transactionId) throws CryptomarketSDKException;


    /**
     * Get an estimate of the withdrawal fee.
     * 
     * Requires authetication
     * 
     * https://api.exchange.cryptomkt.com/#estimate-withdraw-fee
     * 
     * @param currency the currency code for withdraw.
     * @param amount the expected withdraw amount.
     * @return The expected fee
     * @throws CryptomarketApiExeption
     */
    public String getEstimatesWithdrawFee(String currency, String amount) throws CryptomarketSDKException;


    /**
     * Check if an address is from this account.
     * 
     * Requires authentication.
     * 
     * https://api.exchange.cryptomkt.com/#check-if-crypto-address-belongs-to-current-account
     * 
     * @param address The address to check.
     * @return The transaction result. true if it is from the current account.
     * @throws CryptomarketSDKException
     */
    public boolean checkIfCryptoAddressIsMine(String address) throws CryptomarketSDKException;

    /**
     * Transfer money from trading balance to account balance
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#transfer-money-between-trading-account-and-bank-account
     * 
     * @param currency Currency code for transfering
     * @param amount Amount to be transfered between balances
     * @return the transaction identifier of the transfer.
     * @throws CryptomarketSDKException
     */
    public String transferMoneyFromTradingBalanceToAccountBalance(String currency, String amount) throws CryptomarketSDKException;

    /**
     * Transfer money from account balance to trading balance
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#transfer-money-between-trading-account-and-bank-account
     * 
     * @param currency Currency code for transfering
     * @param amount Amount to be transfered between balances
     * @return the transaction identifier of the transfer.
     * @throws CryptomarketSDKException
     */
    public String transferMoneyFromAccountBalanceToTradingBalance(String currency, String amount) throws CryptomarketSDKException;

    /**
     * Transfer money to another user
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#transfer-money-to-another-user-by-email-or-username
     * 
     * @param currency currency code
     * @param amount amount to be transfered between balances
     * @param transferBy a TransferBy enum type, either TransferBy.EMAIL or TransferType.USERNAME
     * @param identifier the email or the username
     * @throws CryptomarketSDKException
     */
    public String transferMonyToAnotherUser(String currency, String amount, TransferBy transferBy, String identifier) throws CryptomarketSDKException;
    

    /**
     * Get the transactions of the account by currency
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#get-transactions-history
     * 
     * @param currency Currency code to get the transaction history
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param by Optional. A By enum type. By.ID or By.TIMESTAMP, default is By.TIMESTAMP, field to use for sorting
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @param showSenders Optional. If True, show the sender address for payins.
     * @return A list with the transactions in the interval.
     * @throws CryptomarketSDKException
     */
    public List<Transaction> getTransactionHistory(
        String currency, 
        @Nullable Sort sort,
        @Nullable By by,
        @Nullable String from,
        @Nullable String till,
        @Nullable Integer limit,
        @Nullable Integer offset,
        @Nullable Boolean showSenders) 
        throws CryptomarketSDKException;

    /**
     * 
     * @param symbol
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @param showSenders Optional. If True, show the sender address for payins.
     * @return
     * @throws CryptomarketSDKException
     */
    public List<Transaction> getTransactionHistory(@Nullable String symbol, @Nullable Pagination pagination, @Nullable Boolean showSenders) 
        throws CryptomarketSDKException;

    /**
     * Get the transactions of the account by its identifier
     * 
     * Requires authentication
     * 
     * https://api.exchange.cryptomkt.com/#get-transactions-history
     * 
     * @param id The identifier of the transaction
     * @return The transaction with the given id
     * @throws CryptomarketSDKException
     */
    public Transaction getTransaction(String id) throws CryptomarketSDKException;
}
