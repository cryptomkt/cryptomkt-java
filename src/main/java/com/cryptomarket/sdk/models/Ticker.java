package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Ticker
 */
public class Ticker {
  private String ask;
  private String bid;
  private String last;
  private String low;
  private String high;
  private String open;
  private String volume;
  @Json(name = "volume_quote")
  private String volumeQuote;
  private String timestamp;

  /**
   * Gets the ticker ask price
   *
   * @return
   */
  public String getAsk() {
    return ask;
  }

  /**
   * Sets the ticker ask price
   *
   * @param ask
   */
  public void setAsk(String ask) {
    this.ask = ask;
  }

  /**
   * Gets the ticker bid price
   *
   * @return
   */
  public String getBid() {
    return bid;
  }

  /**
   * Sets the ticker bid price
   *
   * @param bid
   */
  public void setBid(String bid) {
    this.bid = bid;
  }

  /**
   * Gets the ticker last price
   *
   * @return
   */
  public String getLast() {
    return last;
  }

  /**
   * Sets the ticker last price
   *
   * @param last
   */
  public void setLast(String last) {
    this.last = last;
  }

  /**
   * Gets the ticker low price
   *
   * @return
   */
  public String getLow() {
    return low;
  }

  /**
   * Sets the ticker low price
   *
   * @param low
   */
  public void setLow(String low) {
    this.low = low;
  }

  /**
   * Gets the ticker high price
   *
   * @return
   */
  public String getHigh() {
    return high;
  }

  /**
   * Sets the ticker high price
   *
   * @param high
   */
  public void setHigh(String high) {
    this.high = high;
  }

  /**
   * Gets the ticker open price
   *
   * @return
   */
  public String getOpen() {
    return open;
  }

  /**
   * Sets the ticker open price
   *
   * @param open
   */
  public void setOpen(String open) {
    this.open = open;
  }

  /**
   * Gets the ticker volume in base currency
   *
   * @return
   */
  public String getVolume() {
    return volume;
  }

  /**
   * Sets the ticker volume
   *
   * @param volume
   */
  public void setVolume(String volume) {
    this.volume = volume;
  }

  /**
   * Gets the ticker volume in quote currency
   *
   * @return
   */
  public String getVolumeQuote() {
    return volumeQuote;
  }

  /**
   * Sets the ticker volume quote
   *
   * @param volumeQuote
   */
  public void setVolumeQuote(String volumeQuote) {
    this.volumeQuote = volumeQuote;
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
   * Sets the ticker timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "Ticker [ask=" + ask + ", bid=" + bid + ", high=" + high + ", last=" + last + ", low=" + low + ", open="
        + open + ", timestamp=" + timestamp + ", volume=" + volume + ", volumeQuote=" + volumeQuote + "]";
  }

}
