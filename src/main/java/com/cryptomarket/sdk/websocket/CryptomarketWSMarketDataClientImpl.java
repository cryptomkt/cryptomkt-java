package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import com.cryptomarket.params.TickerSpeed;
import com.cryptomarket.params.Depth;
import com.cryptomarket.params.NotificationType;
import com.cryptomarket.params.OBSpeed;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.PriceSpeed;
import com.cryptomarket.sdk.Adapter;
import com.cryptomarket.sdk.ArgNames;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.WSCandle;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.models.WSOrderBook;
import com.cryptomarket.sdk.models.WSOrderBookTop;
import com.cryptomarket.sdk.models.WSPriceRate;
import com.cryptomarket.sdk.models.WSPublicTrade;
import com.cryptomarket.sdk.models.WSTicker;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;

public class CryptomarketWSMarketDataClientImpl extends ClientBase implements CryptomarketWSMarketDataClient {
  OrderbookCache OBCache = new OrderbookCache();
  protected Adapter adapter = new Adapter();

  public CryptomarketWSMarketDataClientImpl() throws IOException {
    super("wss://api.exchange.cryptomkt.com/api/3/ws/public");
  }

  @Override
  public void handle(String json) throws CryptomarketSDKException {
    WSJsonResponse response = adapter.objectFromJson(json, WSJsonResponse.class);
    if (response.getChannel() != null) {
      handleNotification(response);
    } else if (response.getId() != null) {
      handleResponse(response);
    } else {
      // do nothing
    }

  }

  @Override
  protected void handleNotification(WSJsonResponse response) {
    String key = buildKey(response);
    Interceptor interceptor = interceptorCache.getSubscriptionInterceptor(key);
    if (interceptor != null) {
      interceptor.makeCall(response);
    } else {
    }
  }

  private void subscriptionByChannel(String channel, Map<String, Object> params, Interceptor feedInterceptor,
      Interceptor resultInterceptor) {
    String key = buildKey(channel, params);
    interceptorCache.storeSubscriptionInterceptor(key, feedInterceptor);
    Payload payload = new Payload("subscribe", channel, params);
    if (resultInterceptor != null) {
      Integer id = interceptorCache.saveInterceptor(resultInterceptor);
      payload.id = id;
    }
    String json = payloadAdapter.toJson(payload);
    websocket.send(json);
  };

  @Override
  protected String buildKey(String channel, Map<String, Object> params) {
    String key = params.containsKey(ArgNames.TARGET_CURRENCY)
        ? channel + (String) params.get(ArgNames.TARGET_CURRENCY)
        : channel;
    return key;
  }

  private String buildKey(WSJsonResponse response) {
    String channel = response.getChannel();
    String targetCurrency = response.getTargetCurrency();
    return targetCurrency != null
        ? channel + targetCurrency
        : channel;
  }

  private <T> void makeSubscriptionWithInterceptors(
      String channel,
      ParamsBuilder params,
      Class<T> cls,
      BiConsumer<Map<String, T>, NotificationType> notificationBiConsumer,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = InterceptorFactory.newOfChanneledWSResponse(notificationBiConsumer, cls);
    Interceptor resultInterceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfSubscriptionResponse(resultBiConsumer);
    subscriptionByChannel(channel, params.buildObjectMap(), interceptor, resultInterceptor);
  }

  private <T> void makeSubscriptionWithListInterceptors(
      String channel,
      ParamsBuilder params,
      Class<T> cls,
      BiConsumer<Map<String, List<T>>, NotificationType> notificationBiConsumer,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = InterceptorFactory.newMapStringListOfChanneledWSResponseObject(
        notificationBiConsumer,
        cls);
    Interceptor resultInterceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfSubscriptionResponse(resultBiConsumer);
    subscriptionByChannel(channel, params.buildObjectMap(), interceptor, resultInterceptor);
  }

  // PUBLIC METHODS
  @Override
  public void subscribeToTrades(
      BiConsumer<Map<String, List<WSPublicTrade>>, NotificationType> notificationBiConsumer,
      List<String> symbols,
      Integer limit,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolList(symbols).limit(limit);
    String channel = "trades";
    makeSubscriptionWithListInterceptors(
        channel,
        params,
        WSPublicTrade.class,
        notificationBiConsumer,
        resultBiConsumer);
  }

