package com.cryptomarket.sdk.rest;

import java.io.Closeable;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.cryptomarket.params.AccountType;
import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.IdentifyBy;
import com.cryptomarket.params.OrderBuilder;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.Sort;
import com.cryptomarket.params.SortBy;
import com.cryptomarket.params.SubAccountTransferType;
import com.cryptomarket.params.TimeInForce;
import com.cryptomarket.params.TransactionStatus;
import com.cryptomarket.params.TransactionSubtype;
import com.cryptomarket.params.TransactionType;
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

/**
 * Rest Client Interface for cryptomarket API V3.
 */
public interface CryptomarketRestClient extends Closeable {

  /**
   * Changes the user credentials used for authentication in calls
   *
   * @param apiKey    the user public key used in new calls
   * @param apiSecret the user secret key used in new calls
   */
  public void changeCredentials(String apiKey, String apiSecret);

  /// PUBLIC CALLS///

  /**
   * Get a map of all currencies or specified currencies
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#currencies
   *
   * @param currencies       Optional. A list of currencies ids
   * @param preferredNetwork Optional. Code of the default network for currencies.
   * @return A map of available currencies. indexed by currency id
   * @throws CryptomarketSDKException
   */
  public Map<String, Currency> getCurrencies(
      @Nullable List<String> currencies,
      @Nullable String preferredNetwork) throws CryptomarketSDKException;

  /**
   * Get the data of a currency
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#currencies
   *
   * @param currency A currency id
   * @return A currency
   * @throws CryptomarketSDKException
   */
  public Currency getCurrency(String currency) throws CryptomarketSDKException;

  /**
   * Get a map of all symbols or for specified symbols
   * <p>
   * A symbol is the combination of the base currency (first one) and quote
   * currency (second one)
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#symbols
   *
   * @param symbols Optional. A list of symbol ids
   * @return A map of symbols traded on the exchange, indexed by symbol id
   * @throws CryptomarketSDKException
   */
  public Map<String, Symbol> getSymbols(@Nullable List<String> symbols) throws CryptomarketSDKException;

  /**
   * Get a symbol by its id
   * <p>
   * A symbol is the combination of the base currency (first one) and quote
   * currency (second one)
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#symbols
   *
   * @param symbol A symbol id
   * @return A symbol traded on the exchange
   * @throws CryptomarketSDKException
   */
  public Symbol getSymbol(String symbol) throws CryptomarketSDKException;

  /**
   * Get a map of tickers for all symbols or for specified symbols
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#tickers
   *
   * @param symbols Optional. A list of symbol ids
   * @return A map of symbols traded on the exchange, indexed by symbol id
   * @throws CryptomarketSDKException
   */
  public Map<String, Ticker> getTickers(@Nullable List<String> symbols) throws CryptomarketSDKException;

  /**
   * Get the ticker of a symbol
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#tickers
   *
   * @param symbol A symbol id
   * @return A symbol traded on the exchange
   * @throws CryptomarketSDKException
   */
  public Ticker getTicker(String symbol) throws CryptomarketSDKException;

  /**
   * Get a map of quotation prices of currencies
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#prices
   *
   * @param to   Target currency code
   * @param from Optional. Source currency rate
   * @return A map of quotation prices of currencies, indexed by source currency
   *         code
   * @throws CryptomarketSDKException
   */
  public Map<String, Price> getPrices(String to, @Nullable String from) throws CryptomarketSDKException;

  /**
   * @see #getPrices(String to,String from)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Map<String, Price> getPrices(ParamsBuilder paramsBuilder) throws CryptomarketSDKException;

  /**
   * Get quotation prices history
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#prices
   *
   * @param to     Target currency code
   * @param from   Optional. Source currency rate
   * @param period Optional. A valid tick interval. 'M1' (one minute), 'M3', 'M5',
   *               'M15', 'M30', 'H1' (one hour), 'H4', 'D1' (one day), 'D7', '1M'
   *               (one month). Default is 'M30'
   * @param sort   Optional. Sort direction. 'ASC' or 'DESC'. Default is 'DESC'
   * @param since  Optional. Initial value of the queried interval
   * @param until  Optional. Last value of the queried interval
   * @param limit  Optional. Prices per currency pair. Defaul is 1. Min is 1. Max
   *               is 1000
   * @return A map of quotation prices of currencies, indexed by source currency
   *         code
   * @throws CryptomarketSDKException
   */
  public Map<String, PriceHistory> getPricesHistory(
      String to,
      @Nullable String from,
      @Nullable String until,
      @Nullable String since,
      @Nullable Integer limit,
      @Nullable Period period,
      @Nullable Sort sort)
      throws CryptomarketSDKException;

  /**
   * @see #getPricesHistory(String, String, String, String, Integer, Period, Sort)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Map<String, PriceHistory> getPricesHistory(ParamsBuilder paramsBuilder) throws CryptomarketSDKException;

  /**
   * Get a map of the ticker's last prices for all symbols or for the specified
   * symbols
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#prices
   *
   * @param symbols Optional. A list of symbol ids
   * @return A map of ticker prices of currencies, indexed by symbol
   * @throws CryptomarketSDKException
   */
  public Map<String, TickerPrice> getTickerLastPrices(@Nullable List<String> symbols)
      throws CryptomarketSDKException;

  /**
   * Get ticker's last prices of a symbol
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#prices
   *
   * @param symbol A symbol id
   * @return The ticker's last price of a symbol
   * @throws CryptomarketSDKException
   */
  public TickerPrice getTickerLastPriceBySymbol(String symbol)
      throws CryptomarketSDKException;

