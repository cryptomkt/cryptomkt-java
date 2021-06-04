package com.cryptomarket.sdk.models;

public class Currency {
    private String id;
    private String fullName;
    private boolean crypto;
    private boolean payinEnabled;
    private boolean payinPaymentId;
    private int payinConfirmations;
    private boolean payoutEnabled;
    private boolean payoutIsPaymentId;
    private boolean transferEnabled;
    private boolean delisted;
    private String payoutFee;
    private int precisionPayout;
    private int precisionTransfer;
    private String payoutMinimalAmount;
    private String lowProcessingTime;
    private String highProcessingTime;
    private String avgProcessingTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isCrypto() {
        return crypto;
    }

    public void setCrypto(boolean crypto) {
        this.crypto = crypto;
    }

    public boolean isPayinEnabled() {
        return payinEnabled;
    }

    public void setPayinEnabled(boolean payinEnabled) {
        this.payinEnabled = payinEnabled;
    }

    public boolean isPayinPaymentId() {
        return payinPaymentId;
    }

    public void setPayinPaymentId(boolean payinPaymentId) {
        this.payinPaymentId = payinPaymentId;
    }

    public int getPayinConfirmations() {
        return payinConfirmations;
    }

    public void setPayinConfirmations(int payinConfirmations) {
        this.payinConfirmations = payinConfirmations;
    }

    public boolean isPayoutEnabled() {
        return payoutEnabled;
    }

    public void setPayoutEnabled(boolean payoutEnabled) {
        this.payoutEnabled = payoutEnabled;
    }

    public boolean isPayoutIsPaymentId() {
        return payoutIsPaymentId;
    }

    public void setPayoutIsPaymentId(boolean payoutIsPaymentId) {
        this.payoutIsPaymentId = payoutIsPaymentId;
    }

    public boolean isTransferEnabled() {
        return transferEnabled;
    }

    public void setTransferEnabled(boolean transferEnabled) {
        this.transferEnabled = transferEnabled;
    }

    public boolean isDelisted() {
        return delisted;
    }

    public void setDelisted(boolean delisted) {
        this.delisted = delisted;
    }

    public String getPayoutFee() {
        return payoutFee;
    }

    public void setPayoutFee(String payoutFee) {
        this.payoutFee = payoutFee;
    }

    public int getPrecisionPayout() {
        return precisionPayout;
    }

    public void setPrecisionPayout(int precisionPayout) {
        this.precisionPayout = precisionPayout;
    }

    public int getPrecisionTransfer() {
        return precisionTransfer;
    }

    public void setPrecisionTransfer(int precisionTransfer) {
        this.precisionTransfer = precisionTransfer;
    }

    public String getPayoutMinimalAmount() {
        return payoutMinimalAmount;
    }

    public void setPayoutMinimalAmount(String payoutMinimalAmount) {
        this.payoutMinimalAmount = payoutMinimalAmount;
    }

    public String getLowProcessingTime() {
        return lowProcessingTime;
    }

    public void setLowProcessingTime(String lowProcessingTime) {
        this.lowProcessingTime = lowProcessingTime;
    }

    public String getHighProcessingTime() {
        return highProcessingTime;
    }

    public void setHighProcessingTime(String highProcessingTime) {
        this.highProcessingTime = highProcessingTime;
    }

    public String getAvgProcessingTime() {
        return avgProcessingTime;
    }

    public void setAvgProcessingTime(String avgProcessingTime) {
        this.avgProcessingTime = avgProcessingTime;
    }

    @Override
    public String toString() {
        return "Currency [avgProcessingTime=" + avgProcessingTime + ", crypto=" + crypto + ", delisted=" + delisted
                + ", fullName=" + fullName + ", highProcessingTime=" + highProcessingTime + ", id=" + id
                + ", lowProcessingTime=" + lowProcessingTime + ", payinConfirmations=" + payinConfirmations
                + ", payinEnabled=" + payinEnabled + ", payinPaymentId=" + payinPaymentId + ", payoutEnabled="
                + payoutEnabled + ", payoutFee=" + payoutFee + ", payoutIsPaymentId=" + payoutIsPaymentId
                + ", payoutMinimalAmount=" + payoutMinimalAmount + ", precisionPayout=" + precisionPayout
                + ", precisionTransfer=" + precisionTransfer + ", transferEnabled=" + transferEnabled + "]";
    }

}