  @Override
  public void subscribeToCandles(
      BiConsumer<Map<String, List<WSCandle>>, NotificationType> notificationBiConsumer,
      Period period,
      List<String> symbols,
      Integer limit,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolList(symbols).limit(limit);
    String channel = String.format("candles/%s", period);
    makeSubscriptionWithListInterceptors(
        channel,
        params,
        WSCandle.class,
        notificationBiConsumer,
        resultBiConsumer);
  }

  @Override
  public void subscribeToConvertedCandles(
      BiConsumer<Map<String, List<WSCandle>>, NotificationType> notificationBiConsumer, String targetCurrency,
      Period period, List<String> symbols, Integer limit,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().targetCurrency(targetCurrency).symbolList(symbols).limit(limit);
    String channel = String.format("converted/candles/%s", period);
    makeSubscriptionWithListInterceptors(
        channel,
        params,
        WSCandle.class,
        notificationBiConsumer,
        resultBiConsumer);
  }

  @Override
  public void subscribeToPriceRates(
      BiConsumer<Map<String, WSPriceRate>, NotificationType> notificationBiConsumer,
      PriceSpeed speed,
      String targetCurrency,
      List<String> currencies,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().currencyListOrAsterisc(currencies).targetCurrency(targetCurrency);
    String channel = String.format("price/rate/%s", speed);
    makeSubscriptionWithInterceptors(channel, params, WSPriceRate.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToPriceRatesInBatches(
      BiConsumer<Map<String, WSPriceRate>, NotificationType> notificationBiConsumer,
      PriceSpeed speed,
      String targetCurrency,
      List<String> currencies,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().currencyListOrAsterisc(currencies).targetCurrency(targetCurrency);
    String channel = String.format("price/rate/%s/batches", speed);
    makeSubscriptionWithInterceptors(channel, params, WSPriceRate.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToMiniTicker(
      BiConsumer<Map<String, WSCandle>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("ticker/price/%s", speed);
    makeSubscriptionWithInterceptors(channel, params, WSCandle.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToMiniTickerInBatches(
      BiConsumer<Map<String, WSCandle>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("ticker/price/%s/batch", speed);
    makeSubscriptionWithInterceptors(channel, params, WSCandle.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToTicker(
      BiConsumer<Map<String, WSTicker>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("ticker/%s", speed);
    makeSubscriptionWithInterceptors(channel, params, WSTicker.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToTickerInBatches(
      BiConsumer<Map<String, WSTicker>, NotificationType> notificationBiConsumer,
      TickerSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("ticker/%s/batch", speed);
    makeSubscriptionWithInterceptors(channel, params, WSTicker.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToFullOrderBook(
      BiConsumer<Map<String, WSOrderBook>, NotificationType> notificationBiConsumer,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolList(symbols);
    String channel = "orderbook/full";
    makeSubscriptionWithInterceptors(channel, params, WSOrderBook.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToPartialOrderBook(
      BiConsumer<Map<String, WSOrderBook>, NotificationType> notificationBiConsumer,
      Depth depth,
      OBSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("orderbook/%s/%s", depth, speed);
    makeSubscriptionWithInterceptors(channel, params, WSOrderBook.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToPartialOrderBookInBatches(
      BiConsumer<Map<String, WSOrderBook>, NotificationType> notificationBiConsumer,
      Depth depth,
      OBSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("orderbook/%s/%s/batch", depth, speed);
    makeSubscriptionWithInterceptors(channel, params, WSOrderBook.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToTopOfOrderBook(
      BiConsumer<Map<String, WSOrderBookTop>, NotificationType> notificationBiConsumer,
      OBSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("orderbook/top/%s", speed);
    makeSubscriptionWithInterceptors(channel, params, WSOrderBookTop.class, notificationBiConsumer, resultBiConsumer);
  }

  @Override
  public void subscribeToTopOfOrderBookInBatches(
      BiConsumer<Map<String, WSOrderBookTop>, NotificationType> notificationBiConsumer,
      OBSpeed speed,
      List<String> symbols,
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().symbolListOrAsteric(symbols);
    String channel = String.format("orderbook/top/%s/batch", speed);
    makeSubscriptionWithInterceptors(channel, params, WSOrderBookTop.class, notificationBiConsumer, resultBiConsumer);
  }
}
