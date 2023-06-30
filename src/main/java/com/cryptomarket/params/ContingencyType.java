package com.cryptomarket.params;

import com.squareup.moshi.Json;

public enum ContingencyType {
  @Json(name = "allOrNone")
  ALL_OR_NONE("allOrNone"),
  @Json(name = "oneCancelOther")
  ONE_CANCEL_OTHER("oneCancelOther"),
  @Json(name = "oneTriggerOther")
  ONE_TRIGGER_OTHER("oneTriggerOther"),
  @Json(name = "oneTriggerOneCancelOther")
  ONE_TRIGGER_ONE_CANCEL_OTHER("oneTriggerOneCancelOther");

  private final String label;

  private ContingencyType(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
