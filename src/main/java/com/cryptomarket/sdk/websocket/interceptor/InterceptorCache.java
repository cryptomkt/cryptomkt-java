package com.cryptomarket.sdk.websocket.interceptor;

import java.util.HashMap;
import java.util.Map;


public class InterceptorCache {
    Map<String, Interceptor> interceptors = new HashMap<String, Interceptor>();
    Integer nextId = 1;

    private Integer getNextId() {
        int next = nextId;
        nextId++;
        if (nextId <= 0) nextId = 1;
        return next;
    }

    public Integer storeInterceptor(Interceptor interceptor) {
        Integer id = getNextId();
        interceptors.put(id.toString(), interceptor);
        return id;
    }
    public Interceptor popInterceptor(Integer id) {
        // if (!interceptors.containsKey(id)) throw CallbackCacheException.newIdException(id.toString());
        Interceptor callback = interceptors.get(id.toString());
        interceptors.remove(id.toString());
        return callback;
    }

    public void storeSubscriptionInterceptor(String key, Interceptor interceptor) {
        this.interceptors.put(key, interceptor);
    }

    public Interceptor getSubscriptionInterceptor(String key) {
        if (!this.interceptors.containsKey(key)) return null;
        return this.interceptors.get(key);
    }

    public void deleteSubscriptionInterceptor(String key) {
        this.interceptors.remove(key);
    }
}
