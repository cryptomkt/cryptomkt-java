package com.cryptomarket.sdk.models;

public class Fee {
  private String fee;
  private String networkFee;
  private String amount;
  private String currency;

  public Fee(String fee, String networkFee, String amount, String currency) {
    this.fee = fee;
    this.networkFee = networkFee;
    this.amount = amount;
    this.currency = currency;
  }

  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
  }

  public String getNetworkFee() {
    return networkFee;
  }

  public void setNetworkFee(String networkFee) {
    this.networkFee = networkFee;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "Fee [fee=" + fee + ", networkFee=" + networkFee + ", amount=" + amount + ", currency=" + currency + "]";
  }

}
