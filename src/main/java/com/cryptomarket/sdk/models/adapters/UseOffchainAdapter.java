package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.UseOffchain;
import com.squareup.moshi.ToJson;

/**
 * A moshi adapter for UseOffchain
 */
public class UseOffchainAdapter {
  /**
   * Converts to json from UseOffchain
   * @param useOffchain
   * @return Json representation of UseOffchain
   */
  @ToJson
  String toJson(UseOffchain useOffchain) {
    return useOffchain.toString();
  }
}
