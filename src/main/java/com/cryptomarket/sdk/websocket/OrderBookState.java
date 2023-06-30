package com.cryptomarket.sdk.websocket;

public enum OrderBookState {
    UPDATING,
    BROKEN,
    BROKEN_PARSE_ERROR,
    WAITING;
}
