package com.cryptomarket.sdk.models;

import java.util.List;

import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.params.OrderType;
import com.squareup.moshi.Json;

public class Order {
  @Json(name = "id")
  private String ID;
  @Json(name = "client_order_id")
  private String clientOrderID;
  @Json(name="order_list_id")
  private String orderListID;
  @Json(name="contingency_type")
  private ContingencyType contingencyType;
  private String symbol;
  private String side;
  private OrderStatus status;
  private OrderType type;
  @Json(name = "time_in_force")
  private String timeInForce;
  private String price;
  private String quantity;
  @Json(name = "quantity_cumulative")
  private String quantityCumulative;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "updated_at")
  private String updatedAt;
  @Json(name = "post_only")
  private boolean postOnly;
  @Json(name = "stop_price")
  private String stopPrice;
  @Json(name = "expire_time")
  private String expireTime;
  private List<Trade> trades;
  @Json(name = "original_client_order_id")
  private String originalClientOrderID;

  public String getID() {
    return ID;
  }

  public void setID(String iD) {
    ID = iD;
  }

  public String getClientOrderID() {
    return clientOrderID;
  }

  public void setClientOrderID(String clientOrderID) {
    this.clientOrderID = clientOrderID;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getSide() {
    return side;
  }

  public void setSide(String side) {
    this.side = side;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public OrderType getType() {
    return type;
  }

  public void setType(OrderType type) {
    this.type = type;
  }

  public String getTimeInForce() {
    return timeInForce;
  }

  public void setTimeInForce(String timeInForce) {
    this.timeInForce = timeInForce;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getQuantityCumulative() {
    return quantityCumulative;
  }

  public void setQuantityCumulative(String quantityCumulative) {
    this.quantityCumulative = quantityCumulative;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isPostOnly() {
    return postOnly;
  }

  public void setPostOnly(boolean postOnly) {
    this.postOnly = postOnly;
  }

  public String getStopPrice() {
    return stopPrice;
  }

  public void setStopPrice(String stopPrice) {
    this.stopPrice = stopPrice;
  }

  public String getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(String expireTime) {
    this.expireTime = expireTime;
  }

  public List<Trade> getTrades() {
    return trades;
  }

  public void setTrades(List<Trade> trades) {
    this.trades = trades;
  }

  public String getOriginalClientOrderID() {
    return originalClientOrderID;
  }

  public void setOriginalClientOrderID(String originalClientOrderID) {
    this.originalClientOrderID = originalClientOrderID;
  }


  public String getOrderListID() {
    return orderListID;
  }

  public void setOrderListID(String orderListID) {
    this.orderListID = orderListID;
  }

  public ContingencyType getContingencyType() {
    return contingencyType;
  }

  public void setContingencyType(ContingencyType contingencyType) {
    this.contingencyType = contingencyType;
  }

  @Override
  public String toString() {
    return "Order [ID=" + ID + ", clientOrderID=" + clientOrderID + ", contingencyType=" + contingencyType
        + ", createdAt=" + createdAt + ", expireTime=" + expireTime + ", orderListID=" + orderListID
        + ", originalClientOrderID=" + originalClientOrderID + ", postOnly=" + postOnly + ", price=" + price
        + ", quantity=" + quantity + ", quantityCumulative=" + quantityCumulative + ", side=" + side + ", status="
        + status + ", stopPrice=" + stopPrice + ", symbol=" + symbol + ", timeInForce=" + timeInForce + ", trades="
        + trades + ", type=" + type + ", updatedAt=" + updatedAt + "]";
  }

}