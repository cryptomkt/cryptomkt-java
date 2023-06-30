package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean isCrypto() {
        return crypto;
    }

    public void setCrypto(Boolean crypto) {
        this.crypto = crypto;
    }

    public Boolean isPayinEnabled() {
        return payinEnabled;
    }

    public void setPayinEnabled(Boolean payinEnabled) {
        this.payinEnabled = payinEnabled;
    }

    public Boolean isPayoutEnabled() {
        return payoutEnabled;
    }

    public void setPayoutEnabled(Boolean payoutEnabled) {
        this.payoutEnabled = payoutEnabled;
    }

    public Boolean isTransferEnabled() {
        return transferEnabled;
    }

    public void setTransferEnabled(Boolean transferEnabled) {
        this.transferEnabled = transferEnabled;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCryptoPaymentIdName() {
        return cryptoPaymentIdName;
    }

    public void setCryptoPaymentIdName(String cryptoPaymentIdName) {
        this.cryptoPaymentIdName = cryptoPaymentIdName;
    }

    public String getCryptoExplorer() {
        return cryptoExplorer;
    }

    public void setCryptoExplorer(String cryptoExplorer) {
        this.cryptoExplorer = cryptoExplorer;
    }

    public String getPrecisionTransfer() {
        return precisionTransfer;
    }

    public void setPrecisionTransfer(String precisionTransfer) {
        this.precisionTransfer = precisionTransfer;
    }

    public Double getAccountTopOrder() {
        return accountTopOrder;
    }

    public void setAccountTopOrder(Double accountTopOrder) {
        this.accountTopOrder = accountTopOrder;
    }

    public String getQrPrefix() {
        return qrPrefix;
    }

    public void setQrPrefix(String qrPrefix) {
        this.qrPrefix = qrPrefix;
    }

    public Boolean isDelisted() {
        return delisted;
    }

    public void setDelisted(Boolean delisted) {
        this.delisted = delisted;
    }

    public List<Network> getNetworks() {
        return networks;
    }

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