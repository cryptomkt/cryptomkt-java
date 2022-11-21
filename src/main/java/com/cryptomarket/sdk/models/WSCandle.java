package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

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

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
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

  public String getBaseVolume() {
    return volumeBase;
  }

  public void setBaseVolume(String volumeBase) {
    this.volumeBase = volumeBase;
  }

  public String getQuoteVolume() {
    return volumeQuote;
  }

  public void setQuoteVolume(String volumeQuote) {
    this.volumeQuote = volumeQuote;
  }

  @Override
  public String toString() {
    return "WSCandle [close=" + close + ", high=" + high + ", low=" + low + ", open=" + open + ", timestamp="
        + timestamp + ", volumeBase=" + volumeBase + ", volumeQuote=" + volumeQuote + "]";
  }

}
