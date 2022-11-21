package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

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
  Long LastTradeID;

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getBestAsk() {
    return bestAsk;
  }

  public void setBestAsk(String bestAsk) {
    this.bestAsk = bestAsk;
  }

  public String getBestAskQuantity() {
    return bestAskQuantity;
  }

  public void setBestAskQuantity(String bestAskQuantity) {
    this.bestAskQuantity = bestAskQuantity;
  }

  public String getBestBid() {
    return bestBid;
  }

  public void setBestBid(String bestBid) {
    this.bestBid = bestBid;
  }

  public String getBestBidQuantity() {
    return bestBidQuantity;
  }

  public void setBestBidQuantity(String bestBidQuantity) {
    this.bestBidQuantity = bestBidQuantity;
  }

  public String getClose() {
    return close;
  }

  public void setClose(String close) {
    this.close = close;
  }

  public String getHigh() {
    return high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getLow() {
    return low;
  }

  public void setLow(String low) {
    this.low = low;
  }

  public String getPriceChange() {
    return priceChange;
  }

  public void setPriceChange(String priceChange) {
    this.priceChange = priceChange;
  }

  public String getPriceChangePercent() {
    return priceChangePercent;
  }

  public void setPriceChangePercent(String priceChangePercent) {
    this.priceChangePercent = priceChangePercent;
  }

  public Long getLastTradeID() {
    return LastTradeID;
  }

  public void setLastTradeID(Long lastTradeID) {
    LastTradeID = lastTradeID;
  }

  public String getVolumeBase() {
    return volumeBase;
  }

  public void setVolumeBase(String volumeBase) {
    this.volumeBase = volumeBase;
  }

  public String getVolumeQuote() {
    return volumeQuote;
  }

  public void setVolumeQuote(String volumeQuote) {
    this.volumeQuote = volumeQuote;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }

  @Override
  public String toString() {
    return "WSTicker [LastTradeID=" + LastTradeID + ", bestAsk=" + bestAsk + ", bestAskQuantity=" + bestAskQuantity
        + ", bestBid=" + bestBid + ", bestBidQuantity=" + bestBidQuantity + ", close=" + close + ", high=" + high
        + ", low=" + low + ", open=" + open + ", priceChange=" + priceChange + ", priceChangePercent="
        + priceChangePercent + ", timestamp=" + timestamp + ", volumeBase=" + volumeBase + ", volumeQuote="
        + volumeQuote + "]";
  }

}
