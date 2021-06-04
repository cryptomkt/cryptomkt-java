package com.cryptomarket.sdk;

public abstract class Callback<T> {
    abstract public void resolve(T result);
    public void reject(Throwable exception) {};
}