  /**
   * Get a map of trades for all symbols or for specified symbols
   * <p>
   * 'from' param and 'till' param must have the same format, both id or both
   * timestamp
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#trades
   *
   * @param symbols Optional. A list of symbol ids
   * @param by      Optional. Sorting parameter. 'id' or 'timestamp'. Default is
   *                'timestamp'
   * @param sort    Optional. Sort direction. 'ASC' or 'DESC'. Default is 'DESC'
   * @param from    Optional. Initial value of the queried interval
   * @param till    Optional. Last value of the queried interval
   * @param limit   Optional. Prices per currency pair. Defaul is 10. Min is 1.
   *                Max is 1000
   * @return A map with a list of trades for each symbol of the query. Indexed by
   *         symbol
   * @throws CryptomarketSDKException
   */
  public Map<String, List<PublicTrade>> getTrades(
      @Nullable List<String> symbols,
      @Nullable Sort sort,
      @Nullable SortBy by,
      @Nullable String from,
      @Nullable String till,
      @Nullable String limit)
      throws CryptomarketSDKException;

  /**
   * @see #getTrades(List, Sort, SortBy, String, String, String)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Map<String, List<PublicTrade>> getTrades(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get trades of a symbol
   * <p>
   * 'from' param and 'till' param must have the same format, both id or both
   * timestamp
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#trades
   *
   * @param symbol A symbol id
   * @param by     Optional. Sorting parameter. 'id' or 'timestamp'. Default is
   *               'timestamp'
   * @param sort   Optional. Sort direction. 'ASC' or 'DESC'. Default is 'DESC'
   * @param from   Optional. Initial value of the queried interval
   * @param till   Optional. Last value of the queried interval
   * @param limit  Optional. Prices per currency pair. Defaul is 10. Min is 1. Max
   *               is 1000
   * @param offset Optional. Default is 0. Min is 0. Max is 100000
   * @return A list of trades of the symbol
   * @throws CryptomarketSDKException
   */
  public List<PublicTrade> getTradesBySymbol(
      String symbol,
      @Nullable Sort sort,
      @Nullable SortBy by,
      @Nullable String from,
      @Nullable String till,
      @Nullable Integer limit,
      @Nullable Integer offset)
      throws CryptomarketSDKException;

  /**
   * @see #getTradesBySymbol(String, Sort, SortBy, String, String, Integer,
   *      Integer)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public List<PublicTrade> getTradesBySymbol(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get a map of orderbooks for all symbols or for the specified symbols
   * <p>
   * An Order Book is an electronic list of buy and sell orders for a specific
   * symbol, structured by price level
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#order-books
   *
   * @param symbols Optional. A list of symbol ids
   * @param depth   Optional. Order Book depth. Default value is 10. Set to 0 to
   *                view the full Order Book
   * @return A map with the order book for each queried symbol. indexed by symbol
   * @throws CryptomarketSDKException
   */
  public Map<String, OrderBook> getOrderBooks(
      @Nullable List<String> symbols,
      @Nullable Integer depth)
      throws CryptomarketSDKException;

  /**
   * @see #getOrderBooks(List, Integer)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Map<String, OrderBook> getOrderBooks(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get order book of a symbol
   * <p>
   * An Order Book is an electronic list of buy and sell orders for a specific
   * symbol, structured by price level
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#order-books
   *
   * @param symbol A symbol id
   * @param depth  Optional. Order Book depth. Default value is 100. Set to 0 to
   *               view the full Order Book
   * @return The order book of the symbol
   * @throws CryptomarketSDKException
   */
  public OrderBook getOrderBookBySymbol(String symbol, @Nullable Integer depth) throws CryptomarketSDKException;

  /**
   * @see #getOrderBookBySymbol(String, Integer)
   * @param paramsBuilder
   * @return
   * @throws CryptomarketSDKException
   */
  public OrderBook getOrderBookBySymbol(ParamsBuilder paramsBuilder) throws CryptomarketSDKException;

  /**
   * Get order book of a symbol with the desired volume for market depth search
   * <p>
   * An Order Book is an electronic list of buy and sell orders for a specific
   * symbol, structured by price level
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#order-books
   *
   * @param symbol A symbol id
   * @param volume Optional. Desired volume for market depth search
   * @return The order book of the symbol
   * @throws CryptomarketSDKException
   */
  public OrderBook getOrderBookVolumeBySymbol(String symbol, Integer volume) throws CryptomarketSDKException;

  /**
   * @see #getOrderBookVolumeBySymbol(String, Integer)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public OrderBook getOrderBookVolumeBySymbol(ParamsBuilder paramsBuilder) throws CryptomarketSDKException;

  /**
   * Get a map of candles for all symbols or for specified symbols
   * <p>
   * Candels are used for OHLC representation
   * <p>
   * The result contains candles with non-zero volume only (no trades = no
   * candles)
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#candles
   *
   * @param symbols A list of symbols
   * @param period  Optional. A valid tick interval. 'M1' (one minute), 'M3',
   *                'M5',
   *                'M15', 'M30', 'H1' (one hour), 'H4', 'D1' (one day), 'D7',
   *                '1M'
   *                (one month). Default is 'M30'
   * @param sort    Optional. Sort direction. 'ASC' or 'DESC'. Default is 'DESC'
   * @param from    Optional. Initial value of the queried interval. As DateTime
   * @param till    Optional. Last value of the queried interval. As DateTime
   * @param limit   Optional. Prices per currency pair. Defaul is 10. Min is 1.
   *                Max
   *                is 1000
   * @return A map with a list of candles for each symbol of the query. indexed by
   *         symbol
   * @throws CryptomarketSDKException
   */
  public Map<String, List<Candle>> getCandles(
      @Nullable List<String> symbols,
      @Nullable Period period,
      @Nullable Sort sort,
      @Nullable String from,
      @Nullable String till,
      @Nullable Integer limit)
      throws CryptomarketSDKException;

