package com.cryptomarket.sdk.rest;

import java.io.Closeable;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;

import org.jetbrains.annotations.Nullable;

public interface CloseableHttpClient extends Closeable {

  /**
   * Changes the user credentials used for authentication in calls
   *
   * @param apiKey    the user public key used in new calls
   * @param apiSecret the user secret key used in new calls
   */
  public void changeCredentials(String apiKey, String apiSecret);

  /**
   * Changes the window used in authenticated calls
   *
   * @param window acceptable time between request and server execution
   */
  public void changeWindow(Integer window);

  /**
   * Does an http get without authentication
   *
   * @param endpoint The endpoint to make the request
   * @param params   The request params
   * @return A json response
   * @throws CryptomarketSDKException
   */
  public String publicGet(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException;

  /**
   * Does an http get with authentication
   *
   * @param endpoint The endpoint to make the request
   * @param params   The request params
   * @return A json response
   * @throws CryptomarketSDKException
   */
  public String get(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException;

  /**
   * Does an http post with authentication
   *
   * @param endpoint The endpoint to make the request
   * @param payload  The json String request payload
   * @return A json response
   * @throws CryptomarketSDKException
   */
  public String post(String endpoint, String payload) throws CryptomarketSDKException;

  /**
   * Does an http post with authentication
   *
   * @param endpoint The endpoint to make the request
   * @param payload  The Map(String, String) payload of the request
   * @return A json response
   * @throws CryptomarketSDKException
   */
  public String post(String endpoint, Map<String, String> payload) throws CryptomarketSDKException;

  /**
   * Does an http put with authentication
   *
   * @param endpoint The endpoint to make the request
   * @param params   The request params
   * @return A json response
   * @throws CryptomarketSDKException
   */
  public String put(String endpoint, Map<String, String> params) throws CryptomarketSDKException;

  /**
   * Does an http path with authentication
   *
   * @param endpoint The endpoint to make the request
   * @param params   The request params
   * @return A json response
   * @throws CryptomarketSDKException
   */
  public String patch(String endpoint, Map<String, String> params) throws CryptomarketSDKException;

  /**
   * Does an http delete with authentication
   *
   * @param endpoint The endpoint to make the request
   * @param params   The request params
   * @return A json response
   * @throws CryptomarketSDKException
   */
  public String delete(String endpoint, Map<String, String> params) throws CryptomarketSDKException;
}
