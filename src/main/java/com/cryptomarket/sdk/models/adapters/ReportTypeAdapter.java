package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.ReportType;
import com.squareup.moshi.FromJson;

/**
 * A moshi adapter for ReportType
 */
public class ReportTypeAdapter {
  /**
   * Converts from json to ReportType
   * @param str Json representation or report type
   * @return A ReportType
   */
  @FromJson
  ReportType fromJson(String str) {
    return ReportType.valueOf(str.toUpperCase());
  }

}