  /**
   * @see #getCandles(List, Period, Sort, String, String, Integer)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Map<String, List<Candle>> getCandles(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get candles of a symbol
   * <p>
   * Candels are used for OHLC representation
   * <p>
   * The result contains candles with non-zero volume only (no trades = no
   * candles)
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#candles
   *
   * @param symbol A symbol id
   * @param period Optional. A valid tick interval. 'M1' (one minute), 'M3', 'M5',
   *               'M15', 'M30', 'H1' (one hour), 'H4', 'D1' (one day), 'D7', '1M'
   *               (one month). Default is 'M30'
   * @param sort   Optional. Sort direction. 'ASC' or 'DESC'. Default is 'DESC'
   * @param from   Optional. Initial value of the queried interval. As DateTime
   * @param till   Optional. Last value of the queried interval. As DateTime
   * @param limit  Optional. Prices per currency pair. Defaul is 100. Min is 1.
   *               Max is 1000
   * @param offset Optional. Default is 0. Min is 0. Max is 100000
   * @return A list of candles of a symbol
   * @throws CryptomarketSDKException
   */
  public List<Candle> getCandlesBySymbol(
      String symbol,
      @Nullable Period period,
      @Nullable Sort sort,
      @Nullable String from,
      @Nullable String till,
      @Nullable Integer limit,
      @Nullable Integer offset)
      throws CryptomarketSDKException;

