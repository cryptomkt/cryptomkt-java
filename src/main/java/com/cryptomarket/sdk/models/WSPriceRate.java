package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Websocket price rate
 */
public class WSPriceRate {
  @Json(name = "t")
  private long timestamp;
  @Json(name = "r")
  private String rate;

  /**
   * Gets the price rate timestamp
   *
   * @return
   */
  public long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the price rate timestamp
   *
   * @param timestam
   */
  public void setTimestamp(long timestam) {
    this.timestamp = timestam;
  }

  /**
   * Gets the rate of the price rate
   *
   * @return
   */
  public String getRate() {
    return rate;
  }

  /**
   * Sets the rate of the price rate
   *
   * @param rate
   */
  public void setRate(String rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "WSPriceRate [timestamp=" + timestamp + ", rate=" + rate + "]";
  }

}
