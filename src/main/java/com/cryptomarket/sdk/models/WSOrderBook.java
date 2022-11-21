package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

public class WSOrderBook {
  @Json(name = "t")
  Long timestamp;
  @Json(name = "s")
  Long sequence;
  @Json(name = "a")
  List<List<String>> asks;
  @Json(name = "b")
  List<List<String>> bids;
  public Long getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }
  public Long getSequence() {
    return sequence;
  }
  public void setSequence(Long sequence) {
    this.sequence = sequence;
  }
  public List<List<String>> getAsks() {
    return asks;
  }
  public void setAsks(List<List<String>> asks) {
    this.asks = asks;
  }
  public List<List<String>> getBids() {
    return bids;
  }
  public void setBids(List<List<String>> bids) {
    this.bids = bids;
  }
  @Override
  public String toString() {
    return "WSOrderBook [asks=" + asks + ", bids=" + bids + ", sequence=" + sequence + ", timestamp=" + timestamp + "]";
  }

}
