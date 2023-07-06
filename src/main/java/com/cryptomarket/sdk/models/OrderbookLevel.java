package com.cryptomarket.sdk.models;

/**
 * Orderbook level. A amount of able to beeing buyed or selled for a given price
 */
public class OrderbookLevel {
  private String price;
  private String amount;

  /**
   * Gets the price of the orderbook level
   *
   * @return
   */
  public String getPrice() {
    return price;
  }

  /**
   * Sets the price of the orderbook level
   *
   * @param price
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Gets the amount of the orderbook level
   *
   * @return
   */
  public String getAmount() {
    return amount;
  }

  /**
   * Sets the amount of the orderbook level
   * @param amount
   */
  public void setAmount(String amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "OrderbookLevel [amount=" + amount + ", price=" + price + "]";
  }

}
