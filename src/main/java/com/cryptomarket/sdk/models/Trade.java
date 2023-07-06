package com.cryptomarket.sdk.models;

import com.cryptomarket.params.Side;
import com.squareup.moshi.Json;

/**
 * Trade
 */
public class Trade {
  private long id;
  @Json(name = "client_order_id")
  private String clientOrderId;
  @Json(name = "order_id")
  private String orderId;
  private String symbol;
  private String quantity;
  private String price;
  private Side side;
  private String fee;
  private String timestamp;
  private Boolean taker;

  /**
   * Gets the trade id
   *
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the trade id
   *
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the client order id of the trade
   *
   * @return
   */
  public String getClientOrderId() {
    return clientOrderId;
  }

  /**
   * Sets the client orde rid of the trade
   *
   * @param clientOrderId
   */
  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  /**
   * Gets the trade order id
   *
   * @return
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * Sets the trade order id
   *
   * @param orderId
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * Gets the trade symbol
   *
   * @return
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Sets the trade symbol
   *
   * @param symbol
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Gets trade quantity
   *
   * @return
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * Sets trade quantity
   *
   * @param quantity
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets trade price
   *
   * @return
   */
  public String getPrice() {
    return price;
  }

  /**
   * Sets the trade price
   *
   * @param price
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Gets the trade side
   *
   * @return
   */
  public Side getSide() {
    return side;
  }

  /**
   * Sets the side
   *
   * @param side
   */
  public void setSide(Side side) {
    this.side = side;
  }

  /**
   * Gets the trade fee
   *
   * @return
   */
  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  /**
   *
   * @return True if taker
   */
  public Boolean isTaker() {
    return taker;
  }

  /**
   * Sets the taker flag
   *
   * @param taker
   */
  public void setTaker(Boolean taker) {
    this.taker = taker;
  }

  @Override
  public String toString() {
    return "Trade [id=" + id + ", clientOrderId=" + clientOrderId + ", orderId=" + orderId + ", symbol=" + symbol
        + ", quantity=" + quantity + ", price=" + price + ", side=" + side + ", fee=" + fee + ", timestamp=" + timestamp
        + ", taker=" + taker + "]";
  }
}
