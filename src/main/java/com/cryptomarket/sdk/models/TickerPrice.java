package com.cryptomarket.sdk.models;

/**
 * Ticker price
 */
public class TickerPrice {

  private String price;
  private String timestamp;

  /**
   * Get the ticker price
   *
   * @return
   */
  public String getPrice() {
    return price;
  }

  /**
   * Sets the ticker price
   *
   * @param price
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Gets the ticker timestamp
   *
   * @return
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "TickerPrice [price=" + price + ", timestamp=" + timestamp + "]";
  }
}
