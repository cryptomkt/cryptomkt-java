package com.cryptomarket.sdk.rest;

/**
 * provides the getCredential method to get a credential to use in
 * authentication
 */
public interface Authenticator {
  /**
   * builds and gets a credential
   * @param method An http method
   * @param body Url encoded body of the request
   * @param url Url for the request to be authenticated
   * @return the credential
   */
  public String getCredential(String method, String body, String url);
}
