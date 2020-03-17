package com.cryptomkt.api.entity;

import java.io.Serializable;

public class Market implements Serializable {

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toSting() { return name; }
}