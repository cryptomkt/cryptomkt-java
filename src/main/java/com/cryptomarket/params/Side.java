package com.cryptomarket.params;

import com.squareup.moshi.Json;

/** side */
public enum Side {
  /** buy */
  @Json(name = "buy")
  BUY("buy"),
  /** sell */
  @Json(name = "sell")
  SELL("sell");

  private final String label;

  private Side(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
