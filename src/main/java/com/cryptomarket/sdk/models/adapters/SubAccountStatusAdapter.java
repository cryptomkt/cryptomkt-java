package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.SubAccountStatus;
import com.squareup.moshi.FromJson;

/**
 * A moshi adapter for SubAccountStatus
 */
public class SubAccountStatusAdapter {
  /**
   * Converts from json to SubAccountStatus
   *
   * @param str Json representation of SubAccountStatus
   * @return A SubAccountStatus
   */
  @FromJson
  SubAccountStatus fromJson(String str) {
    return SubAccountStatus.valueOf(str);
  }
}
