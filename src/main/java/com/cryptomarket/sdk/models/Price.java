package com.cryptomarket.sdk.models;

/**
 * Price
 */
public class Price {
  private String currency;
  private String price;
  private String timestamp;

  /**
   * Gets the price currency
   *
   * @return
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the price currency
   *
   * @param currency
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the quotation price
   *
   * @return
   */
  public String getPrice() {
    return price;
  }

  /**
   * Sets the quotation price
   *
   * @param price
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Gets the timestamp
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
    return "Price [currency=" + currency + ", price=" + price + ", timestamp=" + timestamp + "]";
  }
}
