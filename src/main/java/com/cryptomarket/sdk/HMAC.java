package com.cryptomarket.sdk;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HMAC {
  private static String HMAC_SHA256 = "HmacSHA256";
  private static Charset charset = Charset.forName("US-ASCII");
  // private static Charset charset = Charset.forName("UTF-8");
  private String apiSecret;
  private String apiKey;
  private Integer window;

  HMAC(String apiKey, String apiSecret, Integer window) {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    this.window = window;
  }

  public String getCredential(String method, String body, String url) {
    String timestamp = String.format("%d", System.currentTimeMillis());
    String message = new StringBuffer()
        .append(method)
        .append(url)
        .append((method.equals("GET") && body != null) ? "?" : "")
        .append((body != null) ? body : "")
        .append(timestamp)
        .append(window != 0 ? window : "")
        .toString();
    try {
      String signature = HMAC.sign(this.apiSecret, message);
      String signed = new StringBuffer()
          .append(this.apiKey)
          .append(":")
          .append(signature)
          .append(":")
          .append(timestamp)
          .append(window != 0 ? ":" : "")
          .append(window != 0 ? window : "")
          .toString();
      byte[] strBytes = signed.getBytes(charset);
      String authStr = Base64.getEncoder().encodeToString(strBytes).trim();
      return "HS256 " + authStr;
    } catch (Exception e) {
      return null;
    }
  }

  public static String sign(String key, String message) {
    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(charset), HMAC_SHA256);
    Mac sha256Hmac;
    try {
      sha256Hmac = Mac.getInstance(HMAC_SHA256);
      sha256Hmac.init(keySpec);
      byte[] macData = sha256Hmac.doFinal(message.getBytes(charset));
      return new String(Hex.encodeHex(macData));
    } catch (Exception e) {
      return null;
    }
  }
}
