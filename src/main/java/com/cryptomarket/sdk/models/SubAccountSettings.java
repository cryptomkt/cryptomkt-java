package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class SubAccountSettings {
  @Json(name = "sub_account_id")
  String subAccountID;
  @Json(name = "deposit_address_generation_enabled")
  Boolean depositAddressGenerationEnabled;
  @Json(name = "withdraw_enabled")
  Boolean withdrawEnabled;
  String description;
  @Json(name = "created_at")
  String createdAt;
  @Json(name = "updated_at")
  String updatedAt;

  public String getSubAccountID() {
    return subAccountID;
  }

  public void setSubAccountID(String subAccountID) {
    this.subAccountID = subAccountID;
  }

  public Boolean getDepositAddressGenerationEnabled() {
    return depositAddressGenerationEnabled;
  }

  public void setDepositAddressGenerationEnabled(Boolean depositAddressGenerationEnabled) {
    this.depositAddressGenerationEnabled = depositAddressGenerationEnabled;
  }

  public Boolean getWithdrawEnabled() {
    return withdrawEnabled;
  }

  public void setWithdrawEnabled(Boolean withdrawEnabled) {
    this.withdrawEnabled = withdrawEnabled;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  @Override
  public String toString() {
    return "ACLSetting [createdAt=" + createdAt + ", depositAddressGenerationEnabled=" + depositAddressGenerationEnabled
        + ", description=" + description + ", subAccountID=" + subAccountID + ", updatedAt=" + updatedAt
        + ", withdrawEnabled=" + withdrawEnabled + "]";
  }
}
