package com.cryptomarket.params;

public enum ContingencyType {
  ALL_OR_NONE("allOrNone"),
  ONE_CANCEL_OTHER("oneCancelOther"),
  ONE_TRIGGER_ONE_CANCEL_OTHER("oneTriggerOneCancelOther");

  private final String label;

  private ContingencyType(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
