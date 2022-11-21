package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Transaction {
  @Json(name="id")
  private Long ID;
  private String status;
  private String type;
  private String subtype;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "updated_at")
  private String updatedAt;
  @Json(name="native")
  private NativeTransaction nativeTransaction;
  private MetaTransaction metaTransaction;
  private String primetrust;
  public Long getID() {
    return ID;
  }
  public void setID(Long iD) {
    ID = iD;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getSubtype() {
    return subtype;
  }
  public void setSubtype(String subtype) {
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
  public MetaTransaction getMetaTransaction() {
    return metaTransaction;
  }
  public void setMetaTransaction(MetaTransaction metaTransaction) {
    this.metaTransaction = metaTransaction;
  }
  public String getPrimetrust() {
    return primetrust;
  }
  public void setPrimetrust(String primetrust) {
    this.primetrust = primetrust;
  }
  @Override
  public String toString() {
    return "Transaction [ID=" + ID + ", createdAt=" + createdAt + ", metaTransaction=" + metaTransaction
        + ", nativeTransaction=" + nativeTransaction + ", primetrust=" + primetrust + ", status=" + status
        + ", subtype=" + subtype + ", type=" + type + ", updatedAt=" + updatedAt + "]";
  }

}