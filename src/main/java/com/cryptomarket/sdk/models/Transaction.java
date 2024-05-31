package com.cryptomarket.sdk.models;

import com.cryptomarket.params.TransactionStatus;
import com.cryptomarket.params.TransactionSubtype;
import com.cryptomarket.params.TransactionType;
import com.squareup.moshi.Json;

/**
 * Transaction
 */
public class Transaction {
  private Long id;
  private TransactionStatus status;
  private TransactionType type;
  private TransactionSubtype subtype;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "updated_at")
  private String updatedAt;
  @Json(name = "last_activity_at")
  private String lastActivityAt;
  @Json(name = "native")
  private NativeTransaction nativeTransaction;
  @Json(name = "network_code")
  private String networkCode;
  @Json(name = "commit_risk")
  private CommitRisk commitRisk;

  /**
   * Gets the transaction id
   *
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the transaction id
   *
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the transaction status
   *
   * @return
   */
  public TransactionStatus getStatus() {
    return status;
  }

  /**
   * Sets the transaction status
   *
   * @param status
   */
  public void setStatus(TransactionStatus status) {
    this.status = status;
  }

  /**
   * Gets the transaction type
   *
   * @return
   */
  public TransactionType getType() {
    return type;
  }

  /**
   * Sets the transaction type
   *
   * @param type
   */
  public void setType(TransactionType type) {
    this.type = type;
  }

  /**
   * Gets the transaction subtype
   *
   * @return
   */
  public TransactionSubtype getSubtype() {
    return subtype;
  }

  /**
   * Sets the transaction subtype
   *
   * @param subtype
   */
  public void setSubtype(TransactionSubtype subtype) {
    this.subtype = subtype;
  }

  /**
   * Gets the transaction creation datetime
   *
   * @return
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the transaction created at
   *
   * @param createdAt
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the transaction updated datetime
   *
   * @return
   */
  public String getUpdatedAt() {
    return updatedAt;
  }

  /**
   * Sets the transaction updated at
   *
   * @param updatedAt
   */
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   * Gets the last activity of the transaction
   *
   * @return
   */
  public String getLastActivityAt() {
    return lastActivityAt;
  }

  /**
   * Sets the last activity of the transaction
   *
   * @param lastActivityAt
   */
  public void setLastActivityAt(String lastActivityAt) {
    this.lastActivityAt = lastActivityAt;
  }

  /**
   * Gets the native transaction
   *
   * @return
   */
  public NativeTransaction getNativeTransaction() {
    return nativeTransaction;
  }

  /**
   * Sets the native transaction
   *
   * @param nativeTransaction
   */
  public void setNativeTransaction(NativeTransaction nativeTransaction) {
    this.nativeTransaction = nativeTransaction;
  }

  /**
   * Gets the network code
   *
   * @return
   */
  public String getNetworkCode() {
    return networkCode;
  }

  /**
   * Sets the network code
   *
   * @param networkCode
   */
  public void setNetworkCode(String networkCode) {
    this.networkCode = networkCode;
  }

  /**
   * Gets the commit risk of the transaction
   * @return
   */
  public CommitRisk getCommitRisk() {
    return commitRisk;
  }

  /**
   * Sets the commit rist of the transaction
   *
   * @param commitRisk
   */
  public void setCommitRisk(CommitRisk commitRisk) {
    this.commitRisk = commitRisk;
  }

  @Override
  public String toString() {
    return "Transaction [id=" + id + ", status=" + status + ", type=" + type + ", subtype=" + subtype + ", createdAt="
        + createdAt + ", updatedAt=" + updatedAt + ", lastActivityAt=" + lastActivityAt + ", nativeTransaction="
        + nativeTransaction + ", networkCode=" + networkCode + ", commitRisk=" + commitRisk + "]";
  }
}
