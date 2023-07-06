package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Websocket public trade
 */
public class WSPublicTrade {
  @Json(name = "t")
  private Long timestamp;
  @Json(name = "i")
  private Long id;
  @Json(name = "p")
  private String price;
  @Json(name = "q")
  private String quantity;
  @Json(name = "s")
  private String side;

  /**
   * Gets the public trade timestamp
   *
   * @return
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the public trade id
   *
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the public trade id
   *
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the public trade price
   *
   * @return
   */
  public String getPrice() {
    return price;
  }

  /**
   * Sets the public trade price
   *
   * @param price
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Gets the public trade quantity
   *
   * @return
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * Sets the public trade quantity
   *
   * @param quantity
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the public trade side
   *
   * @return
   */
  public String getSide() {
    return side;
  }

  /**
   * Sets the public trade side
   *
   * @param side
   */
  public void setSide(String side) {
    this.side = side;
  }

  @Override
  public String toString() {
    return "WSPublicTrade [timestamp=" + timestamp + ", id=" + id + ", price=" + price + ", quantity=" + quantity
        + ", side=" + side + "]";
  }

}
