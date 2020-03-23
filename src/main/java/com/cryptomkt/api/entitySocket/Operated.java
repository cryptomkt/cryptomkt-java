package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Operated implements Serializable {

    @JsonProperty("flag")
    private String flag;

    @JsonProperty("threshold")
    private String threshold;

    @JsonProperty("traded")
    private String traded;

    @JsonProperty("tk")
    private String tk;

    @JsonProperty("mk")
    private String mk;


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getTraded() {
        return traded;
    }

    public void setTraded(String traded) {
        this.traded = traded;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }




}
