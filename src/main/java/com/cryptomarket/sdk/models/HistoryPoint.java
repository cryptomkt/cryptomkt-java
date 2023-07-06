package com.cryptomarket.sdk.models;

/**
 * Price history point
 */
public class HistoryPoint {
  private String timestamp;
  private String open;
  private String close;
  private String min;
  private String max;

  /**
   * Gets the timestamp of the price point
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

  /**
   * Gets the open price of the price history point
   *
   * @return
   */
  public String getOpen() {
    return open;
  }

  /**
   * Sets the open price of the price history point
   *
   * @param open
   */
  public void setOpen(String open) {
    this.open = open;
  }

  /**
   * Gets the close price of the price history point
   *
   * @return
   */
  public String getClose() {
    return close;
  }

  /**
   * Sets the close price of the price history point
   *
   * @param close
   */
  public void setClose(String close) {
    this.close = close;
  }

  /**
   * Gets the minimum price of the price history point
   *
   * @return
   */
  public String getMin() {
    return min;
  }

  /**
   * Sets the minimum price of the price history point
   *
   * @param min
   */
  public void setMin(String min) {
    this.min = min;
  }

  /**
   * Gets the maximum price of the price history point
   *
   * @return
   */
  public String getMax() {
    return max;
  }

  /**
   * Sets the maximum price of the price history point
   *
   * @param max
   */
  public void setMax(String max) {
    this.max = max;
  }

  @Override
  public String toString() {
    return "HistoryPoint [close=" + close + ", max=" + max + ", min=" + min + ", open=" + open + ", timestamp="
        + timestamp + "]";
  }

}
