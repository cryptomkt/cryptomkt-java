package com.cryptomarket.params;

import com.squareup.moshi.Json;

/** Order builder, used in order requests. used as a method chain */
@SuppressWarnings("unused")
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

  /**
   * Sets the client order id of the order
   *
   * @param clientOrderId The client order id of the order
   * @return The order builder
   */
  public OrderBuilder clientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
    return this;
  }

  /**
   * Sets the symbol of the order
   *
   * @param symbol The symbol of the order
   * @return The order builder
   */
  public OrderBuilder symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  /**
   * Sets the side of the order
   *
   * @param side The side of the order
   * @return The order builder
   */
  public OrderBuilder side(Side side) {
    this.side = side;
    return this;
  }

  /**
   * Sets the type of the order
   *
   * @param type The type of the order
   * @return The order builder
   */
  public OrderBuilder orderType(OrderType type) {
    this.type = type;
    return this;
  }

  /**
   * Sets the time in force of the order
   *
   * @param timeInForce The time in force of the order
   * @return The order builder
   */
  public OrderBuilder timeInForce(TimeInForce timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

  /**
   * Sets the quantity of the order
   *
   * @param quantity The quantity of the order
   * @return The order builder
   */
  public OrderBuilder quantity(String quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Sets the price of the order
   *
   * @param price The price of the order
   * @return The order builder
   */
  public OrderBuilder price(String price) {
    this.price = price;
    return this;
  }

  /**
   * Sets the stop price of the order
   *
   * @param stopPrice The stop price of the order
   * @return The order builder
   */
  public OrderBuilder stopPrice(String stopPrice) {
    this.stopPrice = stopPrice;
    return this;
  }

  /**
   * Sets the expire time of the order
   *
   * @param expireTime The expire time of the order
   * @return The order builder
   */
  public OrderBuilder expireTime(String expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  /**
   * Sets the strict validate of the order
   *
   * @param strictValidate The strict validate of the order
   * @return The order builder
   */
  public OrderBuilder strictValidate(Boolean strictValidate) {
    this.strictValidate = strictValidate;
    return this;
  }

  /**
   * Sets the post only of the order
   *
   * @param postOnly The post only of the order
   * @return The order builder
   */
  public OrderBuilder postOnly(Boolean postOnly) {
    this.postOnly = postOnly;
    return this;
  }

  /**
   * Sets the take rate of the order
   *
   * @param takeRate The take rate of the order
   * @return The order builder
   */
  public OrderBuilder takeRate(String takeRate) {
    this.takeRate = takeRate;
    return this;
  }

  /**
   * Sets the make rate of the order
   *
   * @param makeRate The make rate of the order
   * @return The order builder
   */
  public OrderBuilder makeRate(String makeRate) {
    this.makeRate = makeRate;
    return this;
  }
}
