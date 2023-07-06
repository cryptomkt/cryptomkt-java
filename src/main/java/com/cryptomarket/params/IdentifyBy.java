package com.cryptomarket.params;

/** Identify user by */
public enum IdentifyBy {
  /** Identify by email */
  EMAIL("email"),
  /** Identify by username */
  USERNAME("username");

  private final String label;

  private IdentifyBy(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
