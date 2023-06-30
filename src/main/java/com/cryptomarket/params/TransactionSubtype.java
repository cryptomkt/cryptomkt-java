package com.cryptomarket.params;

public enum TransactionSubtype {
  UNCLASSIFIED("UNCLASSIFIED"),
  BLOCKCHAIN("BLOCKCHAIN"),
  AFFILIATE("AFFILIATE"),
  OFFCHAIN("OFFCHAIN"),
  FIAT("FIAT"),
  SUB_ACCOUNT("SUB_ACCOUNT"),
  WALLET_TO_SPOT("WALLET_TO_SPOT"),
  SPOT_TO_WALLET("SPOT_TO_WALLET"),
  CHAIN_SWITCH_FROM("CHAIN_SWITCH_FROM"),
  CHAIN_SWITCH_TO("CHAIN_SWITCH_TO");

  private final String label;

  private TransactionSubtype(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
