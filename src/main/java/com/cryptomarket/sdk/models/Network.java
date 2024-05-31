package com.cryptomarket.sdk.models;

import java.util.Map;

import com.squareup.moshi.Json;

/**
 * Currency Network
 */
public class Network {
  private String code;
  private String network;
  private String protocol;
  @Json(name = "default")
  private Boolean defaultNetwork;
  @Json(name = "payin_enabled")
  private Boolean payinEnabled;
  @Json(name = "payout_enabled")
  private Boolean payoutEnabled;
  @Json(name = "parecision_payout")
  private String precisionPayout;
  @Json(name = "payout_fee")
  private String payoutFee;
  @Json(name = "payout_is_payment_id")
  private Boolean payoutIsPaymentId;
  @Json(name = "payin_payment_id")
  private Boolean payinPaymentId;
  @Json(name = "payin_confirmations")
  private int payinConfirmations;
  @Json(name = "address_regex")
  private String addressRegex;
  @Json(name = "payment_id_regex")
  private String paymentIdRegex;
  @Json(name = "low_processing_time")
  private String lowProcessingTime;
  @Json(name = "high_processing_time")
  private String highProcessingTime;
  @Json(name = "avg_processing_time")
  private String avgProcessingTime;
  @Json(name = "crypto_payment_id_name")
  private String cryptoPaymentIdName;
  @Json(name = "crypto_explorer")
  private String cryptoExplorer;
  @Json(name = "network_name")
  private String networkName;
  @Json(name = "is_ens_available")
  private Boolean ensAvailable;
  @Json(name = "contract_address")
  private String contractAddress;
  @Json(name = "is_multichain")
  private Boolean multichain;
  @Json(name = "asset_id")
  private Map<String, String> assetId;

  /**
   * Gets the network code
   *
   * @return
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the network code
   *
   * @param code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Gets the network name
   *
   * @return
   */
  public String getNetwork() {
    return network;
  }

  /**
   * Sets the network name
   *
   * @param network
   */
  public void setNetwork(String network) {
    this.network = network;
  }

  /**
   *
   * @return True if the network supports ENS (Etherium Name Service)
   */
  public Boolean isEnsAvailable() {
    return ensAvailable;
  }

  /**
   * Sets the ens available flag
   *
   * @param isEnsAvailable
   */
  public void setEnsAvailable(Boolean isEnsAvailable) {
    this.ensAvailable = isEnsAvailable;
  }

  /**
   * Gets the network protocol
   *
   * @return
   */
  public String getProtocol() {
    return protocol;
  }

  /**
   * Sets the network protocol
   *
   * @param protocol
   */
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  /**
   *
   * @return True if is default network for the currency
   */
  public Boolean isDefaultNetwork() {
    return defaultNetwork;
  }

  /**
   * Sets the default network flag
   *
   * @param isDefaultNetwork
   */
  public void setDefaultNetwork(Boolean isDefaultNetwork) {
    this.defaultNetwork = isDefaultNetwork;
  }

  /**
   *
   * @return True if pay in is enable
   */
  public Boolean isPayinEnabled() {
    return payinEnabled;
  }

  /**
   * Sets the pay in enable flag
   *
   * @param payinEnabled
   */
  public void setPayinEnabled(Boolean payinEnabled) {
    this.payinEnabled = payinEnabled;
  }

  /**
   *
   * @return True if pay out is enabled
   */
  public Boolean isPayoutEnabled() {
    return payoutEnabled;
  }

  /**
   * Sets the pay out enabled flag
   *
   * @param payoutEnabled
   */
  public void setPayoutEnabled(Boolean payoutEnabled) {
    this.payoutEnabled = payoutEnabled;
  }

  /**
   * Gets the precision payout
   *
   * @return
   */
  public String getPrecisionPayout() {
    return precisionPayout;
  }

  /**
   * Sets the precision payout
   *
   * @param precisionPayout
   */
  public void setPrecisionPayout(String precisionPayout) {
    this.precisionPayout = precisionPayout;
  }

  /**
   * Gets the payout fee
   *
   * @return
   */
  public String getPayoutFee() {
    return payoutFee;
  }

  /**
   * Sets the payout fee
   *
   * @param payoutFee
   */
  public void setPayoutFee(String payoutFee) {
    this.payoutFee = payoutFee;
  }

  /**
   *
   * @return True if needs a payment id to withdraw
   */
  public Boolean isPayoutPaymentId() {
    return payoutIsPaymentId;
  }

  /**
   * Sets the payout is payment id flag
   *
   * @param payoutPaymentId
   */
  public void setPayoutPaymentId(Boolean payoutPaymentId) {
    this.payoutIsPaymentId = payoutPaymentId;
  }

  /**
   *
   * @return True if payment id is required for deposits
   */
  public Boolean isPayinPaymentId() {
    return payinPaymentId;
  }

  /**
   * Sets the payin is payment id
   *
   * @param payinPaymentId
   */
  public void setPayinPaymentId(Boolean payinPaymentId) {
    this.payinPaymentId = payinPaymentId;
  }

