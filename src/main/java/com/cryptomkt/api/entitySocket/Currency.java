package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {

    @JsonProperty("kind")
    private Integer kind;

    @JsonProperty("prefix")
    private  String prefix;

    @JsonProperty("decimals")
    private Integer decimals;

    @JsonProperty("name")
    private String name;

    @JsonProperty("big_name")
    private String bigName;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("postfix")
    private String postfix;


    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBigName() {
        return bigName;
    }

    public void setBigName(String bigName) {
        this.bigName = bigName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
}
