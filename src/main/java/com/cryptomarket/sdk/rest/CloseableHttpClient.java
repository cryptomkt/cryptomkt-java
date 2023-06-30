package com.cryptomarket.sdk.rest;

import java.io.Closeable;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;

import org.jetbrains.annotations.Nullable;

public interface CloseableHttpClient extends Closeable {

      public String publicGet(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException;

      public String get(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException;

      public String post(String endpoint, String payload) throws CryptomarketSDKException;

      public String post(String endpoint, Map<String, String> payload) throws CryptomarketSDKException;
      
      public String put(String endpoint, Map<String, String> params) throws CryptomarketSDKException;

      public String patch(String endpoint, Map<String, String> params) throws CryptomarketSDKException;

      public String delete(String endpoint, Map<String, String> params) throws CryptomarketSDKException;
}