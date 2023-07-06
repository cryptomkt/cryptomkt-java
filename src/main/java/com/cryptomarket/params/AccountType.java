package com.cryptomarket.params;

/**
 * Account type
 */
public enum AccountType {
  /** Account type wallet */
  WALLET("wallet"),
  /** Account type spot */
  SPOT("spot");

  private final String label;

  private AccountType(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
