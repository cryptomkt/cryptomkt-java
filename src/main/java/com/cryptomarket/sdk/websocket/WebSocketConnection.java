package com.cryptomarket.sdk.websocket;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketConnection extends WebSocketListener {
  WebSocket websocket;
  Handler handler;
  String url;

  public WebSocketConnection(Handler handler, String url) {
    this.handler = handler;
    this.url = url;
  }

  void run() throws IOException {
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build();
    Request request = new Request.Builder().url(this.url).build();
    websocket = client.newWebSocket(request, this);
    client.dispatcher().executorService().shutdown();
  }

  @Override
  public void onOpen(WebSocket webSocket, Response response) {
    handler.onOpen();
  }

  @Override
  public void onClosed(WebSocket webSocket, int code, String reason) {
    handler.onClose(reason);
  }

  @Override
  public void onMessage(WebSocket webSocket, String text) {
    try {
      handler.handle(text);
    } catch (CryptomarketSDKException e) {
      e.printStackTrace();
    }
  }

  /**
   * The peer ask for close to this socket
   */
  @Override
  public void onClosing(WebSocket webSocket, int code, String reason) {
    webSocket.close(1000, null);
  }

  @Override
  public void onFailure(WebSocket webSocket, Throwable t, Response response) {
    handler.onFailure(t);
  }

  public void send(String json) {
    websocket.send(json);
  }

  /**
   * this socket ask for close to the peer
   */
  public void close() {
    websocket.close(1000, null);
  }
}
