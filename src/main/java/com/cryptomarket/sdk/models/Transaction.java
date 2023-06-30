package com.cryptomarket.sdk.models;

import com.cryptomarket.params.TransactionStatus;
import com.cryptomarket.params.TransactionSubtype;
import com.cryptomarket.params.TransactionType;
import com.squareup.moshi.Json;

public class Transaction {
  private Long id;
  private TransactionStatus status;
  private TransactionType type;
  private TransactionSubtype subtype;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "updated_at")
  private String updatedAt;
  @Json(name = "native")
  private NativeTransaction nativeTransaction;
  @Json(name = "network_code")
  private String networkCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public void setStatus(TransactionStatus status) {
    this.status = status;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public TransactionSubtype getSubtype() {
    return subtype;
  }

  public void setSubtype(TransactionSubtype subtype) {
    this.subtype = subtype;
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

  public NativeTransaction getNativeTransaction() {
    return nativeTransaction;
  }

  public void setNativeTransaction(NativeTransaction nativeTransaction) {
    this.nativeTransaction = nativeTransaction;
  }

  public String getNetworkCode() {
    return networkCode;
  }

  public void setNetworkCode(String networkCode) {
    this.networkCode = networkCode;
  }

  @Override
  public String toString() {
    return "Transaction [id=" + id + ", status=" + status + ", type=" + type + ", subtype=" + subtype + ", createdAt="
        + createdAt + ", updatedAt=" + updatedAt + ", nativeTransaction=" + nativeTransaction + ", networkCode="
        + networkCode + "]";
  }
}