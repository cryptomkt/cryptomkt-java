package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

/**
 * Currency
 */
public class Currency {
  @Json(name = "full_name")
  private String fullName;
  private Boolean crypto;
  @Json(name = "payin_enabled")
  private Boolean payinEnabled;
  @Json(name = "payout_enabled")
  private Boolean payoutEnabled;
  @Json(name = "transfer_enabled")
  private Boolean transferEnabled;
  private String sign;
  @Json(name = "crypto_payment_id_name")
  private String cryptoPaymentIdName;
  @Json(name = "crypto_explorer")
  private String cryptoExplorer;
  @Json(name = "precision_transfer")
  private String precisionTransfer;
  @Json(name = "account_top_order")
  private Double accountTopOrder;
  @Json(name = "qr_prefix")
  private String qrPrefix;
  private Boolean delisted;
  private List<Network> networks;

  /**
   * Gets the full name of the currency
   *
   * @return
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Sets the full name of the currency
   *
   * @param fullName
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   *
   * @return True if is a crypto currency (and not a fiat currency)
   */
  public Boolean isCrypto() {
    return crypto;
  }

  /**
   * Sets the crypto flag
   *
   * @param crypto
   */
  public void setCrypto(Boolean crypto) {
    this.crypto = crypto;
  }

  /**
   *
   * @return True is pay in is enabled
   */
  public Boolean isPayinEnabled() {
    return payinEnabled;
  }

  /**
   * Sets the pay in enabled flag
   *
   * @param payinEnabled
   */
  public void setPayinEnabled(Boolean payinEnabled) {
    this.payinEnabled = payinEnabled;
  }

  /**
   *
   * @return True if is payout enabled
   */
  public Boolean isPayoutEnabled() {
    return payoutEnabled;
  }

  /**
   * Sets the payout enabled flag
   *
   * @param payoutEnabled
   */
  public void setPayoutEnabled(Boolean payoutEnabled) {
    this.payoutEnabled = payoutEnabled;
  }

  /**
   *
   * @return True if transfers are enabled
   */
  public Boolean isTransferEnabled() {
    return transferEnabled;
  }

  /**
   * Sets the transfer enabled flag
   *
   * @param transferEnabled
   */
  public void setTransferEnabled(Boolean transferEnabled) {
    this.transferEnabled = transferEnabled;
  }

  /**
   * Gets the currency sign
   *
   * @return
   */
  public String getSign() {
    return sign;
  }

  /**
   * Sets the currency sign
   *
   * @param sign
   */
  public void setSign(String sign) {
    this.sign = sign;
  }

  /**
   * Gets the crypto payment id name
   *
   * @return
   */
  public String getCryptoPaymentIdName() {
    return cryptoPaymentIdName;
  }

  /**
   * Sets the payment id name
   *
   * @param cryptoPaymentIdName
   */
  public void setCryptoPaymentIdName(String cryptoPaymentIdName) {
    this.cryptoPaymentIdName = cryptoPaymentIdName;
  }

  /**
   * Gets crypto explorer url
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
   * Gets the precision of transfers for the currency
   *
   * @return
   */
  public String getPrecisionTransfer() {
    return precisionTransfer;
  }

  /**
   * Sets the precision transfer
   *
   * @param precisionTransfer
   */
  public void setPrecisionTransfer(String precisionTransfer) {
    this.precisionTransfer = precisionTransfer;
  }

  /**
   * Gets the account top order. The absolute position of the currency in the
   * currency list
   *
   * @return
   */
  public Double getAccountTopOrder() {
    return accountTopOrder;
  }

  /**
   * Sets the account top order
   *
   * @param accountTopOrder
   */
  public void setAccountTopOrder(Double accountTopOrder) {
    this.accountTopOrder = accountTopOrder;
  }

  /**
   * Get The QR prefix used for indication of the currency in a deposit address
   *
   * @return
   */
  public String getQrPrefix() {
    return qrPrefix;
  }

  /**
   * Sets the qr prefix
   *
   * @param qrPrefix
   */
  public void setQrPrefix(String qrPrefix) {
    this.qrPrefix = qrPrefix;
  }

  /**
   *
   * @return True if the currency has been delisted
   */
  public Boolean isDelisted() {
    return delisted;
  }

  /**
   * Sets the delisted flag
   *
   * @param delisted
   */
  public void setDelisted(Boolean delisted) {
    this.delisted = delisted;
  }

  /**
   * Gets a list of network that may host operations on the currency
   *
   * @return
   */
  public List<Network> getNetworks() {
    return networks;
  }

  /**
   * Sets the network list
   *
   * @param networks
   */
  public void setNetworks(List<Network> networks) {
    this.networks = networks;
  }

  @Override
  public String toString() {
    return "Currency [fullName=" + fullName + ", crypto=" + crypto + ", payinEnabled=" + payinEnabled
        + ", payoutEnabled=" + payoutEnabled + ", transferEnabled=" + transferEnabled + ", sign=" + sign
        + ", cryptoPaymentIdName=" + cryptoPaymentIdName + ", cryptoExplorer=" + cryptoExplorer
        + ", precisionTransfer=" + precisionTransfer + ", accountTopOrder=" + accountTopOrder + ", qrPrefix="
        + qrPrefix + ", delisted=" + delisted + ", networks=" + networks + "]";
  }
}
