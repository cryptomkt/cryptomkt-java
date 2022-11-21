package com.cryptomarket.sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cryptomarket.params.AccountType;
import com.cryptomarket.params.ArgNames;
import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.SortBy;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.Sort;
import com.cryptomarket.params.TimeInForce;
import com.cryptomarket.params.TransactionStatus;
import com.cryptomarket.params.TransactionSubtype;
import com.cryptomarket.params.TransactionType;
import com.cryptomarket.params.TransferType;
import com.cryptomarket.params.IdentifyBy;
import com.cryptomarket.params.OrderBuilder;
import com.cryptomarket.params.UseOffchain;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Address;
import com.cryptomarket.sdk.models.AmountLock;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.Price;
import com.cryptomarket.sdk.models.PriceHistory;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.SubAccount;
import com.cryptomarket.sdk.models.SubAccountBalances;
import com.cryptomarket.sdk.models.SubAccountSettings;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.models.TickerPrice;
import com.cryptomarket.sdk.models.Trade;
import com.cryptomarket.sdk.models.Transaction;

public class CryptomarketRestClientImpl implements CryptomarketRestClient {
  HttpClient httpClient;
  Adapter adapter = new Adapter();

  public CryptomarketRestClientImpl(String apiKey, String apiSecret) {
    // httpClient = new HttpClientImpl(apiKey, apiSecret);
    // httpClient = new HttpClientApacheWithBasic(apiKey, apiSecret);
    httpClient = new HttpClientImpl(apiKey, apiSecret);
  }

  public CryptomarketRestClientImpl() {
    this("", "");
  }

  // PUBLIC

