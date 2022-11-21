package com.cryptomarket.sdk;

import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;

import org.jetbrains.annotations.Nullable;

public interface HttpClient {

      public String publicGet(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException;

      public String get(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException;

      public String post(String endpoint, Map<String, String> params) throws CryptomarketSDKException;

      public String put(String endpoint, Map<String, String> params) throws CryptomarketSDKException;


      public String patch(String endpoint, Map<String, String> params) throws CryptomarketSDKException;

	public String delete(String endpoint, Map<String, String> params) throws CryptomarketSDKException;
}