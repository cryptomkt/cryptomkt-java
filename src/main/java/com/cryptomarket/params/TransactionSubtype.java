package com.cryptomarket.params;

/** transaction subtype */
public enum TransactionSubtype {
  /** unclassified */
  UNCLASSIFIED("UNCLASSIFIED"),
  /** blockchain */
  BLOCKCHAIN("BLOCKCHAIN"),
  /** affiliate */
  AFFILIATE("AFFILIATE"),
  /** offchain */
  OFFCHAIN("OFFCHAIN"),
  /** fiat */
  FIAT("FIAT"),
  /** sub account */
  SUB_ACCOUNT("SUB_ACCOUNT"),
  /** wallet to spot */
  WALLET_TO_SPOT("WALLET_TO_SPOT"),
  /** spot to wallet */
  SPOT_TO_WALLET("SPOT_TO_WALLET"),
  /** chain switch from */
  CHAIN_SWITCH_FROM("CHAIN_SWITCH_FROM"),
  /** chain switch to */
  CHAIN_SWITCH_TO("CHAIN_SWITCH_TO"),
  /** airdrop */
  AIRDROP("AIRDROP");

  private final String label;

  private TransactionSubtype(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
