package com.cryptomarket.params;

/** Subscription mode */
public enum SubscriptionMode {
  /** updates */
  UPDATES("updates"),
  /** batches */
  BATCHES("batches");

  public final String label;

  private SubscriptionMode(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
