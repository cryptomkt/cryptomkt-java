package com.cryptomkt.api.utils;

public class Int3Tuple {
    public Integer fst;
    public Integer snd;
    private Integer trd;

    static Integer DELTA = 0;
    static Integer ARRAY = 1;

    public static Int3Tuple newDeltaRef(Integer from, Integer to) {
        return new Int3Tuple(from, to, DELTA);
    }

    public static Int3Tuple newArrayRef(Integer from, Integer to) {
        return new Int3Tuple(from, to, ARRAY);
    }

    public Int3Tuple(Integer fst, Integer snd, Integer trd) {
        this.fst = fst;
        this.snd = snd;
        this.trd = trd;
    }

    public boolean isArrayRef() {
        return this.trd.equals(ARRAY);
    }

    public boolean isDeltaRef() {
        return this.trd.equals(DELTA);
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "fst=" + fst +
                ", snd=" + snd +
                ", trd=" + trd +
                '}';
    }
}
