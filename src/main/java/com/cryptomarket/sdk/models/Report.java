package com.cryptomarket.sdk.models;

import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.ReportType;
import com.cryptomarket.params.Side;
import com.squareup.moshi.Json;

/**
 * An order report or a trade report
 */
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

  /**
   * Gets the order id of the order
   *
   * @return
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the order id of the order
   *
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the client order id of the order
   *
   * @return
   */
  public String getClientOrderId() {
    return clientOrderId;
  }

  /**
   * Sets the client order id of the order
   *
   * @param clientOrderId
   */
  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  /**
   * Gets the symbol of the order
   *
   * @return
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Sets the symbol of the order
   *
   * @param symbol
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Gets the side of the order
   *
   * @return
   */
  public Side getSide() {
    return side;
  }

  /**
   * Sets the side of the order
   *
   * @param side
   */
  public void setSide(Side side) {
    this.side = side;
  }

  /**
   * Gets the order status
   *
   * @return
   */
  public OrderStatus getStatus() {
    return status;
  }

  /**
   * Sets the order status
   *
   * @param status
   */
  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  /**
   * Gets the order type
   *
   * @return
   */
  public OrderType getType() {
    return type;
  }

  /**
   * Sets the order type
   *
   * @param type
   */
  public void setType(OrderType type) {
    this.type = type;
  }

  /**
   * Gets the time in force
   *
   * @return
   */
  public String getTimeInForce() {
    return timeInForce;
  }

  /**
   * Sets the time in force
   *
   * @param timeInForce
   */
  public void setTimeInForce(String timeInForce) {
    this.timeInForce = timeInForce;
  }

  /**
   * Gets the quality
   *
   * @return
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity
   *
   * @param quantity
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the price
   *
   * @return
   */
  public String getPrice() {
    return price;
  }

  /**
   * Sets the price
   *
   * @param price
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Gets the order quantity cumulative
   *
   * @return
   */
  public String getQuantityCumulative() {
    return quantityCumulative;
  }

  /**
   * Sets the order quantity cumulative
   *
   * @param quantityCumulative
   */
  public void setQuantityCumulative(String quantityCumulative) {
    this.quantityCumulative = quantityCumulative;
  }

  /**
   *
   * @return True if the order is post only
   */
  public Boolean isPostOnly() {
    return postOnly;
  }

  /**
   * Sets the post only flag
   *
   * @param postOnly
   */
  public void setPostOnly(Boolean postOnly) {
    this.postOnly = postOnly;
  }

  /**
   * Gets the created datetime of the order or the trade
   *
   * @return
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the created datetime
   *
   * @param createdAt
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the updated datetime of the order or the trade
   *
   * @return
   */
  public String getUpdatedAt() {
    return updatedAt;
  }

  /**
   * Sets the updated datetime
   *
   * @param updatedAt
   */
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   * Gets the order stop price
   *
   * @return
   */
  public String getStopPrice() {
    return stopPrice;
  }

  /**
   * Sets the order stop price
   *
   * @param stopPrice
   */
  public void setStopPrice(String stopPrice) {
    this.stopPrice = stopPrice;
  }

  /**
   * Gets the order expire time
   *
   * @return
   */
  public String getExpireTime() {
    return expireTime;
  }

  /**
   * Sets the expire time
   *
   * @param expireTime
   */
  public void setExpireTime(String expireTime) {
    this.expireTime = expireTime;
  }

  /**
   * Gets the report type
   *
   * @return
   */
  public ReportType getReportType() {
    return reportType;
  }

  /**
   * Sets the report type
   *
   * @param reportType
   */
  public void setReportType(ReportType reportType) {
    this.reportType = reportType;
  }

  /**
   * Gets the trade id
   *
   * @return
   */
  public String getTradeId() {
    return tradeId;
  }

  /**
   * Sets the trade id
   *
   * @param tradeId
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  /**
   * Gets the trade quantity
   *
   * @return
   */
  public String getTradeQuantity() {
    return tradeQuantity;
  }

  /**
   * Sets the trade quantity
   *
   * @param tradeQuantity
   */
  public void setTradeQuantity(String tradeQuantity) {
    this.tradeQuantity = tradeQuantity;
  }

  /**
   * Gets the trade price
   *
   * @return
   */
  public String getTradePrice() {
    return tradePrice;
  }

  /**
   * Sets the trade price
   *
   * @param tradePrice
   */
  public void setTradePrice(String tradePrice) {
    this.tradePrice = tradePrice;
  }

  /**
   * Gets the trade fee
   *
   * @return
   */
  public String getTradeFee() {
    return tradeFee;
  }

  /**
   * Sets the trade fee
   *
   * @param tradeFee
   */
  public void setTradeFee(String tradeFee) {
    this.tradeFee = tradeFee;
  }

  /**
   * Gets the original client order id
   *
   * @return
   */
  public String getOriginalClientOrderId() {
    return originalClientOrderId;
  }

  /**
   * Sets the original client order id
   *
   * @param originalClientOrderId
   */
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
