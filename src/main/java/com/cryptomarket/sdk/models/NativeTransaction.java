package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

public class NativeTransaction {
  @Json(name="id")
  private String ID;
  private Long index;
  private String currency;
  private String amount;
  private String fee;
  private String address;
  @Json(name = "payment_id")
  private String paymentID;
  private String hash;
  @Json(name = "public_comment")
  private String publicComment;
  @Json(name = "error_code")
  private String errorCode;
  private List<String> sender;

  public String getID() {
    return ID;
  }

  public void setID(String iD) {
    ID = iD;
  }

  public Long getIndex() {
    return index;
  }

  public void setIndex(Long index) {
    this.index = index;
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

  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
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

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getPublicComment() {
    return publicComment;
  }

  public void setPublicComment(String publicComment) {
    this.publicComment = publicComment;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public List<String> getSender() {
    return sender;
  }

  public void setSender(List<String> sender) {
    this.sender = sender;
  }

  @Override
  public String toString() {
    return "NativeTransaction [ID=" + ID + ", address=" + address + ", amount=" + amount + ", currency=" + currency
        + ", errorCode=" + errorCode + ", fee=" + fee + ", hash=" + hash + ", index=" + index + ", paymentID="
        + paymentID + ", publicComment=" + publicComment + ", sender=" + sender + "]";
  }

}