  /**
   * @see #getCandlesBySymbol(String, Period, Sort, String, String, Integer,
   *      Integer)
   * @param paramsBuilder
   * @return
   * @throws CryptomarketSDKException
   */
  public List<Candle> getCandlesBySymbol(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /// AUTHENTICATED CALLS ///

  // SPOT TRADING

  /**
   * Get the user's spot trading balance for all currencies with balance
   * <p>
   * Requires the "Orderbook, History, Trading balance" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-spot-trading-balance
   *
   * @return A list of spot trading balances
   * @throws CryptomarketSDKException
   */
  public List<Balance> getSpotTradingBalances() throws CryptomarketSDKException;

  /**
   * Get the user spot trading balance of a currency
   * <p>
   * Requires the "Orderbook, History, Trading balance" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-spot-trading-balance
   *
   * @param currency The currency code to query the balance
   * @return the spot trading balance of a currency
   * @throws CryptomarketSDKException
   */
  public Balance getSpotTradingBalanceByCurrency(String currency) throws CryptomarketSDKException;

  /**
   * Get the user's active spot orders
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-all-active-spot-orders
   *
   * @param symbol Optional. A symbol for filtering the active spot orders
   * @return A list of orders
   * @throws CryptomarketSDKException
   */
  public List<Order> getAllActiveSpotOrders(@Nullable String symbol) throws CryptomarketSDKException;

  /**
   * Get an active spot order by its client order id
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-active-spot-orders
   *
   * @param clientOrderId The client order id of the order
   * @return A spot order of the account
   * @throws CryptomarketSDKException
   */
  public Order getActiveSpotOrder(String clientOrderId) throws CryptomarketSDKException;

  /**
   * Creates a new spot order
   * <p>
   * For fee, for price accuracy and quantity, and for order status information
   * see the api docs
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#create-new-spot-order
   *
   * @param symbol         Trading symbol
   * @param side           Either 'buy' or 'sell'
   * @param quantity       Order quantity
   * @param clientOrderId  Optional. If given must be unique within the trading
   *                       day, including all active orders. If not given, is
   *                       generated by the server
   * @param orderType      Optional. 'limit', 'market', 'stopLimit', 'stopMarket',
   *                       'takeProfitLimit' or 'takeProfitMarket'. Default is
   *                       'limit'
   * @param timeInForce    Optional. 'GTC', 'IOC', 'FOK', 'Day', 'GTD'. Default to
   *                       'GTC'
   * @param price          Optional. Required for 'limit' and 'stopLimit'. limit
   *                       price of the order
   * @param stopPrice      Optional. Required for 'stopLimit' and 'stopMarket'
   *                       orders. stop price of the order
   * @param expireTime     Optional. Required for orders with timeInForce = GDT
   * @param strictValidate Optional. If False, the server rounds half down for
   *                       tickerSize and quantityIncrement. Example of ETHBTC:
   *                       tickSize = '0.000001', then price '0.046016' is valid,
   *                       '0.0460165' is invalid
   * @param postOnly       Optional. If True, your post_only order causes a match
   *                       with a pre-existing order as a taker, then the order
   *                       will be cancelled
   * @param takeRate       Optional. Liquidity taker fee, a fraction of order
   *                       volume, such as 0.001 (for 0.1% fee). Can only increase
   *                       the fee. Used for fee markup.
   * @param makeRate       Optional. Liquidity provider fee, a fraction of order
   *                       volume, such as 0.001 (for 0.1% fee). Can only increase
   *                       the fee. Used for fee markup.
   * @return A new spot order
   * @throws CryptomarketSDKException
   */
  public Order createSpotOrder(
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
      @Nullable Boolean postOnly,
      @Nullable String takeRate,
      @Nullable String makeRate)
      throws CryptomarketSDKException;

  /**
   * @see #createSpotOrder(String, Side, String, String, OrderType, String,
   *      String, TimeInForce, String, Boolean, Boolean, String, String)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Order createSpotOrder(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * @see #createSpotOrder(String, Side, String, String, OrderType, String,
   *      String, TimeInForce, String, Boolean, Boolean, String, String)
   * @param orderBuilder
   * @return
   * @throws CryptomarketSDKException
   */
  public Order createSpotOrder(OrderBuilder orderBuilder)
      throws CryptomarketSDKException;

  /**
   * creates a list of spot orders
   * <p>
   * <b>Types or contingency:</b>
   * <ul>
   * <li>ContingencyType.ALL_OR_NONE (AON)
   * <li>ContingencyType.ONE_CANCEL_OTHER (OCO)
   * <li>ContingencyType.ONE_TRIGGER_OTHER (OTO)
   * <li>ContingencyType.ONE_TRIGGER_ONE_CANCEL_OTHER (OTOCO)
   * </ul>
   * <p>
   * <b>Restriction in the number of orders:</b>
   * <ul>
   * <li>An AON list must have 2 or 3 orders
   * <li>An OCO list must have 2 or 3 orders
   * <li>An OTO list must have 2 or 3 orders
   * <li>An OTOCO must have 3 or 4 orders
   * </ul>
   * <p>
   * <b>Symbol restrictions:</b>
   * <ul>
   * <li>For an AON order list, the symbol code of orders must be unique for each
   * order in the list.
   * <li>For an OCO order list, there are no symbol code restrictions.
   * <li>For an OTO order list, there are no symbol code restrictions.
   * <li>For an OTOCO order list, the symbol code of orders must be the same for
   * all orders in the list (placing orders in different order books is not
   * supported).
   * </ul>
   * <p>
   * <b> OrderType restrictions:</b>
   * <ul>
   * <li>For an AON order list, orders must be OrderType.LIMIT or OrderType.Market
   * <li>For an OCO order list, orders must be OrderType.LIMIT,
   * OrderType.STOP_LIMIT,
   * OrderType.STOP_MARKET, OrderType.TAKE_PROFIT_LIMIT or
   * OrderType.TAKE_PROFIT_MARKET.
   * <li>An OCO order list cannot include more than one limit order (the same
   * applies to secondary orders in an OTOCO order list).
   * <li>For an OTO order list, there are no order type restrictions.
   * <li>For an OTOCO order list, the first order must be OrderType.LIMIT,
   * OrderType.MARKET, OrderType.STOP_LIMIT, OrderType.STOP_MARKET,
   * OrderType.TAKE_PROFIT_LIMIT or OrderType.TAKE_PROFIT_MARKET.
   * <li>For an OTOCO order list, the secondary orders have the same restrictions
   * as an OCO order
   * <li>Default is OrderType.Limit
   * </ul>
   * <p>
   * https://api.exchange.cryptomkt.com/#create-new-spot-order-list
   *
   * @param contingencyType order list type. ContingencyType.ALL_OR_NONE,
   *                        ContingencyType.ONE_CANCEL_OTHER,
   *                        ContingencyType.ONE_TRIGGER_OTHER or
   *                        ContingencyType.ONE_TRIGGER_ONE_CANCEL_OTHER
   * @param orders          the list of orders
   * @param orderListId     Optional. order list identifier. If ommited, it will
   *                        be generated by the system. Must be equal to the
   *                        client order id of the first order in the request
   */
  public List<Order> createSpotOrderList(
      ContingencyType contingencyType,
      List<OrderBuilder> orders,
      @Nullable String orderListId)
      throws CryptomarketSDKException;

  /**
   * Replaces a spot order
   * <p>
   * For fee, for price accuracy and quantity, and for order status information
   * see the api docs
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#replace-spot-order
   *
   * @param clientOrderId    client order id of the old order
   * @param newClientOrderId client order id for the new order
   * @param quantity         Order quantity
   * @param strictValidate   Price and quantity will be checked for incrementation
   *                         within the symbol’s tick size and quantity step. See
   *                         the symbol's tick_size and quantity_increment
   * @param price            Required if order type is 'limit', 'stopLimit', or
   *                         'takeProfitLimit'. Order price
   * @return The new spot order
   * @throws CryptomarketSDKException
   */
  public Order replaceSpotOrder(
      String clientOrderId,
      String newClientOrderId,
      String quantity,
      @Nullable String price,
      @Nullable Boolean strictValidate) throws CryptomarketSDKException;

  /**
   * @see #replaceSpotOrder(String, String, String, String, Boolean)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Order replaceSpotOrder(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Cancel all active spot orders
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#cancel-all-spot-orders
   *
   * @return A list with the canceled spot order
   * @throws CryptomarketSDKException
   */
  public List<Order> cancelAllSpotOrders() throws CryptomarketSDKException;

  /**
   * Cancel the order with the specified client order id
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#cancel-spot-order
   *
   * @param clientOrderId client order id of the order to cancel
   * @return The canceled spot order
   * @throws CryptomarketSDKException
   */
  public Order cancelSpotOrder(String clientOrderId) throws CryptomarketSDKException;

  /**
   * Get the personal trading commission rates for all symbols
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-all-trading-commissions
   *
   * @return A list of commission rates
   * @throws CryptomarketSDKException
   */
  public List<Commission> getAllTradingCommissions() throws CryptomarketSDKException;

  /**
   * Get the personal trading commission rate of a symbol
   * <p>
   * Requires the "Place/cancel orders" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-trading-commission
   *
   * @param symbol The symbol of the commission rate
   * @return The commission rate of a symbol
   * @throws CryptomarketSDKException
   */
  public Commission getTradingCommission(String symbol) throws CryptomarketSDKException;

  // TRADING HISTORY

  /**
   * Get all the spot orders
   * <p>
   * Orders without executions are deleted after 24 hours
   * <p>
   * 'from' param and 'till' param must have the same format, both id or both
   * timestamp
   * <p>
   * Requires the "Orderbook, History, Trading balance" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#spot-orders-history
   *
   * @param clientOrderId Optional. If specified, all other parameters will be
   *                      ignored.
   * @param symbol        Optional. Filter orders by symbol
   * @param by            Optional. Sorting parameter. 'id' or 'timestamp'.
   *                      Default is
   *                      'timestamp'
   * @param sort          Optional. Sort direction. 'ASC' or 'DESC'. Default is
   *                      'DESC'
   * @param from          Optional. Initial value of the queried interval
   * @param till          Optional. Last value of the queried interval
   * @param limit         Optional. Prices per currency pair. Defaul is 100. Max
   *                      is 1000
   * @param offset        Optional. Default is 0. Max is 100000
   * @return A list of orders
   * @throws CryptomarketSDKException
   */
  public List<Order> getSpotOrderHistory(
      @Nullable String clientOrderId,
      @Nullable String symbol,
      @Nullable Sort sort,
      @Nullable SortBy by,
      @Nullable String from,
      @Nullable String till,
      @Nullable Integer limit,
      @Nullable Integer offset)
      throws CryptomarketSDKException;

  /**
   * @see #getSpotOrderHistory(String, String, Sort, SortBy, String, String,
   *      Integer, Integer)
   * @param paramsBuilder
   * @return
   * @throws CryptomarketSDKException
   */
  public List<Order> getSpotOrderHistory(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get the user's spot trading history
   * <p>
   * Requires the "Orderbook, History, Trading balance" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#spot-trades-history
   *
   * @param orderId Optional. Order unique identifier as assigned by the exchange
   * @param symbol  Optional. Filter orders by symbol
   * @param by      Optional. Sorting parameter. 'id' or 'timestamp'. Default is
   *                'timestamp'
   * @param sort    Optional. Sort direction. 'ASC' or 'DESC'. Default is 'DESC'
   * @param from    Optional. Initial value of the queried interval
   * @param till    Optional. Last value of the queried interval
   * @param limit   Optional. Prices per currency pair. Defaul is 100. Max is 1000
   * @param offset  Optional. Default is 0. Max is 100000
   * @return A list of trades
   * @throws CryptomarketSDKException
   */
  public List<Trade> getSpotTradesHistory(
      @Nullable String orderId,
      @Nullable String symbol,
      @Nullable Sort sort,
      @Nullable SortBy by,
      @Nullable String from,
      @Nullable String till,
      @Nullable Integer limit,
      @Nullable Integer offset)
      throws CryptomarketSDKException;

  /**
   * @see #getSpotTradesHistory(String, String, Sort, SortBy, String, String,
   *      Integer, Integer)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public List<Trade> getSpotTradesHistory(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  // WALLET MANAGEMENT

  /**
   * Get the user's wallet balance for all currencies with balance
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#wallet-balance
   *
   * @return A list of wallet balances
   * @throws CryptomarketSDKException
   */
  public List<Balance> getWalletBalances() throws CryptomarketSDKException;

  /**
   * Get the user's wallet balance of a currency
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#wallet-balance
   *
   * @param currency The currency code to query the balance
   * @return The wallet balance of the currency
   * @throws CryptomarketSDKException
   */
  public Balance getWalletBalanceByCurrency(String currency) throws CryptomarketSDKException;

  /**
   * Get the current addresses of the user
   * <p>
   * If the address for a currency did not previously exist, calling this method
   * will generate the address for it automatically
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-deposit-crypto-address
   *
   * @param currency    Optional. Currency of the address to get
   * @param networkCode Optional. Network code
   * @return A list of currency addresses
   * @throws CryptomarketSDKException
   */
  public List<Address> getDepositCryptoAddresses(
      @Nullable String currency,
      @Nullable String networkCode) throws CryptomarketSDKException;

  /**
   * Creates a new address for a currency
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#generate-deposit-crypto-address
   *
   * @param currency    currency to create a new address
   * @param networkCode Optional. Network code
   * @return The created address for the currency
   * @throws CryptomarketSDKException
   */
  public Address createDepositCryptoAddress(String currency, @Nullable String networkCode)
      throws CryptomarketSDKException;

  /**
   * Get the last 10 unique addresses used for deposit, by currency
   * <p>
   * Addresses used a long time ago may be omitted, even if they are among the
   * last 10 unique addresses
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#last-10-deposit-crypto-addresses
   *
   * @param currency    currency to get the list of addresses
   * @param networkCode Optional. Network code
   * @return A list of addresses
   * @throws CryptomarketSDKException
   */
  public List<Address> getLast10DepositCryptoAddresses(String currency, @Nullable String networkCode)
      throws CryptomarketSDKException;

  /**
   * Get the last 10 unique addresses used for withdrawals, by currency
   * <p>
   * Addresses used a long time ago may be omitted, even if they are among the
   * last 10 unique addresses
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#last-10-withdrawal-crypto-addresses
   *
   * @param currency    currency to get the list of addresses
   * @param networkCode Optional. Network code
   * @return A list of addresses
   * @throws CryptomarketSDKException
   */
  public List<Address> getLast10WithdrawalCryptoAddresses(String currency, @Nullable String networkCode)
      throws CryptomarketSDKException;

  /**
   * Please take note that changing security settings affects withdrawals:
   * <p>
   * - It is impossible to withdraw funds without enabling the two-factor
   * authentication (2FA)
   * <p>
   * - Password reset blocks withdrawals for 72 hours
   * <p>
   * - two-factor authentication reset blocks withdrawal for 96 hours
   * <p>
   * - Each time a new address is added to the whitelist, it takes 48 hours before
   * that address becomes active for withdrawal
   * <p>
   * Successful response to the request does not necessarily mean the resulting
   * transaction got executed immediately. It has to be processed first and may
   * eventually be rolled back. Nonetheless, a successful request unconditionally
   * results in reservation of funds on the wallet. The sum reserved covers the
   * transaction amount and the fee
   * <p>
   * To see whether a transaction has been finalized, call
   * {@link #getTransaction(String)}
   * <p>
   * Requires the "Withdraw cryptocurrencies" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#withdraw-crypto
   *
   * @param currency      currency code of the crypto to withdraw
   * @param amount        amount to be sent to the specified address
   * @param address       address identifier
   * @param networkCode   Optional. Network code
   * @param paymentId     Optional. An additional identifier required for specific
   *                      currencies (for example, "Memo")
   * @param includeFee    Optional. If true then the amount includes fees. Default
   *                      is false
   * @param autoCommit    Optional. If false then you should commit or rollback
   *                      the transaction in an hour. Used in two phase commit
   *                      schema. Default is true
   * @param useOffchain   Optional. Whether the withdrawal may be comitted
   *                      offchain. Accepted values are 'never', 'optionaly' and
   *                      'required'. Default is 'never'
   * @param publicComment Optional. Maximum lenght is 255
   * @return The transaction identifier of the withdraw
   * @throws CryptomarketSDKException
   */
  public String withdrawCrypto(
      String currency,
      String amount,
      String address,
      @Nullable String networkCode,
      @Nullable String paymentId,
      @Nullable Boolean includeFee,
      @Nullable Boolean autoCommit,
      @Nullable UseOffchain useOffchain,
      @Nullable String publicComment)
      throws CryptomarketSDKException;

  /**
   * @see #withdrawCrypto(String, String, String, String, String, Boolean,
   *      Boolean, UseOffchain, String)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public String withdrawCrypto(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Commit a withdrawal
   * <p>
   * Requires the "Withdraw cryptocurrencies" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#withdraw-crypto-commit-or-rollback
   *
   * @param transactionId the withdrawal transaction identifier
   * @return The transaction result. true if the commit is successful
   * @throws CryptomarketSDKException
   */
  public Boolean withdrawCryptoCommit(String transactionId) throws CryptomarketSDKException;

  /**
   * Rollback a withdrawal
   * <p>
   * Requires the "Withdraw cryptocurrencies" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#withdraw-crypto-commit-or-rollback
   *
   * @param transactionId the withdrawal transaction identifier
   * @return The transaction result. true if the rollback is successful
   * @throws CryptomarketSDKException
   */
  public Boolean withdrawCryptoRollback(String transactionId) throws CryptomarketSDKException;

  /**
   * Get an estimate of the withdrawal fee
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#estimate-withdrawal-fee
   *
   * @param currency    the currency code for withdrawal
   * @param amount      the expected withdraw amount
   * @param networkCode Optional. Network code
   * @return The expected fee
   * @throws CryptomarketSDKException
   */
  public String getEstimateWithdrawalFee(String currency, String amount, @Nullable String networkCode)
      throws CryptomarketSDKException;

  /**
   * @see #getEstimateWithdrawalFee(String, String, String)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public String getEstimateWithdrawalFee(ParamsBuilder paramsBuilder) throws CryptomarketSDKException;

  /**
   * Converts between currencies
   * <p>
   * Successful response to the request does not necessarily mean the resulting
   * transaction got executed immediately. It has to be processed first and may
   * eventually be rolled back
   * <p>
   * To see whether a transaction has been finalized, call
   * {@link #getTransaction(String)}
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#convert-between-currencies
   *
   * @param fromCurrency currency code of origin
   * @param toCurrency   currency code of destiny
   * @param amount       the amount to be converted
   * @return A list of transaction identifiers of the conversion
   * @throws CryptomarketSDKException
   */
  public List<String> convertBetweenCurrencies(
      String fromCurrency,
      String toCurrency,
      String amount)
      throws CryptomarketSDKException;

  /**
   * @see #convertBetweenCurrencies(String, String, String)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public List<String> convertBetweenCurrencies(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Check if an address is from this account
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#check-if-crypto-address-belongs-to-current-account
   *
   * @param address address to check
   * @return True if it is from the current account
   * @throws CryptomarketSDKException
   */
  public Boolean checkCryptoAddressBelongsToCurrentAccount(String address) throws CryptomarketSDKException;

  /**
   * Transfer funds between account types
   * <p>
   * 'source' param and 'destination' param must be different account types
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#transfer-between-wallet-and-exchange
   *
   * @param currency    currency code for transfering
   * @param amount      amount to be transfered
   * @param source      transfer source account type. Either 'wallet' or 'spot'
   * @param destination transfer source account type. Either 'wallet' or 'spot'
   * @return the transaction identifier of the transfer
   * @throws CryptomarketSDKException
   */
  public String transferBetweenWalletAndExchange(
      String currency,
      String amount,
      AccountType source,
      AccountType destination)
      throws CryptomarketSDKException;

  /**
   * @see #transferBetweenWalletAndExchange(String, String, AccountType,
   *      AccountType)
   * @param paramsBuilder
   * @return
   * @throws CryptomarketSDKException
   */
  public String transferBetweenWalletAndExchange(
      ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Transfer funds to another user
   * <p>
   * Requires the "Withdraw cryptocurrencies" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#transfer-money-to-another-user
   *
   * @param currency      currency code
   * @param amount        amount to be transfered
   * @param by            type of identifier. Either 'email' or 'username'
   * @param identifier    the email or username of the recieving user
   * @param publicComment Optional. Can be up to 255 characters long, excluding
   *                      the following characters: &amp;, &#39;, &lt;, &gt;,
   *                      &quot;
   * @return the transaction identifier of the transfer
   * @throws CryptomarketSDKException
   */
  public String transferMoneyToAnotherUser(String currency, String amount, IdentifyBy by, String identifier,
      String publicComment)
      throws CryptomarketSDKException;

  /**
   * @see #transferMoneyToAnotherUser(String, String, IdentifyBy, String, String)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public String transferMoneyToAnotherUser(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get the transaction history of the account
   * <p>
   * Important:
   * <p>
   * - The list of supported transaction types may be expanded in future versions
   * <p>
   * - Some transaction subtypes are reserved for future use and do not purport to
   * provide any functionality on the platform
   * <p>
   * - The list of supported transaction subtypes may be expanded in future
   * versions
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-transactions-history
   *
   * @param transactionIds Optional. List of transaction identifiers to query
   * @param currencies     Optional. List of currency codes
   * @param networks       Optional. List of network codes
   * @param types          Optional. List of types to query. valid types are:
   *                       'DEPOSIT', 'WITHDRAW', 'TRANSFER' and 'SWAP'
   * @param subtypes       Optional. List of subtypes to query. valid
   *                       subtypes are: 'UNCLASSIFIED', 'BLOCKCHAIN',
   *                       'AIRDROP', 'AFFILIATE', 'STAKING', 'BUY_CRYPTO',
   *                       'OFFCHAIN', 'FIAT', 'SUB_ACCOUNT',
   *                       'WALLET_TO_SPOT', 'SPOT_TO_WALLET',
   *                       'WALLET_TO_DERIVATIVES', 'DERIVATIVES_TO_WALLET',
   *                       'CHAIN_SWITCH_FROM', 'CHAIN_SWITCH_TO' and
   *                       'INSTANT_EXCHANGE'
   * @param statuses       Optional. List of statuses to query. valid
   *                       subtypes are: 'CREATED', 'PENDING', 'FAILED',
   *                       'SUCCESS' and 'ROLLED_BACK'
   * @param sort           Optional. Sort direction. 'ASC' or 'DESC'. Default
   *                       is 'DESC'
   * @param sortBy         Optional. sorting parameter.'created_at' or 'id'.
   *                       Default is 'created_at'
   * @param from           Optional. Interval initial value when ordering by
   *                       'created_at'. As Datetime
   * @param till           Optional. Interval end value when ordering by
   *                       'created_at'. As Datetime
   * @param idFrom         Optional. Interval initial value when ordering by
   *                       id. Min is 0
   * @param idTill         Optional. Interval end value when ordering by id.
   *                       Min is 0
   * @param limit          Optional. Transactions per query. Defaul is 100.
   *                       Max is 1000
   * @param offset         Optional. Default is 0. Max is 100000
   * @return A list of transactions
   * @throws CryptomarketSDKException
   */
  public List<Transaction> getTransactionHistory(
      @Nullable List<String> transactionIds,
      @Nullable List<String> currencies,
      @Nullable List<String> networks,
      @Nullable List<TransactionType> types,
      @Nullable List<TransactionSubtype> subtypes,
      @Nullable List<TransactionStatus> statuses,
      @Nullable Sort sort,
      @Nullable SortBy sortBy,
      @Nullable String from,
      @Nullable String till,
      @Nullable Integer idFrom,
      @Nullable Integer idTill,
      @Nullable Integer limit,
      @Nullable Integer offset)
      throws CryptomarketSDKException;

  public List<Transaction> getTransactionHistory(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get a transaction by its identifier
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-transactions-history
   *
   * @param transactionId The identifier of the transaction
   * @return A transaction of the account
   * @throws CryptomarketSDKException
   */
  public Transaction getTransaction(String transactionId) throws CryptomarketSDKException;

  /**
   * get the status of the offchain
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#check-if-offchain-is-available
   *
   * @param currency  currency code
   * @param address   address identifier
   * @param paymentId Optional.
   * @return True if the offchain is available
   * @throws CryptomarketSDKException
   */
  public Boolean checkIfOffchainIsAvailable(String currency, String address, @Nullable String paymentId)
      throws CryptomarketSDKException;

  /**
   * @see #checkIfOffchainIsAvailable(String, String, String)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public Boolean checkIfOffchainIsAvailable(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  /**
   * Get the list of amount locks
   * <p>
   * Amount locks allow to set the minimum user's balance to determine their
   * solvency. The locked amount is not displayed in user's balances
   * <p>
   * Amount locks are not tied to a currency. All locks in total affect the
   * ability to withdraw the balance in any currency
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-amount-locks
   *
   * @param currency Optional. Currency code
   * @param active   Optional. value showing whether the lock is active
   * @param limit    Optional. Dafault is 100. Min is 0. Max is 1000
   * @param offset   Optional. Default is 0. Min is 0
   * @return A list of locks
   * @throws CryptomarketSDKException
   */
  public List<AmountLock> getAmountLocks(
      @Nullable String currency,
      @Nullable Boolean active,
      @Nullable Integer limit,
      @Nullable Integer offset) throws CryptomarketSDKException;

  /**
   * @see #getAmountLocks(String, Boolean, Integer, Integer)
   * @param paramsBuilder
   * @throws CryptomarketSDKException
   */
  public List<AmountLock> getAmountLocks(ParamsBuilder paramsBuilder)
      throws CryptomarketSDKException;

  // SUB ACOUNTS

  /**
   * Returns list of sub-accounts per a super account.
   * <p>
   * Requires no API key Access Rights.
   * <p>
   * https://api.exchange.cryptomkt.com/#get-sub-accounts-list
   *
   * @return A list of subaccounts
   * @throws CryptomarketSDKException
   */
  public List<SubAccount> getSubAccountList() throws CryptomarketSDKException;

  /**
   * gets a sub-account by id.
   * <p>
   * Requires no API key Access Rights.
   * <p>
   * https://api.exchange.cryptomkt.com/#get-sub-accounts-list
   *
   * @param subAccountId UUID of the sub-account to query
   * @return A list of subaccounts
   * @throws CryptomarketSDKException
   */
  public SubAccount getSubAccount(String subAccountId)
      throws CryptomarketSDKException;

  /**
   * Freezes sub-accounts listed. It implies that the Sub-accounts frozen wouldn't
   * be able to:
   * <p>
   * <ul>
   * <li>login
   * <li>withdraw funds
   * <li>trade
   * <li>complete pending orders
   * <li>use API keys
   * </ul>
   * <p>
   * For any sub-account listed, all orders will be canceled and all funds will be
   * transferred form the Trading balance.
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#freeze-sub-account
   *
   * @param subAccountIds A list of sub-account ids to freeze
   * @return true if the freeze was successful
   * @throws CryptomarketSDKException
   */
  public Boolean freezeSubAccount(List<String> subAccountIds) throws CryptomarketSDKException;

  /**
   * Activates sub-accounts listed. It would make sub-accounts active after being
   * frozen
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#activate-sub-account
   *
   * @param subAccountIds A list of account ids to activate
   * @return true if the activation was successful
   * @throws CryptomarketSDKException
   */
  public Boolean activateSubAccount(List<String> subAccountIds) throws CryptomarketSDKException;

  /**
   * Transfers funds from the super-account to a sub-account or from a sub-account
   * to the super-account
   * <p>
   * Requires the "Withdraw cryptocurrencies" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#transfer-funds
   *
   * @param subAccountId The id of the account to transfer from or to
   * @param amount       the amount of currency to transfer
   * @param currency     the currency to transfer
   * @param transferType the type of transfer.
   *                     SubAccountTransferType.TO_SUB_ACCOUNT or
   *                     SubAccountTransferType.FROM_SUB_ACCOUNT
   * @return The transaction id of the tranfer
   * @throws CryptomarketSDKException
   */
  public String transferFunds(String subAccountId, String amount, String currency, SubAccountTransferType transferType)
      throws CryptomarketSDKException;

  /**
   * Returns a list of withdrawal settings for sub-accounts listed
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-acl-settings
   *
   * @param subAccountIds The list of sub-account ids of the sub-accounts to get
   *                      the ACL settings. The subAccountId filed is ignored.
   * @return A list of withdraw settings for sub-accounts listed
   * @throws CryptomarketSDKException
   */
  public List<SubAccountSettings> getACLSettings(List<String> subAccountIds) throws CryptomarketSDKException;

  /**
   * Disables or enables withdrawals for a sub-account
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#change-acl-settings
   *
   * @param subAccountIds The list of sub-account ids to change the ACL settings
   * @param settings      The settings to change the ACL settings
   * @return The list of the updated withdraw settings of the changed sub-account
   * @throws CryptomarketSDKException
   */
  public List<SubAccountSettings> changeACLSettings(List<String> subAccountIds, SubAccountSettings settings)
      throws CryptomarketSDKException;

  /**
   * Returns non-zero balance values for a sub-account.
   * <p>
   * Report will include the wallet and Trading balances for each currency.
   * <p>
   * It is functional with no regard to the state of a sub-account. All account
   * types are optional and appears only in case of non-zero balance
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-sub-account-balance
   *
   * @param subAccountId the sub-account id of the sub-account to get the balances
   * @return A list of balances of the sub-account
   * @throws CryptomarketSDKException
   */
  public SubAccountBalances getSubAccountBalance(String subAccountId) throws CryptomarketSDKException;

  /**
   * Returns sub-account crypto address for currency
   * <p>
   * Requires the "Payment information" API key Access Right
   * <p>
   * https://api.exchange.cryptomkt.com/#get-sub-account-crypto-address
   *
   * @param subAccountId the id of the sub-account to get the crypto address
   * @param currency     currency code to get the crypto address
   * @param networkCode  Optional. network code
   * @return The crypto address
   * @throws CryptomarketSDKException
   */
  public String getSubAccountCryptoAddress(String subAccountId, String currency, String networkCode)
      throws CryptomarketSDKException;

}
