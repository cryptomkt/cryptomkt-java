package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Websocket ticker
 */
public class WSTicker {
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
  @Json(name = "p")
  String priceChange;
  @Json(name = "P")
  String priceChangePercent;
  @Json(name = "L")
  Long lastTradeId;

  /**
   * Gets the ticker timestamp
   *
   * @return
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the ticker timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the ticker best ask price
   *
   * @return
   */
  public String getBestAsk() {
    return bestAsk;
  }

  /**
   * Sets the ticker best ask price
   *
   * @param bestAsk
   */
  public void setBestAsk(String bestAsk) {
    this.bestAsk = bestAsk;
  }

  /**
   * Gets the ticker best ask quantity
   *
   * @return
   */
  public String getBestAskQuantity() {
    return bestAskQuantity;
  }

  /**
   * Sets the ticker best ask quantity
   *
   * @param bestAskQuantity
   */
  public void setBestAskQuantity(String bestAskQuantity) {
    this.bestAskQuantity = bestAskQuantity;
  }

  /**
   * Gets the ticker best bid price
   *
   * @return
   */
  public String getBestBid() {
    return bestBid;
  }

  /**
   * Sets the ticker best bid price
   *
   * @param bestBid
   */
  public void setBestBid(String bestBid) {
    this.bestBid = bestBid;
  }

  /**
   * Gets the ticker best bid quantity
   *
   * @return
   */
  public String getBestBidQuantity() {
    return bestBidQuantity;
  }

  /**
   * Sets the ticker best bid quantity
   *
   * @param bestBidQuantity
   */
  public void setBestBidQuantity(String bestBidQuantity) {
    this.bestBidQuantity = bestBidQuantity;
  }

  /**
   * Gets the ticker close price
   *
   * @return
   */
  public String getClose() {
    return close;
  }

  /**
   * Sets the ticker close price
   *
   * @param close
   */
  public void setClose(String close) {
    this.close = close;
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
   * Gets the ticker price change
   *
   * @return
   */
  public String getPriceChange() {
    return priceChange;
  }

  /**
   * Sets the ticker price change
   *
   * @param priceChange
   */
  public void setPriceChange(String priceChange) {
    this.priceChange = priceChange;
  }

  /**
   * Gets the ticker price change percent
   *
   * @return
   */
  public String getPriceChangePercent() {
    return priceChangePercent;
  }

  /**
   * Sets the ticker price change percent
   *
   * @param priceChangePercent
   */
  public void setPriceChangePercent(String priceChangePercent) {
    this.priceChangePercent = priceChangePercent;
  }

  /**
   * Gets the ticker last trade id
   *
   * @return
   */
  public Long getLastTradeId() {
    return lastTradeId;
  }

  /**
   * Sets the ticker last trade id
   *
   * @param lastTradeId
   */
  public void setLastTradeId(Long lastTradeId) {
    this.lastTradeId = lastTradeId;
  }

  /**
   * Gets the ticker volume in base currency
   *
   * @return
   */
  public String getVolumeBase() {
    return volumeBase;
  }

  /**
   * Sets the ticker volume in base currency
   *
   * @param volumeBase
   */
  public void setVolumeBase(String volumeBase) {
    this.volumeBase = volumeBase;
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
   * Sets the ticker volume in quote currency
   *
   * @param volumeQuote
   */
  public void setVolumeQuote(String volumeQuote) {
    this.volumeQuote = volumeQuote;
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

  @Override
  public String toString() {
    return "WSTicker [timestamp=" + timestamp + ", bestAsk=" + bestAsk + ", bestAskQuantity=" + bestAskQuantity
        + ", bestBid=" + bestBid + ", bestBidQuantity=" + bestBidQuantity + ", open=" + open + ", close=" + close
        + ", high=" + high + ", low=" + low + ", volumeBase=" + volumeBase + ", volumeQuote=" + volumeQuote
        + ", priceChange=" + priceChange + ", priceChangePercent=" + priceChangePercent + ", lastTradeId=" + lastTradeId
        + "]";
  }

}
