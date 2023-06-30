package com.cryptomarket.sdk.models;

import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.ReportType;
import com.cryptomarket.params.Side;
import com.squareup.moshi.Json;

public class Report {
  private String id;
  @Json(name = "client_order_id")
  private String clientOrderId;
  private String symbol;
  private Side side;
  private OrderStatus status;
  private OrderType type;
  @Json(name = "time_in_force")
  private String timeInForce;
  private String quantity;
  private String price;
  @Json(name = "quantity_cumulative")
  private String quantityCumulative;
  @Json(name = "post_only")
  private Boolean postOnly;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "updated_at")
  private String updatedAt;
  @Json(name = "stop_price")
  private String stopPrice;
  @Json(name = "expire_time")
  private String expireTime;
  @Json(name = "report_type")
  private ReportType reportType;
  @Json(name = "trade_id")
  private String tradeId;
  @Json(name = "trade_quantity")
  private String tradeQuantity;
  @Json(name = "trade_price")
  private String tradePrice;
  @Json(name = "trade_taker")
  private String tradeFee;
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

  public String getTimeInForce() {
    return timeInForce;
  }

  public void setTimeInForce(String timeInForce) {
    this.timeInForce = timeInForce;
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

  public String getQuantityCumulative() {
    return quantityCumulative;
  }

  public void setQuantityCumulative(String quantityCumulative) {
    this.quantityCumulative = quantityCumulative;
  }

  public Boolean isPostOnly() {
    return postOnly;
  }

  public void setPostOnly(Boolean postOnly) {
    this.postOnly = postOnly;
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

  public ReportType getReportType() {
    return reportType;
  }

  public void setReportType(ReportType reportType) {
    this.reportType = reportType;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getTradeQuantity() {
    return tradeQuantity;
  }

  public void setTradeQuantity(String tradeQuantity) {
    this.tradeQuantity = tradeQuantity;
  }

  public String getTradePrice() {
    return tradePrice;
  }

  public void setTradePrice(String tradePrice) {
    this.tradePrice = tradePrice;
  }

  public String getTradeFee() {
    return tradeFee;
  }

  public void setTradeFee(String tradeFee) {
    this.tradeFee = tradeFee;
  }

  public String getOriginalClientOrderId() {
    return originalClientOrderId;
  }

  public void setOriginalClientOrderId(String originalClientOrderId) {
    this.originalClientOrderId = originalClientOrderId;
  }

  @Override
  public String toString() {
    return "Report [id=" + id + ", clientOrderId=" + clientOrderId + ", createdAt=" + createdAt + ", expireTime="
        + expireTime + ", originalClientOrderId=" + originalClientOrderId + ", postOnly=" + postOnly + ", price="
        + price + ", quantity=" + quantity + ", quantityCumulative=" + quantityCumulative + ", reportType=" + reportType
        + ", side=" + side + ", status=" + status + ", stopPrice=" + stopPrice + ", symbol=" + symbol + ", timeInForce="
        + timeInForce + ", tradeFee=" + tradeFee + ", tradeId=" + tradeId + ", tradePrice=" + tradePrice
        + ", tradeQuantity=" + tradeQuantity + ", type=" + type + ", updatedAt=" + updatedAt + "]";
  }
}
