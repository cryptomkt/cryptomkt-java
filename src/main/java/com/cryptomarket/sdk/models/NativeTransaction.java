package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

/**
 * Native transaction
 */
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
  @Json(name = "network_code")
  private String networkCode;
  @Json(name = "error_code")
  private String errorCode;
  private List<String> sender;

  /**
   * Gets the transaction id
   *
   * @return An id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the transaction id
   *
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the transaction index. Internal index value that represents when the
   * entry was updated
   *
   * @return
   */
  public Long getIndex() {
    return index;
  }

  /**
   * Sets the transaction index
   *
   * @param index
   */
  public void setIndex(Long index) {
    this.index = index;
  }

  /**
   * Gets the transaction currency
   *
   * @return
   */
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the transaction amount
   *
   * @return
   */
  public String getAmount() {
    return amount;
  }

  /**
   * Sets the transaction amount
   *
   * @param amount
   */
  public void setAmount(String amount) {
    this.amount = amount;
  }

  /**
   * Gets the transaction fee
   *
   * @return
   */
  public String getFee() {
    return fee;
  }

  /**
   * Sets the transaction fee
   *
   * @param fee
   */
  public void setFee(String fee) {
    this.fee = fee;
  }

  /**
   * Gets the transaction address
   *
   * @return
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the transaction address
   *
   * @param address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets the transaction payment id
   *
   * @return
   */
  public String getPaymentId() {
    return paymentId;
  }

  /**
   * Sets the transaction payment id
   *
   * @param paymentId
   */
  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  /**
   * Gets the transaction hash
   *
   * @return
   */
  public String getHash() {
    return hash;
  }

  /**
   * Sets the transaction hash
   *
   * @param hash
   */
  public void setHash(String hash) {
    this.hash = hash;
  }

  /**
   * Gets the transaction offchain id
   *
   * @return
   */
  public String getOffchainId() {
    return offchainId;
  }

  /**
   * Sets the transaction offchain id
   *
   * @param offchainId
   */
  public void setOffchainId(String offchainId) {
    this.offchainId = offchainId;
  }

  /**
   * Gets the transaction confirmations
   *
   * @return
   */
  public String getConfirmations() {
    return confirmations;
  }

  /**
   * Sets the transaction confirmations
   *
   * @param confirmations
   */
  public void setConfirmations(String confirmations) {
    this.confirmations = confirmations;
  }

  /**
   * Gets the transaction public comment
   *
   * @return
   */
  public String getPublicComment() {
    return publicComment;
  }

  /**
   * Sets the transaction public comment
   *
   * @param publicComment
   */
  public void setPublicComment(String publicComment) {
    this.publicComment = publicComment;
  }

  /**
   * Gets the transaction error code
   *
   * @return
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * Sets the transaction error code
   *
   * @param errorCode
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Gets the transaction senders
   *
   * @return
   */
  public List<String> getSender() {
    return sender;
  }

  /**
   * Sets the transaction senders
   *
   * @param sender
   */
  public void setSender(List<String> sender) {
    this.sender = sender;
  }

  @Override
  public String toString() {
    return "NativeTransaction [id=" + id + ", index=" + index + ", currency=" + currency + ", amount=" + amount
        + ", fee=" + fee + ", address=" + address + ", paymentId=" + paymentId + ", hash=" + hash + ", offchainId="
        + offchainId + ", confirmations=" + confirmations + ", publicComment=" + publicComment + ", networkCode="
        + networkCode + ", errorCode=" + errorCode + ", sender=" + sender + "]";
  }

}
