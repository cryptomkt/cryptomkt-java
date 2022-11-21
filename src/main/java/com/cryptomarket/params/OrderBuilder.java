package com.cryptomarket.params;

import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketArgumentException;

public class OrderBuilder {
  ParamsBuilder paramsBuilder = new ParamsBuilder();

  public OrderBuilder checkRequired(List<String> requiredParams) throws CryptomarketArgumentException {
    paramsBuilder.checkRequired(requiredParams);
    return this;
  }
  public OrderBuilder clientOrderID(String clientOrderID) {
    paramsBuilder.clientOrderID(clientOrderID);
    return this;
  }

  public OrderBuilder symbol(String symbol) {
    paramsBuilder.symbol(symbol);
    return this;
  }

  public OrderBuilder side(Side side) {
    paramsBuilder.side(side);
    return this;
  }

  public OrderBuilder orderType(OrderType orderType) {
    paramsBuilder.orderType(orderType);
    return this;
  }

  public OrderBuilder timeInForce(TimeInForce timeInForce) {
    paramsBuilder.timeInForce(timeInForce);
    return this;
  }

  public OrderBuilder quantity(String quantity) {
    paramsBuilder.quantity(quantity);
    return this;
  }

  public OrderBuilder price(String price) {
    paramsBuilder.price(price);
    return this;
  }

  public OrderBuilder stopPrice(String stpoPrice) {
    paramsBuilder.stopPrice(stpoPrice);
    return this;
  }

  public OrderBuilder expireTime(String expireTime) {
    paramsBuilder.expireTime(expireTime);
    return this;
  }

  public OrderBuilder strictValidate(Boolean strictValidate) {
    paramsBuilder.strictValidate(strictValidate);
    return this;
  }

  public OrderBuilder postOnly(Boolean postOnly) {
    paramsBuilder.postOnly(postOnly);
    return this;
  }

  public OrderBuilder takeRate(String takeRate) {
    paramsBuilder.takeRate(takeRate);
    return this;
  }

  public OrderBuilder makeRate(String makeRate) {
    paramsBuilder.makeRate(makeRate);
    return this;
  }

  public Map<String, Object> buildObjectMap() {
    return paramsBuilder.buildObjectMap();
  }

  public Map<String, String> build() {
    return paramsBuilder.build();
  }
}
