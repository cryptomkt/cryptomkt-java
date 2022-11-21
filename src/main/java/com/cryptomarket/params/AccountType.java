package com.cryptomarket.params;

public enum AccountType {
  WALLET("wallet"),
  SPOT("spot");

  private final String label;

  private AccountType(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
