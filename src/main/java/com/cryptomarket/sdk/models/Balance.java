package com.cryptomarket.sdk.models;

public class Balance {
  private String currency;
  private String available;
  private String reserved;

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

  @Override
  public String toString() {
    return "Balance [currency=" + currency + ", available=" + available + ", reserved=" + reserved + "]";
  }
}