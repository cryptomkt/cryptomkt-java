package com.cryptomarket.sdk.models;

import java.util.List;

import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.params.OrderType;
import com.cryptomarket.params.Side;
import com.cryptomarket.params.TimeInForce;
import com.squareup.moshi.Json;

/**
 * Order
 */
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
  @Json(name = "price_average")
  private String averagePrice;
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

  /**
   * Gets the order id. assigned by the exchange
   *
   * @return
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the order id
   *
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the client order id assigned by the user, or by the server if the user
   * did not define it
   *
   * @return
   */
  public String getClientOrderId() {
    return clientOrderId;
  }

  /**
   * Sets the client order id
   *
   * @param clientOrderId
   */
  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  /**
   * Gets the order list id. if the order was created in a list
   *
   * @return
   */
  public String getOrderListId() {
    return orderListId;
  }

  /**
   * Sets the order list id
   *
   * @param orderListId
   */
  public void setOrderListId(String orderListId) {
    this.orderListId = orderListId;
  }

  /**
   * Gets the contingencyType of the order. If the order was created in an order
   * list
   *
   * @return
   */
  public ContingencyType getContingencyType() {
    return contingencyType;
  }

  /**
   * Sets the contingency type
   *
   * @param contingencyType
   */
  public void setContingencyType(ContingencyType contingencyType) {
    this.contingencyType = contingencyType;
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
   * Gets the order side
   *
   * @return
   */
  public Side getSide() {
    return side;
  }

  /**
   * Sets the order side
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
   * Gets the time in force of the order
   *
   * @return
   */
  public TimeInForce getTimeInForce() {
    return timeInForce;
  }

  /**
   * Sets the time in force of the order
   *
   * @param timeInForce
   */
  public void setTimeInForce(TimeInForce timeInForce) {
    this.timeInForce = timeInForce;
  }

  /**
   * Gets the order price
   *
   * @return
   */
  public String getPrice() {
    return price;
  }

  /**
   * Sets the order price
   *
   * @param price
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * gets the average price of the executed order quantity
   *
   * @return
   */
  public String getAveragePrice() {
    return averagePrice;
  }

  public void setAveragePrice(String averagePrice) {
    this.averagePrice = averagePrice;
  }

  /**
   * Gets the order quantity
   *
   * @return
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * Sets the order quantity
   *
   * @param quantity
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the quantity of the order already executed
   *
   * @return
   */
  public String getQuantityCumulative() {
    return quantityCumulative;
  }

  /**
   * Sets the quantity cumulative
   *
   * @param quantityCumulative
   */
  public void setQuantityCumulative(String quantityCumulative) {
    this.quantityCumulative = quantityCumulative;
  }

  /**
   * Gets the created datetime of the order
   *
   * @return
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * sets the created datetime of the order
   *
   * @param createdAt
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the updated datetime of the order
   *
   * @return
   */
  public String getUpdatedAt() {
    return updatedAt;
  }

  /**
   * Sets the updated datetime of the order
   *
   * @param updatedAt
   */
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   *
   * @return True if is a post only order
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
   * Get the order expire time
   *
   * @return
   */
  public String getExpireTime() {
    return expireTime;
  }

  /**
   * Sets the order expired time
   *
   * @param expireTime
   */
  public void setExpireTime(String expireTime) {
    this.expireTime = expireTime;
  }

  /**
   * Gets the trades fullfilling this order. never returned for an order of an
   * order list
   *
   * @return
   */
  public List<Trade> getTrades() {
    return trades;
  }

  /**
   * Sets the list of trades
   *
   * @param trades
   */
  public void setTrades(List<Trade> trades) {
    this.trades = trades;
  }

  /**
   * Gets the original client order id of the order
   *
   * @return
   */
  public String getOriginalClientOrderId() {
    return originalClientOrderId;
  }

  /**
   * Gets the original client order id of the order
   *
   * @param originalClientOrderId
   */
  public void setOriginalClientOrderId(String originalClientOrderId) {
    this.originalClientOrderId = originalClientOrderId;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", clientOrderId=" + clientOrderId + ", orderListId=" + orderListId
        + ", contingencyType=" + contingencyType + ", symbol=" + symbol + ", side=" + side + ", status=" + status
        + ", type=" + type + ", timeInForce=" + timeInForce + ", price=" + price + ", averagePrice=" + averagePrice
        + ", quantity=" + quantity + ", quantityCumulative=" + quantityCumulative + ", createdAt=" + createdAt
        + ", updatedAt=" + updatedAt + ", postOnly=" + postOnly + ", stopPrice=" + stopPrice + ", expireTime="
        + expireTime + ", trades=" + trades + ", originalClientOrderId=" + originalClientOrderId + "]";
  }
}
