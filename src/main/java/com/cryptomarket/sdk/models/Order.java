package com.cryptomarket.sdk.models;

import java.util.List;

import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.TimeInForce;
import com.squareup.moshi.Json;

public class Order {
  private String id;
  @Json(name = "client_order_id")
  private String clientOrderId;
  @Json(name = "order_list_id")
  private String orderListId;
  @Json(name = "contingency_type")
  private ContingencyType contingencyType;
  private String symbol;
  private Side side;
  private OrderStatus status;
  private OrderType type;
  @Json(name = "time_in_force")
  private TimeInForce timeInForce;
  private String price;
  private String quantity;
  @Json(name = "quantity_cumulative")
  private String quantityCumulative;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "updated_at")
  private String updatedAt;
  @Json(name = "post_only")
  private Boolean postOnly;
  @Json(name = "stop_price")
  private String stopPrice;
  @Json(name = "expire_time")
  private String expireTime;
  private List<Trade> trades;
  @Json(name = "original_client_order_id")
  private String originalClientOrderId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  public String getOrderListId() {
    return orderListId;
  }

  public void setOrderListId(String orderListId) {
    this.orderListId = orderListId;
  }

  public ContingencyType getContingencyType() {
    return contingencyType;
  }

  public void setContingencyType(ContingencyType contingencyType) {
    this.contingencyType = contingencyType;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Side getSide() {
    return side;
  }

  public void setSide(Side side) {
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

  public TimeInForce getTimeInForce() {
    return timeInForce;
  }

  public void setTimeInForce(TimeInForce timeInForce) {
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

  public Boolean isPostOnly() {
    return postOnly;
  }

  public void setPostOnly(Boolean postOnly) {
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

  public String getOriginalClientOrderId() {
    return originalClientOrderId;
  }

  public void setOriginalClientOrderId(String originalClientOrderId) {
    this.originalClientOrderId = originalClientOrderId;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", clientOrderId=" + clientOrderId + ", orderListId=" + orderListId
        + ", contingencyType=" + contingencyType + ", symbol=" + symbol + ", side=" + side + ", status=" + status
        + ", type=" + type + ", timeInForce=" + timeInForce + ", price=" + price + ", quantity=" + quantity
        + ", quantityCumulative=" + quantityCumulative + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
        + ", postOnly=" + postOnly + ", stopPrice=" + stopPrice + ", expireTime=" + expireTime + ", trades=" + trades
        + ", originalClientOrderId=" + originalClientOrderId + "]";
  }
}