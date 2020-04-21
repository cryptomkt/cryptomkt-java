package com.cryptomkt.api.utils;

public class IntPair {
    public Integer fst;
    public Integer snd;

    public IntPair(Integer fst, Integer snd) {
        this.fst = fst;
        this.snd = snd;
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "fst=" + fst +
                ", snd=" + snd +
                '}';
    }
}
