package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class WSJsonResponse {
  private String jsonrpc;
  private Integer id;
  private String method;
  @Json(name = "ch")
  private String channel;
  @Json(name = "target_currency")
  private String targetCurrency;

  private ErrorBody error;
  private Object params;
  private Object result;
  private Object snapshot;
  private Object update;
  private Object data;

  public String getJsonrpc() {
    return jsonrpc;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Object getUpdate() {
    return update;
  }

  public void setUpdate(Object update) {
    this.update = update;
  }

  public Object getSnapshot() {
    return snapshot;
  }

  public void setSnapshot(Object snapshot) {
    this.snapshot = snapshot;
  }

  public void setJsonrpc(String jsonrpc) {
    this.jsonrpc = jsonrpc;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public ErrorBody getError() {
    return error;
  }

  public void setError(ErrorBody error) {
    this.error = error;
  }

  public Object getParams() {
    return params;
  }

  public void setParams(Object params) {
    this.params = params;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  @Override
  public String toString() {
    return "WSJsonResponse [jsonrpc=" + jsonrpc + ", id=" + id + ", method=" + method + ", channel=" + channel
        + ", targetCurrency=" + targetCurrency + ", error=" + error + ", params=" + params + ", result=" + result
        + ", snapshot=" + snapshot + ", update=" + update + ", data=" + data + "]";
  }

}
