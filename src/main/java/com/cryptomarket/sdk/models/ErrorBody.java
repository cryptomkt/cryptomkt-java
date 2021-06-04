package com.cryptomarket.sdk.models;

public class ErrorBody {
    private int code;
    private String message;
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "ErrorBody [code=" + code + ", description=" + description + ", message=" + message + "]";
    }
}