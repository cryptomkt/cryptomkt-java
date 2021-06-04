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

    HMAC(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String getCredential(String method, String body, String url) {
        String timestamp = String.format("%d", System.currentTimeMillis() / 1000);
        String message = new StringBuffer()
            .append(method)
            .append(timestamp)
            .append(url)
            .append((method.equals("GET") && body != null) ? "?" : "")
            .append((body != null) ? body : "")
            .toString();
        try {
            String signature = HMAC.sign(this.apiSecret, message);
            String str = this.apiKey + ":" + timestamp + ":" + signature;
            byte[] strBytes = str.getBytes(charset);
            String authStr = Base64.getEncoder().encodeToString(strBytes).strip();
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
            System.out.println("signing failed");
            return null;
        }
    }
}
