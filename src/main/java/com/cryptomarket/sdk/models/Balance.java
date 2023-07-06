package com.cryptomarket.sdk.models;

/**
 * User Balance
 */
public class Balance {
  private String currency;
  private String available;
  private String reserved;

  /**
   * Gets the currency of the balance
   *
   * @return A currency code
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the currency
   *
   * @param currency
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the available balance
   *
   * @return
   */
  public String getAvailable() {
    return available;
  }

  /**
   * Sets the available balance
   *
   * @param available
   */
  public void setAvailable(String available) {
    this.available = available;
  }

  /**
   * Gets the reserved balance
   *
   * @return
   */
  public String getReserved() {
    return reserved;
  }

  /**
   * Sets the reserved balance
   *
   * @param reserved
   */
  public void setReserved(String reserved) {
    this.reserved = reserved;
  }

  @Override
  public String toString() {
    return "Balance [currency=" + currency + ", available=" + available + ", reserved=" + reserved + "]";
  }
}
