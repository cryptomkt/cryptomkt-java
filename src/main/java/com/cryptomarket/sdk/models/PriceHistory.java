package com.cryptomarket.sdk.models;

import java.util.List;

/**
 * A history of prices by currency
 */
public class PriceHistory {

  private String currency;
  private List<HistoryPoint> history;

  /**
   * Gets the currency of the price history
   *
   * @return
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
   * Gets the list of price history points
   *
   * @return
   */
  public List<HistoryPoint> getHistory() {
    return history;
  }

  /**
   * Sets the history point
   *
   * @param history
   */
  public void setHistory(List<HistoryPoint> history) {
    this.history = history;
  }

  @Override
  public String toString() {
    return "PriceHistory [currency=" + currency + ", history=" + history + "]";
  }
}
