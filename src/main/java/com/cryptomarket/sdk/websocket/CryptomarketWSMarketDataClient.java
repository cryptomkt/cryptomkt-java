package com.cryptomarket.sdk.websocket;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import com.cryptomarket.params.TickerSpeed;
import com.cryptomarket.params.Depth;
import com.cryptomarket.params.NotificationType;
import com.cryptomarket.params.OBSpeed;
import com.cryptomarket.params.Period;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.WSCandle;
import com.cryptomarket.sdk.models.WSOrderBook;
import com.cryptomarket.sdk.models.WSOrderBookTop;
import com.cryptomarket.sdk.models.WSPublicTrade;
import com.cryptomarket.sdk.models.WSTicker;

import org.jetbrains.annotations.Nullable;

/**
 * PublicClient connects via websocket to cryptomarket to get market information
 * of the exchange.
 * <p>
 * Requires no API keys to make socket calls
 */
public interface CryptomarketWSMarketDataClient extends CryptomarketWS {

  /**
   * subscribe to a feed of trades
   * <p>
   * subscription is for the specified symbols
   * <p>
   * normal subscriptions have one update message per symbol
   * <p>
   * the first notification contains the last n trades, with n defined by the
   * limit argument, the next notifications are updates and correspond to new
   * trades
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-trades
   *
   * @param notificationBiConsumer recieves a feed of trades as a map of them,
   *                               indexed by symbol id, and the
   *                               type of notification, either SNAPSHOT or UPDATE
   * @param symbols                A list of symbol ids
   * @param limit                  Number of historical entries returned in the
   *                               first feed. Min is 0. Max is 1000. Default is 0
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if
   *                               there was a problem (or null if there was none)
   */
  public void subscribeToTrades(
      BiConsumer<Map<String, List<WSPublicTrade>>, NotificationType> notificationBiConsumer,
      List<String> symbols,
      Integer limit,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of candles
   * <p>
   * subscription is for the specified symbols
   * <p>
   * normal subscriptions have one update message per symbol
   * <p>
   * the first notification are n candles, with n defined by the limit argument,
   * the next notification are updates, with one candle at a time
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-candles
   *
   * @param notificationBiConsumer recieves a feed of candles as a map of them,
   *                               indexed by symbol id, and the
   *                               type of notification, either SNAPSHOT or UPDATE
   * @param period                 Optional. A valid tick interval. 'M1' (one
   *                               minute), 'M3', 'M5', 'M15', 'M30', 'H1' (one
   *                               hour), 'H4', 'D1' (one day), 'D7', '1M' (one
   *                               month). Default is 'M30'
   * @param symbols                A list of symbol ids
   * @param limit                  Optional. Number of historical entries returned
   *                               in the first feed. Min is 0. Max is 1000.
   *                               Default is 0
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if
   *                               there was a problem (or null if there was none)
   */
  public void subscribeToCandles(
      BiConsumer<Map<String, List<WSCandle>>, NotificationType> notificationBiConsumer,
      Period period,
      @Nullable List<String> symbols,
      @Nullable Integer limit,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of mini tickers
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * normal subscriptions have one update message per symbol
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-mini-ticker
   *
   * @param notificationBiConsumer recieves a feed of mini tickers as a map of
   *                               them, indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param speed                  The speed of the feed. '1s' or '3s'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if
   *                               there was a problem (or null if there was none)
   */
  public void subscribeToMiniTicker(
      BiConsumer<Map<String, WSCandle>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of mini tickers
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * batch subscriptions have a joined update for all symbols
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-mini-ticker-in-batches
   *
   * @param notificationBiConsumer recieves a feed of mini tickers as a map of
   *                               them, indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param speed                  The speed of the feed. '1s' or '3s'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if
   *                               there was a problem (or null if there was none)
   */

  public void subscribeToMiniTickerInBatches(
      BiConsumer<Map<String, WSCandle>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of tickers
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * normal subscriptions have one update message per symbol
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-ticker
   *
   * @param notificationBiConsumer recieves a feed of tickers as a map of them,
   *                               indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param speed                  The speed of the feed. '1s' or '3s'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if
   *                               there was a problem (or null if there was none)
   */
  public void subscribeToTicker(
      BiConsumer<Map<String, WSTicker>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of tickers
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * batch subscriptions have a joined update for all symbols
   * <p>
   * Requires no API key Access Rights
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-ticker-in-batches
   *
   * @param notificationBiConsumer recieves a feed of tickers as a map of them,
   *                               indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param speed                  The speed of the feed. '1s' or '3s'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if
   *                               there was a problem (or null if there was none)
   */
  public void subscribeToTickerInBatches(
      BiConsumer<Map<String, WSTicker>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of a full orderbook
   * <p>
   * subscription is for the specified symbols
   * <p>
   * normal subscriptions have one update message per symbol
   * <p>
   * the first notification is a snapshot of the full orderbook, and next
   * notifications are updates to this snapshot
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-full-order-book
   *
   * @param notificationBiConsumer recieves a feed of full orderbooks as a map of
   *                               them, indexed by symbol id, and the
   *                               type of notification, either SNAPSHOT or UPDATE
   * @param symbols                A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if
   *                               there was a problem (or null if there was none)
   *
   */
  public void subscribeToFullOrderBook(
      BiConsumer<Map<String, WSOrderBook>, NotificationType> notificationBiConsumer,
      List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of a partial orderbook
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * normal subscriptions have one update message per symbol
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-partial-order-book
   *
   * @param notificationBiConsumer recieves a feed of partial orderbooks as a map
   *                               of them, indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param depth                  The depth of the partial orderbook. 'D5', 'D10' or 'D20'
   * @param speed                  The speed of the feed. '100ms', '500ms' or
   *                               '1000ms'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols , and a
   *                               CryptomarketSDKException if there was a problem
   *                               (or null if there was none)
   */
  public void subscribeToPartialOrderBook(
      BiConsumer<Map<String, WSOrderBook>, NotificationType> notificationBiConsumer,
      Depth depth,
      OBSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of a partial orderbook in batches
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * batch subscriptions have a joined update for all symbols
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-partial-order-book-in-batches
   *
   * @param notificationBiConsumer recieves a feed of partial orderbooks as a map
   *                               of them, indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param depth                  The depth of the partial orderbook. 'D5', 'D10' or 'D20'
   * @param speed                  The speed of the feed. '100ms', '500ms' or
   *                               '1000ms'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if there was a problem
   *                               (or null if there was none)
   *
   */
  public void subscribeToPartialOrderBookInBatches(
      BiConsumer<Map<String, WSOrderBook>, NotificationType> notificationBiConsumer,
      Depth depth,
      OBSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of the top of the orderbook
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * normal subscriptions have one update message per symbol
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-top-of-book
   *
   * @param notificationBiConsumer recieves a feed of the top of orderbooks as a
   *                               map of them, indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param speed                  The speed of the feed. '100ms', '500ms' or
   *                               '1000ms'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if there was a problem
   *                               (or null if there was none)
   */
  public void subscribeToTopOfOrderBook(
      BiConsumer<Map<String, WSOrderBookTop>, NotificationType> notificationBiConsumer,
      OBSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);

  /**
   * subscribe to a feed of the top of the orderbook
   * <p>
   * subscription is for all symbols or for the specified symbols
   * <p>
   * batch subscriptions have a joined update for all symbols
   * <p>
   * https://api.exchange.cryptomkt.com/#subscribe-to-top-of-book-in-batches
   *
   * @param notificationBiConsumer recieves a feed of the top orderbooks as a map
   *                               of them, indexed by symbol id, and the
   *                               type of notification, only DATA
   * @param speed                  The speed of the feed. '100ms', '500ms' or
   *                               '1000ms'
   * @param symbols                Optional. A list of symbol ids
   * @param resultBiConsumer       Optional. recieves a list of successfully
   *                               subscribed symbols, and a
   *                               CryptomarketSDKException if there was a problem
   *                               (or null if there was none)
   */
  public void subscribeToTopOfOrderBookInBatches(
      BiConsumer<Map<String, WSOrderBookTop>, NotificationType> notificationBiConsumer,
      OBSpeed speed,
      @Nullable List<String> symbols,
      @Nullable BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer);
}
