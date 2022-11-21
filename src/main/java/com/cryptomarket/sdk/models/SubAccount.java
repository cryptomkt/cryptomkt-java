package com.cryptomarket.sdk.models;

import com.cryptomarket.params.SubAccountStatus;
import com.squareup.moshi.Json;

public class SubAccount {
  @Json(name = "sub_account_id")
  String subAccountID;
  String email;
  SubAccountStatus status;

  public String getSubAccountID() {
    return subAccountID;
  }

  public void setSubAccountID(String subAccountID) {
    this.subAccountID = subAccountID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public SubAccountStatus getStatus() {
    return status;
  }

  public void setStatus(SubAccountStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "SubAccount [email=" + email + ", status=" + status + ", subAccountID=" + subAccountID + "]";
  }
}
