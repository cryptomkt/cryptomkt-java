package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Address {
  private String currency;
  private String address;
  @Json(name = "payment_id")
  private String paymentID;
  @Json(name = "public_key")
  private String publicKey;

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

  public String getPaymentID() {
    return paymentID;
  }

  public void setPaymentID(String paymentID) {
    this.paymentID = paymentID;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  @Override
  public String toString() {
    return "Address [address=" + address + ", currency=" + currency + ", paymentID=" + paymentID + ", publicKey="
        + publicKey + "]";
  }
}