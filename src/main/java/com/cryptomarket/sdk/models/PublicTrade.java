package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Public Trade
 */
public class PublicTrade {
  private long id;
  @Json(name = "qty")
  private String quantity;
  private String price;
  private String side;
  private String timestamp;

  /**
   * Gets the id of the trade
   *
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id
   *
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the trdde quantity
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
   * Gets the trade side
   *
   * @return
   */
  public String getSide() {
    return side;
  }

  /**
   * Sets the trade side
   *
   * @param side
   */
  public void setSide(String side) {
    this.side = side;
  }

  /**
   * Gets the trade timestamp
   *
   * @return
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the trade timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "Trade [id=" + id + ", price=" + price + ", quantity=" + quantity
        + ", side=" + side + ", timestamp=" + timestamp + "]";
  }
}
