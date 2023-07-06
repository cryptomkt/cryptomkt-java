package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Subaccount settings
 */
public class SubAccountSettings {
  @Json(name = "sub_account_id")
  String subAccountId;
  @Json(name = "deposit_address_generation_enabled")
  Boolean depositAddressGenerationEnabled;
  @Json(name = "withdraw_enabled")
  Boolean withdrawEnabled;
  String description;
  @Json(name = "created_at")
  String createdAt;
  @Json(name = "updated_at")
  String updatedAt;

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
   *
   * @return True if the deposit address generation is enabled
   */
  public Boolean isDepositAddressGenerationEnabled() {
    return depositAddressGenerationEnabled;
  }

  /**
   * Sets the deposit address generation enabled flag
   *
   * @param depositAddressGenerationEnabled
   */
  public void setDepositAddressGenerationEnabled(Boolean depositAddressGenerationEnabled) {
    this.depositAddressGenerationEnabled = depositAddressGenerationEnabled;
  }

  /**
   *
   * @return True if withdraw is enabled
   */
  public Boolean isWithdrawEnabled() {
    return withdrawEnabled;
  }

  /**
   * Sets the withdraw enabled flag
   *
   * @param withdrawEnabled
   */
  public void setWithdrawEnabled(Boolean withdrawEnabled) {
    this.withdrawEnabled = withdrawEnabled;
  }

  /**
   * Gets the description
   *
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description
   *
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the created datetime of the settings
   *
   * @return
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the created datetime
   *
   * @param createdAt
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the updated datetime of the settings
   *
   * @return
   */
  public String getUpdatedAt() {
    return updatedAt;
  }

  /**
   * Sets the updated datetime
   *
   * @param updatedAt
   */
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "ACLSetting [createdAt=" + createdAt + ", depositAddressGenerationEnabled=" + depositAddressGenerationEnabled
        + ", description=" + description + ", subAccountId=" + subAccountId + ", updatedAt=" + updatedAt
        + ", withdrawEnabled=" + withdrawEnabled + "]";
  }
}
