package com.cryptomarket.sdk.websocket.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InterceptorCache {
  private Map<String, Interceptor> subscriptionInterceptors = new HashMap<String, Interceptor>();
  private Map<String, RecallableIntercaptor> interceptors = new HashMap<String, RecallableIntercaptor>();
  private Integer nextId = 1;

  private Integer getNextId() {
    int next = nextId;
    nextId++;
    if (nextId <= 0)
      nextId = 1;
    return next;
  }

  public Integer saveInterceptor(Interceptor interceptor) {
    return saveInterceptor(interceptor, 1);
  }

  public Integer saveInterceptor(Interceptor interceptor, Integer callCount) {
    Integer id = getNextId();
    interceptors.put(id.toString(), new RecallableIntercaptor(interceptor, callCount));
    return id;
  }

  public Optional<Interceptor> getInterceptor(Integer id) {
    var recallableInterceptor = interceptors.get(id.toString());
    if (recallableInterceptor == null) {
      return Optional.empty();
    }
    var interceptor = recallableInterceptor.getInterceptor();
    if (recallableInterceptor.doneRecalling()) {
      subscriptionInterceptors.remove(id.toString());
    }
    return interceptor;
  }

  public void storeSubscriptionInterceptor(String key, Interceptor interceptor) {
    this.subscriptionInterceptors.put(key, interceptor);
  }

  public Interceptor getSubscriptionInterceptor(String key) {
    if (!this.subscriptionInterceptors.containsKey(key))
      return null;
    return this.subscriptionInterceptors.get(key);
  }

  public void deleteSubscriptionInterceptor(String key) {
    this.subscriptionInterceptors.remove(key);
  }
}
