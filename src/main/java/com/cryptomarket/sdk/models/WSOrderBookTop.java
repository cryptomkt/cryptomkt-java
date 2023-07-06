package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Websocket orderbook top
 */
public class WSOrderBookTop {
  @Json(name = "t")
  Long timestamp;
  @Json(name = "a")
  String bestAsk;
  @Json(name = "A")
  String bestAskQuantity;
  @Json(name = "b")
  String bestBid;
  @Json(name = "B")
  String bestBidQuantity;

  /**
   * Gets orderbook top timestamp
   *
   * @return
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the orderbook top timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the orderbook top best ask
   *
   * @return
   */
  public String getBestAsk() {
    return bestAsk;
  }

  /**
   * Sets the orderbook top best ask
   *
   * @param bestAsk
   */
  public void setBestAsk(String bestAsk) {
    this.bestAsk = bestAsk;
  }

  /**
   * Gets the orderbook top best ask quantity
   *
   * @return
   */
  public String getBestAskQuantity() {
    return bestAskQuantity;
  }

  /**
   * Sets the orderbook top best ask quantity
   *
   * @param bestAskQuantity
   */
  public void setBestAskQuantity(String bestAskQuantity) {
    this.bestAskQuantity = bestAskQuantity;
  }

  /**
   * Gets the orderbook top best bid
   *
   * @return
   */
  public String getBestBid() {
    return bestBid;
  }

  /**
   * Sets the orderbook top best bid
   *
   * @param bestBid
   */
  public void setBestBid(String bestBid) {
    this.bestBid = bestBid;
  }

  /**
   * Gets the orderbook top best bid quantity
   *
   * @return
   */
  public String getBestBidQuantity() {
    return bestBidQuantity;
  }

  /**
   * Sets the orderbook top best bid quantity
   *
   * @param bestBidQuantity
   */
  public void setBestBidQuantity(String bestBidQuantity) {
    this.bestBidQuantity = bestBidQuantity;
  }

  @Override
  public String toString() {
    return "WSOrderBookTop [bestAsk=" + bestAsk + ", bestAskQuantity=" + bestAskQuantity + ", bestBid=" + bestBid
        + ", bestBidQuantity=" + bestBidQuantity + ", timestamp=" + timestamp + "]";
  }

}
