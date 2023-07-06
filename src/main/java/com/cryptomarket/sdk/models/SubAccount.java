package com.cryptomarket.sdk.models;

import com.cryptomarket.params.SubAccountStatus;
import com.squareup.moshi.Json;

/**
 * SubAccount
 */
public class SubAccount {
  @Json(name = "sub_account_id")
  String subAccountId;
  String email;
  SubAccountStatus status;

  /**
   * Gets the subaccount id
   *
   * @return
   */
  public String getSubAccountId() {
    return subAccountId;
  }

  /**
   * Sets the subaccount id
   *
   * @param subAccountId
   */
  public void setSubAccountId(String subAccountId) {
    this.subAccountId = subAccountId;
  }

  /**
   * Gets the subaccount email
   *
   * @return
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the subaccount email
   *
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the subaccount status
   *
   * @return
   */
  public SubAccountStatus getStatus() {
    return status;
  }

  /**
   * Sets the subaccount status
   *
   * @param status
   */
  public void setStatus(SubAccountStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "SubAccount [email=" + email + ", status=" + status + ", subAccountId=" + subAccountId + "]";
  }
}
