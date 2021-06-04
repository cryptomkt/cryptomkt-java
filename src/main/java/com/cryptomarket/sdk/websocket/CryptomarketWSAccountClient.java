package com.cryptomarket.sdk.websocket;

import java.util.List;

import com.cryptomarket.params.Pagination;
import com.cryptomarket.params.Sort;
import com.cryptomarket.sdk.Callback;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Transaction;

import org.jetbrains.annotations.Nullable;

/**
 * CryptomarketWSAccountClient connects via websocket to cryptomarket to get account information of the user. uses SHA256 as auth method and authenticates on connection.
 */
public interface CryptomarketWSAccountClient extends CryptomarketWS {

    /**
     * Get the user account balance.
     * <p>
     * https://api.exchange.cryptomkt.com/#request-balance
     * 
     * @param callback A Callback to call with the result data. 
     * 
     * @return A list of balances of the account. Non-zero balances only
     */
    public void getAccountBalance(Callback<List<Balance>> callback);

    /**
     * Get a list of transactions of the account. Accepts only filtering by Datetime
     * <p>
     * https://api.exchange.cryptomkt.com/#find-transactions
     * 
     * @param currency Currency code to get the transaction history
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param from Optional. Initial value of the queried interval. As timestamp
     * @param till Optional. Last value of the queried interval. As timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @param showSenders Optional. If True, show the sender address for payins.
     * @param callback A Callback to call with the result data. 
     * 
     * @return A list with the transactions in the interval.
     */
    public void findTransactions(
        @Nullable String currency, 
        @Nullable Sort sort,
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset,
        @Nullable Boolean showSenders,
        Callback<List<Transaction>> callback);

    /**
     * Get a list of transactions of the account. Accepts only filtering by Datetime
     * <p>
     * https://api.exchange.cryptomkt.com/#find-transactions
     * 
     * @param currency Currency code to get the transaction history
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @param showSenders Optional. If True, show the sender address for payins.
     * @param callback A Callback to call with the result data. 
     * 
     * @return A list with the transactions in the interval.
     */
    public void findTransactions(
        @Nullable String currency, 
        @Nullable Pagination pagination, 
        @Nullable Boolean showSenders,
        Callback<List<Transaction>> callback);

    /**
     * Get a list of transactions of the account. Accepts only filtering by Index.
     * <p>
     * https://api.exchange.cryptomkt.com/#load-transactions
     * 
     * @param currency Currency code to get the transaction history
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.ASC. sorting direction
     * @param from Optional. Initial value of the queried interval. As id
     * @param till Optional. Last value of the queried interval. As id
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @param showSenders Optional. If True, show the sender address for payins.
     * @param callback A Callback to call with the result data. 
     * 
     * @return A list with the transactions in the interval.
     */
    public void loadTransactions(
        @Nullable String currency,
        @Nullable Sort sort,
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset, 
        @Nullable Boolean showSenders,
        Callback<List<Transaction>> callback);

    /**
     * Get a list of transactions of the account. Accepts only filtering by Index.
     * <p>
     * https://api.exchange.cryptomkt.com/#load-transactions
     * 
     * @param currency Currency code to get the transaction history
     * @param pagination A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @param showSenders Optional. If True, show the sender address for payins.
     * @param callback A Callback to call with the result data. 
     * 
     * @return A list with the transactions in the interval.
     */
    public void loadTransactions(
        @Nullable String currency, 
        @Nullable Pagination pagination, 
        @Nullable Boolean showSenders, 
        Callback<List<Transaction>> callback);


    /**
     * Subscribe to a feed of trading events of the account
     * 
     * https://api.exchange.cryptomkt.com/#subscribe-to-reports
     * 
     * @param callback Optional. A Callback to call with the result data. 
     * 
     * @return transactions of the account as feed for the callback
     */
    public void subscribeToTransactions(Callback<Transaction> callback, @Nullable Callback<Boolean> resultCallback);


    /**
     * unsubscribe to the transaction feed.
     * 
     * https://api.exchange.cryptomkt.com/#subscription-to-the-transactions
     * 
     * @param callback Optional. A Callback to call with the result data. 
     * 
     * @return The operation result. true if success
     */
    public void unsubscribeToTransactions(@Nullable Callback<Boolean> callback);
}
