package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

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

  @Override
  public String toString() {
    return "WSOrderBookTop [bestAsk=" + bestAsk + ", bestAskQuantity=" + bestAskQuantity + ", bestBid=" + bestBid
        + ", bestBidQuantity=" + bestBidQuantity + ", timestamp=" + timestamp + "]";
  }

}
