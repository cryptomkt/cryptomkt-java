package com.cryptomarket.sdk.models;

public class Commission {
    private String takeLiquidityRate;
    private String provideLiquidityRate;

    public String getTakeLiquidityRate() {
        return takeLiquidityRate;
    }

    public void setTakeLiquidityRate(String takeLiquidityRate) {
        this.takeLiquidityRate = takeLiquidityRate;
    }

    public String getProvideLiquidityRate() {
        return provideLiquidityRate;
    }

    public void setProvideLiquidityRate(String provideLiquidityRate) {
        this.provideLiquidityRate = provideLiquidityRate;
    }

    @Override
    public String toString() {
        return "Commission [provideLiquidityRate=" + provideLiquidityRate + ", takeLiquidityRate=" + takeLiquidityRate
                + "]";
    }
    
}
