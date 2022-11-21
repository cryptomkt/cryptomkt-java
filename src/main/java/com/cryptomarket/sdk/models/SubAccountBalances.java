package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

public class SubAccountBalances {
  @Json(name = "wallet")
  List<Balance> WalletBalances;
  @Json(name = "spot")
  List<Balance> SpotBalances;
  public List<Balance> getWalletBalances() {
    return WalletBalances;
  }
  public void setWalletBalances(List<Balance> walletBalances) {
    WalletBalances = walletBalances;
  }
  public List<Balance> getSpotBalances() {
    return SpotBalances;
  }
  public void setSpotBalances(List<Balance> spotBalances) {
    SpotBalances = spotBalances;
  }
  @Override
  public String toString() {
    return "SubAccountBalances [SpotBalances=" + SpotBalances + ", WalletBalances=" + WalletBalances + "]";
  }
}
