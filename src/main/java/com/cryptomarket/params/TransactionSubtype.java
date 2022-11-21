package com.cryptomarket.params;

public enum TransactionSubtype {
  UNCLASSIFIED("UNCLASSIFIED"),
  BLOCKCHAIN("BLOCKCHAIN"),
  AIRDROP("AIRDROP"),
  AFFILIATE("AFFILIATE"),
  STAKING("STAKING"),
  BUY_CRYPTO("BUY_CRYPTO"),
  OFFCHAIN("OFFCHAIN"),
  FIAT("FIAT"),
  SUB_ACCOUNT("SUB_ACCOUNT"),
  WALLET_TO_SPOT("WALLET_TO_SPOT"),
  SPOT_TO_WALLET("SPOT_TO_WALLET"),
  WALLET_TO_DERIVATIVES("WALLET_TO_DERIVATIVES"),
  DERIVATIVES_TO_WALLET("DERIVATIVES_TO_WALLET"),
  CHAIN_SWITCH_FROM("CHAIN_SWITCH_FROM"),
  CHAIN_SWITCH_TO("CHAIN_SWITCH_TO"),
  INSTANT_EXCHANGE("INSTANT_EXCHANGE");

  private final String label;

  private TransactionSubtype(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
