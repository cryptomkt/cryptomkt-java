package com.cryptomarket.params;

public enum TimeInForce {   
    GTC,
    IOC,
    FOK,
    DAY {@Override public String toString() {return "Day";}},
    GTD,
}