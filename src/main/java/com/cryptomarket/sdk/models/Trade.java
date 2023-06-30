package com.cryptomarket.sdk.models;

import com.cryptomarket.params.Side;
import com.squareup.moshi.Json;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Side getSide() {
    return side;
  }

  public void setSide(Side side) {
    this.side = side;
  }

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

  public Boolean isTaker() {
    return taker;
  }

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