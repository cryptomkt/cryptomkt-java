package com.cryptomarket.sdk.models;

/**
 * Whitelisted address
 */
public class WhitelistedAddress {
  /** Name of the whitelist item */
  private String name;

  /** Currency code */
  private String currency;

  /** Code of the currency of the hosting network */
  private String network;

  /** Address for deposits */
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getNetwork() {
    return network;
  }

  public void setNetwork(String network) {
    this.network = network;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "WhitelistedAddress [name=" + name + ", currency=" + currency + ", network=" + network + ", address="
        + address + "]";
  }
}
