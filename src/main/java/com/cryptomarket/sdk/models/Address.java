package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Wallet address
 */
public class Address {
  /** currency of the address */
  private String currency;
  /** the address */
  private String address;
  /** aditional identifier required for some currencies */
  @Json(name = "payment_id")
  private String paymentId;
  /** aditional identifier required for some currencies */
  @Json(name = "public_key")
  private String publicKey;
  /** network code */
  @Json(name = "network_code")
  private String networkCode;

  /**
   * Gets the currency of the address
   *
   * @return A currency code
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the currency of an address
   *
   * @param currency A currency
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the address of the address
   *
   * @return An address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the address
   *
   * @param address An address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets the payment id. Not all currencies use payment id
   *
   * @return the payment id
   */
  public String getPaymentId() {
    return paymentId;
  }

  /**
   * Sets the payment id
   *
   * @param paymentId A payment id
   */
  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  /**
   * Gets the public key of an address. Not all currencies use public keys
   *
   * @return the public key
   */
  public String getPublicKey() {
    return publicKey;
  }

  /**
   * Sets the public key
   *
   * @param publicKey The public key
   */
  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  /**
   * Gets the network code
   *
   * @return network code
   */
  public String getNetworkCode() {
    return networkCode;
  }

  /**
   * Sets the network code
   *
   * @param networkCode The network code
   */
  public void setNetworkCode(String networkCode) {
    this.networkCode = networkCode;
  }

  @Override
  public String toString() {
    return "Address [currency=" + currency + ", address=" + address + ", paymentId=" + paymentId + ", publicKey="
        + publicKey + ", networkCode=" + networkCode + "]";
  }
}
