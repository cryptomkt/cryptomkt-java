package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class MetaTransaction {
  @Json(name = "id")
  private long ID;
  @Json(name = "fiat_to_crypto")
  private String fiatToCrypto;
  @Json(name = "provider_name")
  private String providerName;
  @Json(name = "order_type")
  private String orderType;
  @Json(name = "source_currency")
  private String sourceCurrency;
  @Json(name = "target_currency")
  private String targetCurrency;
  @Json(name = "wallet_address")
  private String walletAddress;
  @Json(name = "tx_hash")
  private String transactionHash;
  @Json(name = "target_amount")
  private String targetAmount;
  @Json(name = "source_amount")
  private String sourceAmount;
  private String status;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "updated_at")
  private String updatedAt;
  @Json(name = "deleted_at")
  private String deletedAt;
  @Json(name = "payment_method_type")
  private String paymentMethodType;

  public long getID() {
    return ID;
  }

  public void setID(long iD) {
    ID = iD;
  }

  public String getFiatToCrypto() {
    return fiatToCrypto;
  }

  public void setFiatToCrypto(String fiatToCrypto) {
    this.fiatToCrypto = fiatToCrypto;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getSourceCurrency() {
    return sourceCurrency;
  }

  public void setSourceCurrency(String sourceCurrency) {
    this.sourceCurrency = sourceCurrency;
  }

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  public String getWalletAddress() {
    return walletAddress;
  }

  public void setWalletAddress(String walletAddress) {
    this.walletAddress = walletAddress;
  }

  public String getTransactionHash() {
    return transactionHash;
  }

  public void setTransactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
  }

  public String getTargetAmount() {
    return targetAmount;
  }

  public void setTargetAmount(String targetAmount) {
    this.targetAmount = targetAmount;
  }

  public String getSourceAmount() {
    return sourceAmount;
  }

  public void setSourceAmount(String sourceAmount) {
    this.sourceAmount = sourceAmount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }

  public String getPaymentMethodType() {
    return paymentMethodType;
  }

  public void setPaymentMethodType(String paymentMethodType) {
    this.paymentMethodType = paymentMethodType;
  }

  @Override
  public String toString() {
    return "MetaTransaction [ID=" + ID + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + ", fiatToCrypto="
        + fiatToCrypto + ", orderType=" + orderType + ", paymentMethodType=" + paymentMethodType + ", providerName="
        + providerName + ", sourceAmount=" + sourceAmount + ", sourceCurrency=" + sourceCurrency + ", status=" + status
        + ", targetAmount=" + targetAmount + ", targetCurrency=" + targetCurrency + ", transactionHash="
        + transactionHash + ", updatedAt=" + updatedAt + ", walletAddress=" + walletAddress + "]";
  }

}
