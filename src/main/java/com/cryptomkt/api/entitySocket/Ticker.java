package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Ticker implements Serializable {

    @JsonProperty("BID")
    private Double BID;

    @JsonProperty("ASK")
    private Double ASK;

    @JsonProperty("delta1d")
    private Double delta1d;

    @JsonProperty("delta7d")
    private Double delta7d;

    public Double getBID() {
        return BID;
    }

    public void setBID(Double BID) {
        this.BID = BID;
    }

    public Double getASK() {
        return ASK;
    }

    public void setASK(Double ASK) {
        this.ASK = ASK;
    }

    public Double getDelta1d() {
        return delta1d;
    }

    public void setDelta1d(Double delta1d) {
        this.delta1d = delta1d;
    }

    public Double getDelta7d() {
        return delta7d;
    }

    public void setDelta7d(Double delta7d) {
        this.delta7d = delta7d;
    }

}
