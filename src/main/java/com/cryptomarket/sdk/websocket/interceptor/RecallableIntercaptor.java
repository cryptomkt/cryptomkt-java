package com.cryptomarket.sdk.websocket.interceptor;

import java.util.Optional;

public class RecallableIntercaptor {
  private final Interceptor interceptor;
  private Integer callCount;

  public RecallableIntercaptor(Interceptor interceptor) {
    this.interceptor = interceptor;
    this.callCount = 1;
  }

  public RecallableIntercaptor(Interceptor interceptor, Integer callCount) {
    this.interceptor = interceptor;
    this.callCount = callCount;
  }

  public Optional<Interceptor> getInterceptor() {
    if (callCount < 1) {
      return Optional.empty();
    }
    callCount--;
    return Optional.of(interceptor);
  }

  public Boolean doneRecalling() {
    return callCount < 1;
  }
}
