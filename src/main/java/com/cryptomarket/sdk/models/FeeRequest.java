package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class FeeRequest {
  private String currency;
  private String amount;
  @Json(name = "network_code")
  private String networkCode;

  public FeeRequest(String currency, String amount, String networkCode) {
    this.currency = currency;
    this.amount = amount;
    this.networkCode = networkCode;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getNetworkCode() {
    return networkCode;
  }

  public void setNetworkCode(String networkCode) {
    this.networkCode = networkCode;
  }

  @Override
  public String toString() {
    return "FeeRequest [currency=" + currency + ", amount=" + amount + ", networkCode=" + networkCode + "]";
  }
}
