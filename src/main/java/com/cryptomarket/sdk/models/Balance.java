package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Balance {
  private String currency;
  private String available;
  private String reserved;
  @Json(name = "reserved_margin")
  private String reservedMargin;

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAvailable() {
    return available;
  }

  public void setAvailable(String available) {
    this.available = available;
  }

  public String getReserved() {
    return reserved;
  }

  public void setReserved(String reserved) {
    this.reserved = reserved;
  }

  public String getReservedMargin() {
    return reservedMargin;
  }

  public void setReservedMargin(String reservedMargin) {
    this.reservedMargin = reservedMargin;
  }

  @Override
  public String toString() {
    return "Balance [available=" + available + ", currency=" + currency + ", reserved=" + reserved + ", reservedMargin="
        + reservedMargin + "]";
  }
}