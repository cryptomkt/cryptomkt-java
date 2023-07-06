package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

/**
 * subaccount balances
 */
public class SubAccountBalances {
  @Json(name = "wallet")
  List<Balance> WalletBalances;
  @Json(name = "spot")
  List<Balance> SpotBalances;

  /**
   * Gets the subaccount wallet balances
   *
   * @return
   */
  public List<Balance> getWalletBalances() {
    return WalletBalances;
  }

  /**
   * Sets the subaccount wallet balances
   *
   * @param walletBalances
   */
  public void setWalletBalances(List<Balance> walletBalances) {
    WalletBalances = walletBalances;
  }

  /**
   * Gets the subaccount spot balance
   *
   * @return
   */
  public List<Balance> getSpotBalances() {
    return SpotBalances;
  }

  /**
   * Sets the subaccount spot balance
   *
   * @param spotBalances
   */
  public void setSpotBalances(List<Balance> spotBalances) {
    SpotBalances = spotBalances;
  }

  @Override
  public String toString() {
    return "SubAccountBalances [SpotBalances=" + SpotBalances + ", WalletBalances=" + WalletBalances + "]";
  }
}
