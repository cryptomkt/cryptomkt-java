package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Address {
  private String currency;
  private String address;
  @Json(name = "payment_id")
  private String paymentId;
  @Json(name = "public_key")
  private String publicKey;
  @Json(name = "network_code")
  private String networkCode;

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getNetworkCode() {
    return networkCode;
  }

  public void setNetworkCode(String networkCode) {
    this.networkCode = networkCode;
  }

  @Override
  public String toString() {
    return "Address [currency=" + currency + ", address=" + address + ", paymentId=" + paymentId + ", publicKey="
        + publicKey + ", networkCode=" + networkCode + "]";
  }
}