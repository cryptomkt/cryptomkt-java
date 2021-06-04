package com.cryptomarket.sdk.models;

public class ErrorResponse {
    private int status;
    private ErrorBody error;
    private String timestamp;
    private String path;
    private String requestId;
    private String message;

    public int getStatus() {
        return status;
    }

    public ErrorBody getError() {
        return error;
    }


    public String getTimestamp() {
        return timestamp;
    }


    public String getPath() {
        return path;
    }

    public String getRequestId() {
        return requestId;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorResponse [error=" + error + ", message=" + message + ", path=" + path + ", requestId=" + requestId
                + ", status=" + status + ", timestamp=" + timestamp + "]";
    }

}
