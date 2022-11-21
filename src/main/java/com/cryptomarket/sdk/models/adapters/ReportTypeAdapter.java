package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.ReportType;
import com.squareup.moshi.FromJson;

public class ReportTypeAdapter {
  @FromJson
  ReportType fromJson(String str) {
    return ReportType.valueOf(str.toUpperCase());
  }

}
