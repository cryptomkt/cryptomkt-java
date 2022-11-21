package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.SubAccountStatus;
import com.squareup.moshi.FromJson;

public class SubAccountStatusAdapter {
  @FromJson
  SubAccountStatus fromJson(String str) {
    return SubAccountStatus.valueOf(str);
  }
}
