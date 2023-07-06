package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

/**
 * Websocket orderbook
 */
public class WSOrderBook {
  @Json(name = "t")
  Long timestamp;
  @Json(name = "s")
  Long sequence;
  @Json(name = "a")
  List<List<String>> asks;
  @Json(name = "b")
  List<List<String>> bids;

  /**
   * Gets the orderbook timestamp
   *
   * @return
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the orderbook timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the orderbook sequence
   *
   * @return
   */
  public Long getSequence() {
    return sequence;
  }

  /**
   * Sets the orderbook sequence
   *
   * @param sequence
   */
  public void setSequence(Long sequence) {
    this.sequence = sequence;
  }

  /**
   * gets the orderbook asks
   *
   * @return
   */
  public List<List<String>> getAsks() {
    return asks;
  }

  /**
   * Sets the orderbook asks
   *
   * @param asks
   */
  public void setAsks(List<List<String>> asks) {
    this.asks = asks;
  }

  /**
   * Gets the orderbook bids
   *
   * @return
   */
  public List<List<String>> getBids() {
    return bids;
  }

  /**
   * Sets the orderbook bids
   *
   * @param bids
   */
  public void setBids(List<List<String>> bids) {
    this.bids = bids;
  }

  @Override
  public String toString() {
    return "WSOrderBook [asks=" + asks + ", bids=" + bids + ", sequence=" + sequence + ", timestamp=" + timestamp + "]";
  }

}
