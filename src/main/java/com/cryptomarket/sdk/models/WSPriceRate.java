package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class WSPriceRate {
  @Json(name = "t")
  private long timestamp;
  @Json(name = "r")
  private String rate;

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestam) {
    this.timestamp = timestam;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "WSPriceRate [timestamp=" + timestamp + ", rate=" + rate + "]";
  }

}
