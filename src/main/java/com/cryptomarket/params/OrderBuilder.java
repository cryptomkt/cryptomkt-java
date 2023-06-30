package com.cryptomarket.params;

import com.squareup.moshi.Json;

public class OrderBuilder {
  @Json(name = "client_order_id")
  private String clientOrderId;
  private String symbol;
  private Side side;
  private OrderType type;

  @Json(name = "time_in_force")
  private TimeInForce timeInForce;
  private String quantity;
  private String price;

  @Json(name = "stop_price")
  private String stopPrice;

  @Json(name = "expire_time")
  private String expireTime;

  @Json(name = "strict_validate")
  private Boolean strictValidate;

  @Json(name = "post_only")
  private Boolean postOnly;

  @Json(name = "take_rate")
  private String takeRate;

  @Json(name = "make_rate")
  private String makeRate;

  public OrderBuilder clientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
    return this;
  }

  public OrderBuilder symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public OrderBuilder side(Side side) {
    this.side = side;
    return this;
  }

  public OrderBuilder orderType(OrderType type) {
    this.type = type;
    return this;
  }

  public OrderBuilder timeInForce(TimeInForce timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

  public OrderBuilder quantity(String quanity) {
    this.quantity = quanity;
    return this;
  }

  public OrderBuilder price(String price) {
    this.price = price;
    return this;
  }

  public OrderBuilder stopPrice(String stopPrice) {
    this.stopPrice = stopPrice;
    return this;
  }

  public OrderBuilder expireTime(String expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public OrderBuilder strictValidate(Boolean strictValidate) {
    this.strictValidate = strictValidate;
    return this;
  }

  public OrderBuilder postOnly(Boolean postOnly) {
    this.postOnly = postOnly;
    return this;
  }

  public OrderBuilder takeRate(String takeRate) {
    this.takeRate = takeRate;
    return this;
  }

  public OrderBuilder makeRate(String makeRate) {
    this.makeRate = makeRate;
    return this;
  }
}