  @Override
  public Map<String, Currency> getCurrencies(List<String> currencies) throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder()
        .currencies(currencies)
        .build();
    String jsonResponse = httpClient.publicGet("public/currency", params);
    return adapter.mapFromJson(jsonResponse, Currency.class);
  }

  @Override
  public Currency getCurrency(String currency) throws CryptomarketSDKException {
    String jsonResponse = httpClient.publicGet(
        String.format("public/currency/%s", currency),
        null);
    return adapter.objectFromJson(jsonResponse, Currency.class);
  }

  @Override
  public Map<String, Symbol> getSymbols(List<String> symbols)
      throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder().symbols(symbols).build();
    String jsonResponse = httpClient.publicGet("public/symbol", params);
    return adapter.mapFromJson(jsonResponse, Symbol.class);
  }

  @Override
  public Symbol getSymbol(String symbol) throws CryptomarketSDKException {
    String jsonResponse = httpClient.publicGet(
        String.format("public/symbol/%s", symbol),
        null);
    return adapter.objectFromJson(jsonResponse, Symbol.class);
  }

  @Override
  public Map<String, Ticker> getTickers(List<String> symbols)
      throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder().symbols(symbols).build();
    String jsonResponse = httpClient.publicGet("public/ticker", params);
    return adapter.mapFromJson(jsonResponse, Ticker.class);
  }

  @Override
  public Ticker getTicker(String symbol) throws CryptomarketSDKException {
    String jsonResponse = httpClient.publicGet(
        String.format("public/ticker/%s", symbol),
        null);
    return adapter.objectFromJson(jsonResponse, Ticker.class);
  }

  @Override
  public Map<String, Price> getPrices(String to, String from)
      throws CryptomarketSDKException {
    return getPrices(new ParamsBuilder().to(to).from(from));
  }

  @Override
  public Map<String, Price> getPrices(
      ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(ArgNames.TO));
    String jsonResponse = httpClient.publicGet(
        "public/price/rate",
        paramsBuilder.build());
    return adapter.mapFromJson(jsonResponse, Price.class);
  }

  @Override
  public Map<String, PriceHistory> getPricesHistory(
      String to,
      String from,
      String until,
      String since,
      Integer limit,
      Period period,
      Sort sort)
      throws CryptomarketSDKException {
    return getPricesHistory(new ParamsBuilder()
        .to(to)
        .from(from)
        .until(until)
        .since(since)
        .limit(limit)
        .period(period)
        .sort(sort));
  }

  @Override
  public Map<String, PriceHistory> getPricesHistory(
      ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(ArgNames.TO));
    String jsonResponse = httpClient.publicGet(
        "public/price/history",
        paramsBuilder.build());
    return adapter.mapFromJson(jsonResponse, PriceHistory.class);

  }

  @Override
  public Map<String, TickerPrice> getTickerPrices(List<String> symbols) throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder().symbols(symbols).build();
    String jsonResponse = httpClient.publicGet("public/price/ticker", params);
    return adapter.mapFromJson(jsonResponse, TickerPrice.class);
  }

  @Override
  public TickerPrice getTickerPriceOfSymbol(String symbol)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.publicGet(
        String.format("public/price/ticker/%s", symbol),
        null);
    return adapter.objectFromJson(jsonResponse, TickerPrice.class);
  }

  @Override
  public Map<String, List<PublicTrade>> getTrades(
      List<String> symbols,
      Sort sort,
      SortBy by,
      String from,
      String till,
      String limit)
      throws CryptomarketSDKException {
    return getTrades(new ParamsBuilder()
        .symbols(symbols)
        .sort(sort)
        .from(from)
        .till(till)
        .limit(limit)
        .by(by));
  }

  @Override
  public Map<String, List<PublicTrade>> getTrades(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.publicGet(
        "public/trades",
        paramsBuilder.build());
    return adapter.listMapFromJson(jsonResponse, PublicTrade.class);

  }

  @Override
  public List<PublicTrade> getTradesOfSymbol(
      String symbol,
      Sort sort,
      String from,
      String till,
      Integer limit,
      Integer offset)
      throws CryptomarketSDKException {
    return getTradesOfSymbol(new ParamsBuilder()
        .symbol(symbol)
        .sort(sort)
        .from(from)
        .till(till)
        .limit(limit)
        .offset(offset));
  }

  @Override
  public List<PublicTrade> getTradesOfSymbol(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(ArgNames.SYMBOL));
    String symbol = (String) paramsBuilder.remove(ArgNames.SYMBOL);
    String jsonResponse = httpClient.publicGet(
        String.format("public/trades/%s", symbol),
        paramsBuilder.build());
    return adapter.listFromJson(jsonResponse, PublicTrade.class);

  }

  @Override
  public Map<String, OrderBook> getOrderBooks(
      List<String> symbols, Integer limit)
      throws CryptomarketSDKException {
    return getOrderBooks(new ParamsBuilder()
        .symbols(symbols)
        .limit(limit));
  }

  @Override
  public Map<String, OrderBook> getOrderBooks(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.publicGet(
        "public/orderbook",
        paramsBuilder.build());
    return adapter.mapFromJson(jsonResponse, OrderBook.class);
  }

  @Override
  public OrderBook getOrderBookOfSymbol(String symbol, Integer limit)
      throws CryptomarketSDKException {
    return getOrderBookOfSymbol(new ParamsBuilder()
        .symbol(symbol)
        .limit(limit));
  }

  @Override
  public OrderBook getOrderBookOfSymbol(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(ArgNames.SYMBOL));
    String symbol = (String) paramsBuilder.remove(ArgNames.SYMBOL);
    String jsonResponse = httpClient.publicGet(
        String.format("public/orderbook/%s", symbol),
        paramsBuilder.build());
    return adapter.objectFromJson(jsonResponse, OrderBook.class);
  }

  @Override
  public OrderBook getOrderBookVolumeOfSymbol(String symbol, Integer volume)
      throws CryptomarketSDKException {
    return getOrderBookVolumeOfSymbol(new ParamsBuilder()
        .symbol(symbol)
        .volume(volume));
  }

  @Override
  public OrderBook getOrderBookVolumeOfSymbol(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.SYMBOL,
        ArgNames.VOLUME));
    String symbol = (String) paramsBuilder.remove(ArgNames.SYMBOL);
    String jsonResponse = httpClient.publicGet(
        String.format("public/orderbook/%s", symbol),
        paramsBuilder.build());
    return adapter.objectFromJson(jsonResponse, OrderBook.class);
  }

  @Override
  public Map<String, List<Candle>> getCandles(
      List<String> symbols,
      Period period,
      Sort sort,
      String from,
      String till,
      Integer limit,
      Integer offset)
      throws CryptomarketSDKException {
    return getCandles(new ParamsBuilder()
        .symbols(symbols)
        .period(period)
        .sort(sort)
        .from(from)
        .till(till)
        .limit(limit)
        .offset(offset));
  }

  @Override
  public Map<String, List<Candle>> getCandles(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.publicGet(
        "public/candles",
        paramsBuilder.build());
    return adapter.listMapFromJson(jsonResponse, Candle.class);
  }

  @Override
  public List<Candle> getCandlesOfSymbol(
      String symbol,
      Period period,
      Sort sort,
      String from,
      String till,
      Integer limit,
      Integer offset)
      throws CryptomarketSDKException {
    return getCandlesOfSymbol(new ParamsBuilder()
        .symbol(symbol)
        .period(period)
        .sort(sort)
        .from(from)
        .till(till)
        .limit(limit)
        .offset(offset));
  }

  @Override
  public List<Candle> getCandlesOfSymbol(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(ArgNames.SYMBOL));
    String symbol = (String) paramsBuilder.remove(ArgNames.SYMBOL);
    String jsonResponse = httpClient.publicGet(
        String.format("public/candles/%s", symbol),
        paramsBuilder.build());
    return adapter.listFromJson(jsonResponse, Candle.class);
  }

  // SPOT TRADING

  @Override
  public List<Balance> getSpotTradingBalances() throws CryptomarketSDKException {
    String jsonResponse = httpClient.get("spot/balance", null);
    return adapter.listFromJson(jsonResponse, Balance.class);
  }

  @Override
  public Balance getSpotTradingBalanceOfCurrency(String currency)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(String.format(
        "spot/balance/%s", currency),
        null);
    Balance balance = adapter.objectFromJson(jsonResponse, Balance.class);
    balance.setCurrency(currency);
    return balance;
  }

  @Override
  public List<Order> getAllActiveSpotOrders(String symbol)
      throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder()
        .symbol(symbol)
        .build();
    String jsonResponse = httpClient.get("spot/order", params);
    return adapter.listFromJson(jsonResponse, Order.class);
  }

  @Override
  public Order getActiveSpotOrder(String clientOrderId)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(
        String.format("spot/order/%s", clientOrderId),
        null);
    return adapter.objectFromJson(jsonResponse, Order.class);
  }

  @Override
  public Order createSpotOrder(
      String symbol,
      Side side,
      String quantity,
      String clientOrderID,
      OrderType orderType,
      String price,
      String stopPrice,
      TimeInForce timeInForce,
      String expireTime,
      Boolean strictValidate,
      Boolean postOnly,
      String takeRate,
      String makeRate) throws CryptomarketSDKException {
    return createSpotOrder(new ParamsBuilder()
        .symbol(symbol)
        .side(side)
        .quantity(quantity)
        .clientOrderID(clientOrderID)
        .orderType(orderType)
        .price(price)
        .stopPrice(stopPrice)
        .timeInForce(timeInForce)
        .expireTime(expireTime)
        .strictValidate(strictValidate)
        .postOnly(postOnly)
        .takeRate(takeRate)
        .makeRate(makeRate));
  }

  @Override
  public Order createSpotOrder(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.SYMBOL,
        ArgNames.SIDE,
        ArgNames.QUANTITY));
    String jsonResponse = httpClient.post(
        "spot/order",
        paramsBuilder.build());
    return adapter.objectFromJson(jsonResponse, Order.class);

  }

  @Override
  public Order createSpotOrder(OrderBuilder orderBuilder)
      throws CryptomarketSDKException {
    orderBuilder.checkRequired(Arrays.asList(
        ArgNames.SYMBOL,
        ArgNames.SIDE,
        ArgNames.QUANTITY));
    String jsonResponse = httpClient.post(
        "spot/order",
        orderBuilder.build());
    return adapter.objectFromJson(jsonResponse, Order.class);
  }

  @Override
  public List<Order> createSpotOrderList(
      ContingencyType contingencyType,
      List<OrderBuilder> orders,
      String orderListID)
      throws CryptomarketSDKException {
    List<Map<String, Object>> orderListData = new ArrayList<>();
    orders.forEach(orderBuilder -> orderListData.add(orderBuilder.buildObjectMap()));
    ParamsBuilder paramsBuilder = new ParamsBuilder()
        .contingencyType(contingencyType)
        .orderListID(orderListID)
        .orders(orderListData);
    String jsonResponse = httpClient.post("spot/order/list", paramsBuilder.build());
    return adapter.listFromJson(jsonResponse, Order.class);
  }

  @Override
  public Order replaceSpotOrder(
      String clientOrderID,
      String newClientOrderID,
      String quantity,
      String price,
      Boolean strictValidate) throws CryptomarketSDKException {
    return replaceSpotOrder(new ParamsBuilder()
        .newClientOrderID(newClientOrderID)
        .quantity(quantity)
        .price(price)
        .strictValidate(strictValidate));
  }

  @Override
  public Order replaceSpotOrder(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.CLIENT_ORDER_ID,
        ArgNames.NEW_CLIENT_ORDER_ID,
        ArgNames.QUANTITY));
    String clientOrderID = (String) paramsBuilder.remove(ArgNames.CLIENT_ORDER_ID);
    String jsonResponse = httpClient.patch(
        String.format("spot/order/%s", clientOrderID),
        paramsBuilder.build());
    return adapter.objectFromJson(jsonResponse, Order.class);

  }

  @Override
  public List<Order> cancelAllSpotOrders() throws CryptomarketSDKException {
    String jsonResponse = httpClient.delete("spot/order", null);
    return adapter.listFromJson(jsonResponse, Order.class);
  }

  @Override
  public Order cancelSpotOrder(String clientOrderId)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.delete(
        String.format("spot/order/%s", clientOrderId),
        null);
    return adapter.objectFromJson(jsonResponse, Order.class);
  }

  @Override
  public List<Commission> getAllTradingCommissions() throws CryptomarketSDKException {
    String jsonResponse = httpClient.get("spot/fee", null);
    return adapter.listFromJson(jsonResponse, Commission.class);
  }

  @Override
  public Commission getTradingCommission(String symbol)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(
        String.format("spot/fee/%s", symbol),
        null);
    Commission commission = adapter.objectFromJson(jsonResponse, Commission.class);
    commission.setSymbol(symbol);
    return commission;
  }

  // TRADING HISTORY

  @Override
  public List<Order> getSpotOrderHistory(
      String symbol,
      Sort sort,
      SortBy by,
      String from,
      String till,
      Integer limit,
      Integer offset) throws CryptomarketSDKException {
    return getSpotOrderHistory(new ParamsBuilder()
        .symbol(symbol)
        .sort(sort)
        .by(by)
        .from(from)
        .till(till)
        .limit(limit)
        .offset(offset));
  }

  @Override
  public List<Order> getSpotOrderHistory(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(
        "spot/history/order",
        paramsBuilder.build());
    return adapter.listFromJson(jsonResponse, Order.class);

  }

  @Override
  public List<Trade> getSpotTradesHistory(
      String symbol,
      Sort sort,
      SortBy by,
      String from,
      String till,
      Integer limit,
      Integer offset)
      throws CryptomarketSDKException {
    return getSpotTradesHistory(new ParamsBuilder()
        .symbol(symbol)
        .sort(sort)
        .by(by)
        .from(from)
        .till(till)
        .limit(limit)
        .offset(offset));
  }

  @Override
  public List<Trade> getSpotTradesHistory(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(
        "spot/history/trade",
        paramsBuilder.build());
    return adapter.listFromJson(jsonResponse, Trade.class);
  }

  // WALLET MANAGEMENT

  @Override
  public List<Balance> getWalletBalances() throws CryptomarketSDKException {
    String jsonResponse = httpClient.get("wallet/balance", null);
    return adapter.listFromJson(jsonResponse, Balance.class);
  }

  @Override
  public Balance getWalletBalanceOfCurrency(String currency) throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(String.format("wallet/balance/%s", currency), null);
    return adapter.objectFromJson(jsonResponse, Balance.class);
  }

  @Override
  public List<Address> getDepositCryptoAddresses() throws CryptomarketSDKException {
    String jsonResponse = httpClient.get("wallet/crypto/address", null);
    return adapter.listFromJson(jsonResponse, Address.class);
  }

  @Override
  public Address getDepositCryptoAddressOfCurrency(String currency) throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder().currency(currency).build();
    String jsonResponse = httpClient.get("wallet/crypto/address", params);
    List<Address> addresses = adapter.listFromJson(jsonResponse, Address.class);
    return addresses.get(0);
  }

  @Override
  public Address createDepositCryptoAddress(String currency)
      throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder().currency(currency).build();
    String jsonResponse = httpClient.post(
        "wallet/crypto/address",
        params);
    return adapter.objectFromJson(jsonResponse, Address.class);
  }

  @Override
  public List<Address> getLast10DepositCryptoAddresses(String currency)
      throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder()
        .currency(currency)
        .build();
    String jsonResponse = httpClient.get(
        "wallet/crypto/address/recent-deposit",
        params);
    return adapter.listFromJson(jsonResponse, Address.class);
  }

  @Override
  public List<Address> getLast10WithdrawalCryptoAddresses(String currency)
      throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder()
        .currency(currency)
        .build();
    String jsonResponse = httpClient.get(
        "wallet/crypto/address/recent-withdraw",
        params);
    return adapter.listFromJson(jsonResponse, Address.class);
  }

  @Override
  public String withdrawCrypto(
      String currency,
      String amount,
      String address,
      String paymentId,
      Boolean includeFee,
      Boolean autoCommit,
      UseOffchain useOffchain,
      String publicComment) throws CryptomarketSDKException {
    return withdrawCrypto(new ParamsBuilder()
        .currency(currency)
        .amount(amount)
        .address(address)
        .paymentID(paymentId)
        .includeFee(includeFee)
        .autoCommit(autoCommit)
        .useOffchain(useOffchain)
        .publicComment(publicComment));
  }

  @Override
  public String withdrawCrypto(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.CURRENCY,
        ArgNames.AMOUNT,
        ArgNames.ADDRESS));
    String jsonResponse = httpClient.post(
        "wallet/crypto/withdraw",
        paramsBuilder.build());
    return adapter.objectFromJsonValue(jsonResponse, "id", String.class);

  }

  @Override
  public boolean withdrawCryptoCommit(String transactionId)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.put(String.format(
        "wallet/crypto/withdraw/%s", transactionId),
        null);
    return adapter.objectFromJsonValue(jsonResponse, "result", boolean.class);
  }

  @Override
  public boolean withdrawCryptoRollback(String transactionId)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.delete(String.format(
        "wallet/crypto/withdraw/%s", transactionId),
        null);
    return adapter.objectFromJsonValue(jsonResponse, "result", boolean.class);
  }

  @Override
  public String getEstimateWithdrawalFee(String currency, String amount)
      throws CryptomarketSDKException {
    return getEstimateWithdrawalFee(new ParamsBuilder()
        .currency(currency)
        .amount(amount));
  }

  @Override
  public String getEstimateWithdrawalFee(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.CURRENCY,
        ArgNames.AMOUNT));
    String jsonResponse = httpClient.get(
        "wallet/crypto/fee/estimate",
        paramsBuilder.build());
    return adapter.objectFromJsonValue(jsonResponse, "fee", String.class);

  }

  @Override
  public List<String> convertBetweenCurrencies(
      String fromCurrency, String toCurrency,
      String amount)
      throws CryptomarketSDKException {
    return convertBetweenCurrencies(new ParamsBuilder()
        .fromCurrency(fromCurrency)
        .toCurrency(toCurrency)
        .amount(amount));
  }

  @Override
  public List<String> convertBetweenCurrencies(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.FROM_CURRENCY,
        ArgNames.TO_CURRENCY,
        ArgNames.AMOUNT));
    String jsonResponse = httpClient.post(
        "wallet/convert",
        paramsBuilder.build());
    return adapter.listFromJsonValue(jsonResponse, "result", String.class);

  }

  @Override
  public boolean cryptoAddressBelongsToCurrentAccount(String address)
      throws CryptomarketSDKException {
    Map<String, String> params = new ParamsBuilder()
        .address(address)
        .build();
    String jsonResponse = httpClient.get(
        "wallet/crypto/address/check-mine",
        params);
    return adapter.objectFromJsonValue(jsonResponse, "result", boolean.class);
  }

  @Override
  public String transferBetweenWalletAndExchange(
      String currency,
      String amount,
      AccountType source,
      AccountType destination) throws CryptomarketSDKException {
    return transferBetweenWalletAndExchange(new ParamsBuilder()
        .currency(currency)
        .amount(amount)
        .source(source)
        .destination(destination));
  }

  @Override
  public String transferBetweenWalletAndExchange(
      ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.CURRENCY,
        ArgNames.AMOUNT,
        ArgNames.SOURCE,
        ArgNames.DESTINATION));
    String jsonResponse = httpClient.post(
        "wallet/transfer",
        paramsBuilder.build());
    List<String> response = adapter.listFromJson(jsonResponse, String.class);
    if (response.size() != 1)
      throw new CryptomarketSDKException("Invalid response format: " + response.toString());
    return response.get(0);
  }

  @Override
  public String transferMoneyToAnotherUser(
      String currency,
      String amount,
      IdentifyBy transferBy,
      String identifier)
      throws CryptomarketSDKException {
    return transferMoneyToAnotherUser(new ParamsBuilder()
        .currency(currency)
        .amount(amount)
        .transferBy(transferBy)
        .identifier(identifier));
  }

  @Override
  public String transferMoneyToAnotherUser(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.CURRENCY,
        ArgNames.AMOUNT,
        ArgNames.BY,
        ArgNames.IDENTIFIER));
    String jsonResponse = httpClient.post(
        "wallet/internal/withdraw",
        paramsBuilder.build());
    return adapter.objectFromJsonValue(jsonResponse, "result", String.class);
  }

  @Override
  public List<Transaction> getTransactionHistory(
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
      Integer offset,
      Boolean showSenders) throws CryptomarketSDKException {
    return getTransactionHistory(new ParamsBuilder()
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
        .offset(offset)
        .showSenders(showSenders));
  }

  @Override
  public List<Transaction> getTransactionHistory(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(
        "wallet/transactions",
        paramsBuilder.build());
    return adapter.listFromJson(jsonResponse, Transaction.class);
  }

  @Override
  public Transaction getTransaction(String id) throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(String.format(
        "wallet/transactions/%s", id),
        null);
    return adapter.objectFromJson(jsonResponse, Transaction.class);
  }

  @Override
  public Boolean checkIfOffchainIsAvailable(
      String currency,
      String address,
      String paymentID) throws CryptomarketSDKException {
    return checkIfOffchainIsAvailable(new ParamsBuilder()
        .currency(currency)
        .address(address)
        .paymentID(paymentID));
  }

  @Override
  public Boolean checkIfOffchainIsAvailable(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    paramsBuilder.checkRequired(Arrays.asList(
        ArgNames.CURRENCY,
        ArgNames.ADDRESS));
    String jsonResponse = httpClient.post(
        "wallet/crypto/check-offchain-available",
        paramsBuilder.build());
    return adapter.objectFromJsonValue(jsonResponse, "result", Boolean.class);

  }

  @Override
  public List<AmountLock> getAmountLocks(
      String currency,
      Boolean active,
      Integer limit,
      Integer offset,
      String from,
      String till) throws CryptomarketSDKException {
    return getAmountLocks(new ParamsBuilder()
        .currency(currency)
        .active(active)
        .limit(limit)
        .offset(offset)
        .from(from)
        .till(till));
  }

  @Override
  public List<AmountLock> getAmountLocks(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(
        "wallet/airdrops",
        paramsBuilder.build());
    return adapter.listFromJson(jsonResponse, AmountLock.class);

  }

  @Override
  public List<SubAccount> getSubAccountList() throws CryptomarketSDKException {
    String jsonResponse = httpClient.get("sub-account", null);
    return adapter.listFromJson(jsonResponse, SubAccount.class);
  }

  @Override
  public Boolean freezeSubAccount(List<String> subAccountIDs) throws CryptomarketSDKException {
    ParamsBuilder params = new ParamsBuilder()
        .subAccountIDs(subAccountIDs);
    String jsonResponse = httpClient.get("sub-account/freeze", params.build());
    return adapter.objectFromJsonValue(jsonResponse, "result", Boolean.class);
  }

  @Override
  public Boolean activateSubAccount(List<String> subAccountIDs) throws CryptomarketSDKException {
    ParamsBuilder params = new ParamsBuilder()
        .subAccountIDs(subAccountIDs);
    String jsonResponse = httpClient.get("sub-account/activate", params.build());
    return adapter.objectFromJsonValue(jsonResponse, "result", Boolean.class);
  }

  @Override
  public String transferFunds(String subAccountID, String amount, String currency, TransferType transferType)
      throws CryptomarketSDKException {
    ParamsBuilder params = new ParamsBuilder()
        .subAccountID(subAccountID)
        .amount(amount)
        .currency(currency)
        .transferType(transferType);
    String jsonResponse = httpClient.get("sub-account/transfer", params.build());
    return adapter.objectFromJsonValue(jsonResponse, "result", String.class);
  }

  @Override
  public List<SubAccountSettings> getACLSettings(List<String> subAccountIDs) throws CryptomarketSDKException {
    ParamsBuilder params = new ParamsBuilder()
        .subAccountIDs(subAccountIDs);
    String jsonResponse = httpClient.get("sub-account/acl", params.build());
    return adapter.listFromJson(jsonResponse, SubAccountSettings.class);
  }

  @Override
  public List<SubAccountSettings> changeACLSettings(List<String> subAccountIDs, SubAccountSettings settings)
      throws CryptomarketSDKException {
    ParamsBuilder params = new ParamsBuilder()
        .subAccountIDs(subAccountIDs)
        .depositAddressGenerationEnabled(settings.getDepositAddressGenerationEnabled())
        .withdrawEnabled(settings.getWithdrawEnabled())
        .createdAt(settings.getCreatedAt())
        .description(settings.getDescription())
        .updatedAt(settings.getUpdatedAt());
    String jsonResponse = httpClient.get("sub-account/acl", params.build());
    return adapter.listFromJson(jsonResponse, SubAccountSettings.class);
  }

  @Override
  public SubAccountBalances getSubAccountBalance(String subAccountID) throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(String.format("sub-account/balance/%s", subAccountID), null);
    return adapter.objectFromJson(jsonResponse, SubAccountBalances.class);
  }

  @Override
  public String getSubAccountCryptoAddress(String subAccountID, String currency) throws CryptomarketSDKException {
    String jsonResponse = httpClient.get(
        String.format("sub-account/address/%s/%s", subAccountID, currency),
        null);
    class Address {
      String address;
    }
    Address address = adapter.objectFromJsonValue(jsonResponse, "result", Address.class);
    return address.address;
  }
}