  /**
   * Gets the payin confirmations
   *
   * @return
   */
  public int getPayinConfirmations() {
    return payinConfirmations;
  }

  /**
   * Sets the payin confirmatinos
   *
   * @param payinConfirmations
   */
  public void setPayinConfirmations(int payinConfirmations) {
    this.payinConfirmations = payinConfirmations;
  }

  /**
   * Gets the address regex
   *
   * @return
   */
  public String getAddressRegex() {
    return addressRegex;
  }

  /**
   * Sets the address regex
   *
   * @param addressRegex
   */
  public void setAddressRegex(String addressRegex) {
    this.addressRegex = addressRegex;
  }

  /**
   * Gets the payment id regex
   *
   * @return
   */
  public String getPaymentIdRegex() {
    return paymentIdRegex;
  }

  /**
   * Sets the payment id regex
   *
   * @param paymentIdRegex
   */
  public void setPaymentIdRegex(String paymentIdRegex) {
    this.paymentIdRegex = paymentIdRegex;
  }

  /**
   * Gets the low processing time for a withdrawal in seconds
   *
   * @return
   */
  public String getLowProcessingTime() {
    return lowProcessingTime;
  }

  /**
   * Sets the low processing time
   *
   * @param lowProcessingTime
   */
  public void setLowProcessingTime(String lowProcessingTime) {
    this.lowProcessingTime = lowProcessingTime;
  }

  /**
   * Gets the high processing for a withdrawal in seconds
   *
   * @return
   */
  public String getHighProcessingTime() {
    return highProcessingTime;
  }

  /**
   * Sets the high processing time
   *
   * @param highProcessingTime
   */
  public void setHighProcessingTime(String highProcessingTime) {
    this.highProcessingTime = highProcessingTime;
  }

  /**
   * Gets the average processing time of a withdrawal in seconds
   *
   * @return
   */
  public String getAvgProcessingTime() {
    return avgProcessingTime;
  }

  /**
   * Sets the average processing time
   *
   * @param avgProcessingTime
   */
  public void setAvgProcessingTime(String avgProcessingTime) {
    this.avgProcessingTime = avgProcessingTime;
  }

  /**
   * Gets the cryptopayment id name
   *
   * @return
   */
  public String getCryptoPaymentIdName() {
    return cryptoPaymentIdName;
  }

  /**
   * Sets the crypto payment id name
   *
   * @param cryptoPaymentIdName
   */
  public void setCryptoPaymentIdName(String cryptoPaymentIdName) {
    this.cryptoPaymentIdName = cryptoPaymentIdName;
  }

  /**
   * Gets the crypto explorer url
   *
   * @return An url
   */
  public String getCryptoExplorer() {
    return cryptoExplorer;
  }

  /**
   * Sets the crypto explorer url
   *
   * @param cryptoExplorer
   */
  public void setCryptoExplorer(String cryptoExplorer) {
    this.cryptoExplorer = cryptoExplorer;
  }

  /**
   * Gets the full network name of the network
   *
   * @return
   */
  public String getNetworkName() {
    return networkName;
  }

  /**
   * Sets the network name
   *
   * @param networkName
   */
  public void setNetworkName(String networkName) {
    this.networkName = networkName;
  }

  /**
   * Gets the contract address of the network
   *
   * @return
   */
  public String getContractAddress() {
    return contractAddress;
  }

  /**
   * Sets the contract address of the network
   *
   * @param contractAddress
   */
  public void setContractAddress(String contractAddress) {
    this.contractAddress = contractAddress;
  }

  /**
   * Get a flag indicating if the multichain is active for the network
   *
   * @return
   */
  public Boolean getMultichain() {
    return multichain;
  }

  /**
   * Sets the multichain flag of the network
   *
   * @param multichain
   */
  public void setMultichain(Boolean multichain) {
    this.multichain = multichain;
  }

  /**
   * Gets arbitrary data particular of the network
   * @return
   */
  public Map<String, String> getAssetId() {
    return assetId;
  }

  /**
   * Sets the asset id, extra data of the network
   * @param assetId
   */
  public void setAssetId(Map<String, String> assetId) {
    this.assetId = assetId;
  }

  @Override
  public String toString() {
    return "Network [code=" + code + ", network=" + network + ", ensAvailable=" + ensAvailable + ", protocol="
        + protocol + ", defaultNetwork=" + defaultNetwork + ", payinEnabled=" + payinEnabled + ", payoutEnabled="
        + payoutEnabled + ", precisionPayout=" + precisionPayout + ", payoutFee=" + payoutFee + ", payoutIsPaymentId="
        + payoutIsPaymentId + ", payinPaymentId=" + payinPaymentId + ", payinConfirmations=" + payinConfirmations
        + ", addressRegex=" + addressRegex + ", paymentIdRegex=" + paymentIdRegex + ", lowProcessingTime="
        + lowProcessingTime + ", highProcessingTime=" + highProcessingTime + ", avgProcessingTime=" + avgProcessingTime
        + ", cryptoPaymentIdName=" + cryptoPaymentIdName + ", cryptoExplorer=" + cryptoExplorer + "]";
  }

}
