package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Websocket response
 */
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

  /**
   * Gets the json rpc
   *
   * @return
   */
  public String getJsonrpc() {
    return jsonrpc;
  }

  /**
   * Gets the response data
   *
   * @return
   */
  public Object getData() {
    return data;
  }

  /**
   * Sets the response data
   *
   * @param data
   */
  public void setData(Object data) {
    this.data = data;
  }

  /**
   * Gets the update field
   *
   * @return
   */
  public Object getUpdate() {
    return update;
  }

  /**
   * Sets the update field
   *
   * @param update
   */
  public void setUpdate(Object update) {
    this.update = update;
  }

  /**
   * Gets the snapshot field
   *
   * @return
   */
  public Object getSnapshot() {
    return snapshot;
  }

  /**
   * Sets the snapshot field
   *
   * @param snapshot
   */
  public void setSnapshot(Object snapshot) {
    this.snapshot = snapshot;
  }

  /**
   * Sets the json rpc
   *
   * @param jsonrpc
   */
  public void setJsonrpc(String jsonrpc) {
    this.jsonrpc = jsonrpc;
  }

  /**
   * Gets the response id
   *
   * @return
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the response id
   *
   * @param id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the response method
   *
   * @return
   */
  public String getMethod() {
    return method;
  }

  /**
   * Sets the response method
   *
   * @param method
   */
  public void setMethod(String method) {
    this.method = method;
  }

  /**
   * Gets the response channel
   *
   * @return
   */
  public String getChannel() {
    return channel;
  }

  /**
   * Sets the response channel
   *
   * @param channel
   */
  public void setChannel(String channel) {
    this.channel = channel;
  }

  /**
   * Gets the response error
   *
   * @return
   */
  public ErrorBody getError() {
    return error;
  }

  /**
   * Sets the response error
   *
   * @param error
   */
  public void setError(ErrorBody error) {
    this.error = error;
  }

  /**
   * Gets the response params
   *
   * @return
   */
  public Object getParams() {
    return params;
  }

  /**
   * Sets the response params
   *
   * @param params
   */
  public void setParams(Object params) {
    this.params = params;
  }

  /**
   * Gets the response result
   *
   * @return
   */
  public Object getResult() {
    return result;
  }

  /**
   * Sets the response result
   *
   * @param result
   */
  public void setResult(Object result) {
    this.result = result;
  }

  /**
   * Gets the response target currency
   *
   * @return
   */
  public String getTargetCurrency() {
    return targetCurrency;
  }

  /**
   * Sets the response target currency
   *
   * @param targetCurrency
   */
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
