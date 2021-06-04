package com.cryptomarket.sdk.models;

public class Report {
    private String id;
    private String clientOrderId;
    private String symbol;
    private String side;
    private String status;
    private String type;
    private String timeInForce;
    private String quantity;
    private String price;
    private String cumQuantity;
    private boolean postOnly;
    private String createdAt;
    private String updatedAt;
    private String stopPrice;
    private String expireTime;
    private String reportType;
    private String tradeId;
    private String tradeQuantity;
    private String tradePrice;
    private String tradeFee;
    private String originalRequestClientOrderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCumQuantity() {
        return cumQuantity;
    }

    public void setCumQuantity(String cumQuantity) {
        this.cumQuantity = cumQuantity;
    }

    public boolean getPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(String tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradeFee() {
        return tradeFee;
    }

    public void setTradeFee(String tradeFee) {
        this.tradeFee = tradeFee;
    }

    public String getOriginalRequestClientOrderId() {
        return originalRequestClientOrderId;
    }

    public void setOriginalRequestClientOrderId(String originalRequestClientOrderId) {
        this.originalRequestClientOrderId = originalRequestClientOrderId;
    }

    @Override
    public String toString() {
        return "Report [clientOrderId=" + clientOrderId + ", createdAt=" + createdAt + ", cumQuantity=" + cumQuantity
                + ", expireTime=" + expireTime + ", id=" + id + ", originalRequestClientOrderId="
                + originalRequestClientOrderId + ", postOnly=" + postOnly + ", price=" + price + ", quantity="
                + quantity + ", reportType=" + reportType + ", side=" + side + ", status=" + status + ", stopPrice="
                + stopPrice + ", symbol=" + symbol + ", timeInForce=" + timeInForce + ", tradeFee=" + tradeFee
                + ", tradeId=" + tradeId + ", tradePrice=" + tradePrice + ", tradeQuantity=" + tradeQuantity + ", type="
                + type + ", updatedAt=" + updatedAt + "]";
    }
}
