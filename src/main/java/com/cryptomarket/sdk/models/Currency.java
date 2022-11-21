package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

public class Currency {
    @Json(name = "full_name")
    private String fullName;
    @Json(name = "payin_enabled")
    private boolean payinEnabled;
    @Json(name = "payout_enabled")
    private boolean payoutEnabled;
    @Json(name = "transfer_enabled")
    private boolean transferEnabled;
    @Json(name = "precision_transfer")
    private String precisionTransfer;
    private List<Network> networks;
    
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean isPayinEnabled() {
        return payinEnabled;
    }
    public void setPayinEnabled(boolean payinEnabled) {
        this.payinEnabled = payinEnabled;
    }
    public boolean isPayoutEnabled() {
        return payoutEnabled;
    }
    public void setPayoutEnabled(boolean payoutEnabled) {
        this.payoutEnabled = payoutEnabled;
    }
    public boolean isTransferEnabled() {
        return transferEnabled;
    }
    public void setTransferEnabled(boolean transferEnabled) {
        this.transferEnabled = transferEnabled;
    }
    public String getPrecisionTransfer() {
        return precisionTransfer;
    }
    public void setPrecisionTransfer(String precisionTransfer) {
        this.precisionTransfer = precisionTransfer;
    }
    public List<Network> getNetworks() {
        return networks;
    }
    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }
    @Override
    public String toString() {
        return "Currency [fullName=" + fullName + ", networks=" + networks + ", payinEnabled=" + payinEnabled
                + ", payoutEnabled=" + payoutEnabled + ", precisionTransfer=" + precisionTransfer + ", transferEnabled="
                + transferEnabled + "]";
    }
}