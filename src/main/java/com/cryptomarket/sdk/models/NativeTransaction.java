package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

public class NativeTransaction {
  @Json(name = "tx_id")
  private String id;
  private Long index;
  private String currency;
  private String amount;
  private String fee;
  private String address;
  @Json(name = "payment_id")
  private String paymentId;
  private String hash;
  @Json(name = "offchain_id")
  private String offchainId;
  private String confirmations;
  @Json(name = "public_comment")
  private String publicComment;
  @Json(name = "error_code")
  private String errorCode;
  private List<String> sender;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getOffchainId() {
    return offchainId;
  }

  public void setOffchainId(String offchainId) {
    this.offchainId = offchainId;
  }

  public String getConfirmations() {
    return confirmations;
  }

  public void setConfirmations(String confirmations) {
    this.confirmations = confirmations;
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
    return "NativeTransaction [id=" + id + ", index=" + index + ", currency=" + currency + ", amount=" + amount
        + ", fee=" + fee + ", address=" + address + ", paymentId=" + paymentId + ", hash=" + hash + ", offchainId="
        + offchainId + ", confirmations=" + confirmations + ", publicComment=" + publicComment + ", errorCode="
        + errorCode + ", sender=" + sender + "]";
  }

}
