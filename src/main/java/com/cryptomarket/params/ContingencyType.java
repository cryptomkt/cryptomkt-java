package com.cryptomarket.params;

import com.squareup.moshi.Json;

/** Contingency type */
public enum ContingencyType {
  /** Contingency type all or none */
  @Json(name = "allOrNone")
  ALL_OR_NONE("allOrNone"),
  /** Contingency type one cancel other */
  @Json(name = "oneCancelOther")
  ONE_CANCEL_OTHER("oneCancelOther"),
  /** Contingency type one trigger other */
  @Json(name = "oneTriggerOther")
  ONE_TRIGGER_OTHER("oneTriggerOther"),
  /** Contingency type one trigger one cancel other */
  @Json(name = "oneTriggerOneCancelOther")
  ONE_TRIGGER_ONE_CANCEL_OTHER("oneTriggerOneCancelOther");

  private final String label;

  private ContingencyType(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
