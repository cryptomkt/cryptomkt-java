package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Websocket Candle
 */
public class WSCandle {
  @Json(name = "t")
  Long timestamp;
  @Json(name = "o")
  String open;
  @Json(name = "c")
  String close;
  @Json(name = "h")
  String high;
  @Json(name = "l")
  String low;
  @Json(name = "v")
  String volumeBase;
  @Json(name = "q")
  String volumeQuote;

  /**
   * Gets the candle timestamp
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
   * Gets the candle open price
   *
   * @return
   */
  public String getOpen() {
    return open;
  }

  /**
   * Sets the candle open price
   *
   * @param open
   */
  public void setOpen(String open) {
    this.open = open;
  }

  /**
   * Gets the candle close price
   *
   * @return
   */
  public String getClose() {
    return close;
  }

  /**
   * Sets the candle close price
   *
   * @param close
   */
  public void setClose(String close) {
    this.close = close;
  }

  /**
   * Gets the candle high price
   *
   * @return
   */
  public String getHigh() {
    return high;
  }

  /**
   * Sets the candle high price
   *
   * @param high
   */
  public void setHigh(String high) {
    this.high = high;
  }

  /**
   * Gets the candle low price
   *
   * @return
   */
  public String getLow() {
    return low;
  }

  /**
   * Sets the candle low price
   *
   * @param low
   */
  public void setLow(String low) {
    this.low = low;
  }

  /**
   * Gets the candle volume in base currency
   *
   * @return
   */
  public String getBaseVolume() {
    return volumeBase;
  }

  /**
   * Sets the candle volume in base currency
   *
   * @param volumeBase
   */
  public void setBaseVolume(String volumeBase) {
    this.volumeBase = volumeBase;
  }

  /**
   * Gets the candle volume in quote volume
   *
   * @return
   */
  public String getQuoteVolume() {
    return volumeQuote;
  }

  /**
   * Sets the candle quote volume
   *
   * @param volumeQuote
   */
  public void setQuoteVolume(String volumeQuote) {
    this.volumeQuote = volumeQuote;
  }

  @Override
  public String toString() {
    return "WSCandle [close=" + close + ", high=" + high + ", low=" + low + ", open=" + open + ", timestamp="
        + timestamp + ", volumeBase=" + volumeBase + ", volumeQuote=" + volumeQuote + "]";
  }

}
