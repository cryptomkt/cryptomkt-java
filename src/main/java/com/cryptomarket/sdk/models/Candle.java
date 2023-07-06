package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Candle
 */
public class Candle {
  private String timestamp;
  private String open;
  private String close;
  private String min;
  private String max;
  private String volume;
  @Json(name = "volume_quote")
  private String volumeQuote;

  /**
   * Gets the candle timestamp
   *
   * @return
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the candle timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the open price
   *
   * @return
   */
  public String getOpen() {
    return open;
  }

  /**
   * Sets the open price
   *
   * @param open
   */
  public void setOpen(String open) {
    this.open = open;
  }

  /**
   * Gets the close price
   *
   * @return
   */
  public String getClose() {
    return close;
  }

  /**
   * Sets the close price
   *
   * @param close
   */
  public void setClose(String close) {
    this.close = close;
  }

  /**
   * Get the minimum price
   *
   * @return
   */
  public String getMin() {
    return min;
  }

  /**
   * Sets the minimum price
   *
   * @param min
   */
  public void setMin(String min) {
    this.min = min;
  }

  /**
   * Gets the maximum price
   *
   * @return
   */
  public String getMax() {
    return max;
  }

  /**
   * Sets the maximum price
   *
   * @param max
   */
  public void setMax(String max) {
    this.max = max;
  }

  /**
   * Gets the volume in base currency
   *
   * @return
   */
  public String getVolume() {
    return volume;
  }

  /**
   * Sets the volume in base currency
   *
   * @param volume
   */
  public void setVolume(String volume) {
    this.volume = volume;
  }

  /**
   * Gets the volume in quote currency
   *
   * @return
   */
  public String getVolumeQuote() {
    return volumeQuote;
  }

  /**
   * Sets the volume in quote currency
   *
   * @param volumeQuote
   */
  public void setVolumeQuote(String volumeQuote) {
    this.volumeQuote = volumeQuote;
  }

  @Override
  public String toString() {
    return "Candle [close=" + close + ", max=" + max + ", min=" + min + ", open=" + open + ", timestamp="
        + timestamp + ", volume=" + volume + ", volumeQuote=" + volumeQuote + "]";
  }

}
