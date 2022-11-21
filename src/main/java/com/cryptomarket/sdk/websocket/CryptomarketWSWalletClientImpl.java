package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.List;
import java.util.Map;

import com.cryptomarket.params.NotificationType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Sort;
import com.cryptomarket.params.SortBy;
import com.cryptomarket.params.TransactionStatus;
import com.cryptomarket.params.TransactionSubtype;
import com.cryptomarket.params.TransactionType;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Transaction;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;

public class CryptomarketWSWalletClientImpl extends AuthClient implements CryptomarketWSWalletClient {

  /**
   * creates a new CryptomarketWSWalletClient instance
   *
   * @param apiKey    public API key
   * @param apiSecret secret API key
   * @param window    Maximum difference between the send of the request and the
   *                  moment of request processing in milliseconds
   * @throws IOException
   */
  public CryptomarketWSWalletClientImpl(String apiKey, String apiSecret, Integer window) throws IOException {
    super("wss://api.exchange.cryptomkt.com/api/3/ws/wallet", apiKey, apiSecret, window);
    Map<String, String> subsKeys = this.getSubscritpionKeys();
    // transactions
    subsKeys.put("subscribe_transactions", "transactions");
    subsKeys.put("unsubscribe_transactions", "transactions");
    subsKeys.put("transaction_update", "transactions");
    // balance
    subsKeys.put("subscribe_wallet_balances", "balance");
    subsKeys.put("unsubscribe_wallet_balances", "balance");
    subsKeys.put("wallet_balances", "balance");
    subsKeys.put("wallet_balance_update", "balance");
  }

  /**
   * creates a new CryptomarketWSWalletClient instance, using the default
   * window of 10 seconds
   *
   * @param apiKey    public API key
   * @param apiSecret secret API key
   * @throws IOException
   */
  public CryptomarketWSWalletClientImpl(String apiKey, String apiSecret) throws IOException {
    this(apiKey, apiSecret, 0);
  }

  @Override
  public void subscribeToTransactions(BiConsumer<Transaction, NotificationType> notificationBiConsumer,
      BiConsumer<Boolean, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        Transaction transaction = adapter.objectFromValue(response.getParams(), Transaction.class);
        notificationBiConsumer.accept(transaction, NotificationType.UPDATE);
      }
    };
    Interceptor resultInterceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Boolean.class);
    sendSubscription("subscribe_transactions", null, interceptor, resultInterceptor);
  }

  @Override
  public void unsubscribeToTransactions(BiConsumer<Boolean, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Boolean.class);
    sendUnsubscription("unsubscribe_transactions", null, interceptor);
  }

  @Override
  public void subscribeToWalletBalances(BiConsumer<List<Balance>, NotificationType> notificationBiConsumer,
      BiConsumer<Boolean, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        if (response.getMethod().equals("wallet_balances")) {
          List<Balance> balances = adapter.listFromValue(response.getParams(), Balance.class);
          notificationBiConsumer.accept(balances, NotificationType.SNAPSHOT);
        } else if (response.getMethod().equals("wallet_balance_update")) {
          Balance balance = adapter.objectFromValue(response.getParams(), Balance.class);
          notificationBiConsumer.accept(Arrays.asList(balance), NotificationType.UPDATE);
        }
      }
    };

    Interceptor resultInterceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Boolean.class);

    sendSubscription("subscribe_wallet_balances", null, interceptor, resultInterceptor);
  }

  @Override
  public void unsubscribeToWalletBalances(BiConsumer<Boolean, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Boolean.class);
    sendUnsubscription("unsubscribe_wallet_balances", null, interceptor);
  }

  @Override
  public void getWalletBalances(BiConsumer<List<Balance>, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseList(resultBiConsumer, Balance.class);
    sendById("wallet_balances", null, interceptor);
  }

  @Override
  public void getWalletBalanceOfCurrency(String currency,
      BiConsumer<Balance, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder paramsBuilder = new ParamsBuilder().currency(currency);
    Interceptor interceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Balance.class);
    sendById("wallet_balance", paramsBuilder.buildObjectMap(), interceptor);
  }

  @Override
  public void getTransactions(
      BiConsumer<List<Transaction>, CryptomarketSDKException> resultBiConsumer,
      List<TransactionType> types,
      List<TransactionSubtype> subtypes,
      List<TransactionStatus> statuses,
      List<String> currencies,
      List<String> transactionIDs,
      Sort sort,
      SortBy by,
      String from,
      String till,
      Integer IDFrom,
      Integer IDTill,
      Integer limit,
      Integer offset) {
    getTransactions(
        resultBiConsumer,
        new ParamsBuilder()
            .types(types)
            .subtypes(subtypes)
            .statuses(statuses)
            .currencies(currencies)
            .transactionIDs(transactionIDs)
            .sort(sort)
            .by(by)
            .from(from)
            .till(till)
            .IDFrom(IDFrom)
            .IDTill(IDTill)
            .limit(limit)
            .offset(offset));
  }

  @Override
  public void getTransactions(BiConsumer<List<Transaction>, CryptomarketSDKException> resultBiConsumer,
      ParamsBuilder paramsBuilder) {
    Interceptor interceptor = (resultBiConsumer == null) ? null
        : InterceptorFactory.newOfWSResponseList(resultBiConsumer, Transaction.class);
    sendById("get_transactions", (paramsBuilder == null) ? null : paramsBuilder.buildObjectMap(), interceptor);

  }
}
