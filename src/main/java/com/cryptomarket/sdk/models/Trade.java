package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Trade {
  private long ID;
  @Json(name = "client_order_id")
  private String clientOrderId;
  @Json(name = "order_id")
  private String orderId;
  private String symbol;
  private String quantity;
  private String price;
  private String side;
  private String fee;
  private String timestamp;
  private boolean taker;

  public long getId() {
    return ID;
  }

  public void setId(long id) {
    this.ID = id;
  }

  public boolean isTaker() {
    return taker;
  }

  public void setTaker(boolean taker) {
    this.taker = taker;
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

  public String getSide() {
    return side;
  }

  public void setSide(String side) {
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

  @Override
  public String toString() {
    return "Trade [clientOrderId=" + clientOrderId + ", fee=" + fee + ", id=" + ID + ", orderId=" + orderId
        + ", price=" + price + ", quantity=" + quantity + ", side=" + side + ", symbol=" + symbol
        + ", timestamp=" + timestamp + ", taker=" + taker + "]";
  }

}