package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.NotificationType;
import com.cryptomarket.params.OrderBuilder;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.TimeInForce;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.models.WSJsonResponse;
import com.cryptomarket.sdk.websocket.interceptor.Interceptor;
import com.cryptomarket.sdk.websocket.interceptor.InterceptorFactory;
import com.squareup.moshi.JsonDataException;

public class CryptomarketWSSpotTradingClientImpl extends AuthClient implements CryptomarketWSSpotTradingClient {

  /**
   * creates a new CryptomarketWSSpotTradingClient instance
   *
   * @param apiKey    public API key
   * @param apiSecret secret API key
   * @param window    Maximum difference between the send of the request and the
   *                  moment of request processing in milliseconds
   * @throws IOException
   */
  public CryptomarketWSSpotTradingClientImpl(String apiKey, String apiSecret, Integer window) throws IOException {
    super("wss://api.exchange.cryptomkt.com/api/3/ws/trading", apiKey, apiSecret, window);
    Map<String, String> subsKeys = this.getSubscritpionKeys();
    // reports
    subsKeys.put("spot_subscribe", "reports");
    subsKeys.put("spot_unsubscribe", "reports");
    subsKeys.put("spot_orders", "reports");
    subsKeys.put("spot_order", "reports");
  }

  /**
   * creates a new CryptomarketWSSpotTradingClient instance, using the default
   * window of 10 seconds
   *
   * @param apiKey    public API key
   * @param apiSecret secret API key
   * @throws IOException
   */
  public CryptomarketWSSpotTradingClientImpl(String apiKey, String apiSecret) throws IOException {
    this(apiKey, apiSecret, 0);
  }

  @Override
  public void subscribeToReports(BiConsumer<List<Report>, NotificationType> notificationBiConsumer,
      BiConsumer<Boolean, CryptomarketSDKException> resultBiConsumer) {
    Interceptor feedInterceptor = new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        if (response.getMethod().equals("spot_orders")) {
          try {
            List<Report> reports = adapter.listFromValue(response.getParams(), Report.class);
            if (reports != null) {
              notificationBiConsumer.accept(reports, NotificationType.SNAPSHOT);
            }
          } catch (JsonDataException e) {
          }
        } else if (response.getMethod().equals("spot_order")) {
          try {
            Report report = adapter.objectFromValue(response.getParams(), Report.class);
            List<Report> reports = new ArrayList<Report>();
            reports.add(report);
            notificationBiConsumer.accept(reports, NotificationType.UPDATE);
          } catch (JsonDataException e) {
          }
        }
      }
    };
    Interceptor resultInterceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Boolean.class);
    sendSubscription("spot_subscribe", null, feedInterceptor, resultInterceptor);
  }

  @Override
  public void unsubscribeToReports(BiConsumer<Boolean, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Boolean.class);
    sendUnsubscription("spot_unsubscribe", null, interceptor);
  }

  @Override
  public void getAllActiveOrders(BiConsumer<List<Report>, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = InterceptorFactory.newOfWSResponseList(resultBiConsumer, Report.class);
    sendById("spot_get_orders", null, interceptor);

  }

  @Override
  public void createSpotOrder(
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
      String makeRate,
      BiConsumer<Report, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder paramsBuilder = new ParamsBuilder()
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
        .makeRate(makeRate);
    createSpotOrder(paramsBuilder, resultBiConsumer);
  }

  @Override
  public void createSpotOrder(
      ParamsBuilder paramsBuilder,
      BiConsumer<Report, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Report.class);
    sendById("spot_new_order", paramsBuilder.buildObjectMap(), interceptor);
  }

  @Override
  public void createSpotOrderList(
      ContingencyType contingencyType,
      List<OrderBuilder> orders,
      String orderListID,
      BiConsumer<List<Report>, CryptomarketSDKException> resultBiConsumer) {
    List<Map<String, Object>> orderListData = new ArrayList<>();
    orders.forEach(orderBuilder -> orderListData.add(orderBuilder.buildObjectMap()));
    ParamsBuilder params = new ParamsBuilder()
        .orderListID(orderListID)
        .contingencyType(contingencyType)
        .orders(orderListData);
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseList(resultBiConsumer, Report.class);
    sendById("spot_new_order_list", params.buildObjectMap(), interceptor);

  }

  @Override
  public void cancelSpotOrder(String clientOrderID, BiConsumer<Report, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder params = new ParamsBuilder().clientOrderID(clientOrderID);
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Report.class);
    sendById("spot_cancel_order", params.buildObjectMap(), interceptor);
  }

  @Override
  public void replaceSpotOrder(String clientOrderID, String newClientOrderID, String quantity, String price,
      Boolean strictValidate, BiConsumer<Report, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder paramsBuilder = new ParamsBuilder()
        .clientOrderID(clientOrderID)
        .newClientOrderID(newClientOrderID)
        .quantity(quantity)
        .price(price)
        .strictValidate(strictValidate);
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Report.class);
    sendById("spot_replace_order", paramsBuilder.buildObjectMap(), interceptor);
  }

  @Override
  public void cancelAllSpotOrders(BiConsumer<List<Report>, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseList(resultBiConsumer, Report.class);
    sendById("spot_cancel_orders", null, interceptor);
  }

  @Override
  public void getSpotTradingBalances(BiConsumer<List<Balance>, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseList(resultBiConsumer, Balance.class);
    sendById("spot_balances", null, interceptor);
  }

  @Override
  public void getSpotTradingBalanceOfCurrency(String currency,
      BiConsumer<Balance, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Balance.class);
    ParamsBuilder paramsBuilder = new ParamsBuilder().currency(currency);
    sendById("spot_balance", paramsBuilder.buildObjectMap(), interceptor);
  }

  @Override
  public void getSpotCommissions(BiConsumer<List<Commission>, CryptomarketSDKException> resultBiConsumer) {
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseList(resultBiConsumer, Commission.class);
    sendById("spot_fees", null, interceptor);
  }

  @Override
  public void getSpotCommissionOfSymbol(String symbol,
      BiConsumer<Commission, CryptomarketSDKException> resultBiConsumer) {
    ParamsBuilder paramsBuilder = new ParamsBuilder().symbol(symbol);
    Interceptor interceptor = (resultBiConsumer == null)
        ? null
        : InterceptorFactory.newOfWSResponseObject(resultBiConsumer, Commission.class);
    sendById("spot_fee", paramsBuilder.buildObjectMap(), interceptor);
  }

}
