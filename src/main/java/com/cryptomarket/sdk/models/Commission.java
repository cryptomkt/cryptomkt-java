package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Commission {
  private String symbol;
  @Json(name = "take_rate")
  private String takeRate;
  @Json(name = "make_rate")
  private String makeRate;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getTakeRate() {
    return takeRate;
  }

  public void setTakeRate(String takeRate) {
    this.takeRate = takeRate;
  }

  public String getMakeRate() {
    return makeRate;
  }

  public void setMakeRate(String makeRate) {
    this.makeRate = makeRate;
  }

  @Override
  public String toString() {
    return "Commission [makeRate=" + makeRate + ", symbol=" + symbol + ", takeRate=" + takeRate + "]";
  }

}
