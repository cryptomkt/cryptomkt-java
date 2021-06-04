package com.cryptomarket.sdk.models;

public class WSJsonResponse {
    private String jsonrpc;
    private Integer id;
    private String method;
    private ErrorBody error;
    private Object params;
    private Object result;

    public String getJsonrpc() {
        return jsonrpc;
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

    @Override
    public String toString() {
        return "WSJsonResponse [error=" + error + ", id=" + id + ", jsonrpc=" + jsonrpc + ", method=" + method
                + ", params=" + params + ", result=" + result + "]";
    }
}
