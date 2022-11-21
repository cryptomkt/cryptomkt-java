package com.cryptomarket.sdk;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketAPIException;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.ErrorResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.apache.http.Consts;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Nullable;

public class HttpClientImpl implements HttpClient {
  private final String url = "https://api.exchange.cryptomkt.com";
  private final String apiVersion = "/api/3/";
  private final Moshi moshi = new Moshi.Builder().build();
  private final JsonAdapter<ErrorResponse> errorJsonAdapter = moshi.adapter(ErrorResponse.class);
  CloseableHttpClient client = HttpClients.createDefault();
  private HMAC hmac;

  class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
      return METHOD_NAME;
    }

    public HttpDeleteWithBody(final String uri) {
      super();
      setURI(URI.create(uri));
    }

    public HttpDeleteWithBody(final URI uri) {
      super();
      setURI(uri);
    }

    public HttpDeleteWithBody() {
      super();
    }
  }

  public HttpClientImpl(String apiKey, String apiSecret) {
    hmac = new HMAC(apiKey, apiSecret, 0);
  }

  public HttpClientImpl(String apiKey, String apiSecret, Integer window) {
    hmac = new HMAC(apiKey, apiSecret, window);
  }

  public void close() throws IOException {
    client.close();
  }

  @Override
  public String publicGet(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException {
    URI uri = null;
    try {
      URIBuilder uriBuilder = new URIBuilder(this.url + this.apiVersion + endpoint);
      if (params != null)
        params.forEach((key, val) -> uriBuilder.addParameter(key, val));
      uri = uriBuilder.build();
    } catch (URISyntaxException e) {
      throw new CryptomarketSDKException("Failed to build the uri", e);
    }
    HttpGet httpGet = new HttpGet(uri);
    return makeRequest(httpGet);
  }

  @Override
  public String get(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException {
    URI uri = null;
    try {
      URIBuilder uriBuilder = new URIBuilder(this.url + this.apiVersion + endpoint);
      if (params != null)
        params.entrySet().stream().sorted(Map.Entry.comparingByKey())
            .forEach(e -> uriBuilder.addParameter(e.getKey(), e.getValue()));
      uri = uriBuilder.build();
    } catch (URISyntaxException e) {
      throw new CryptomarketSDKException("Failed to build the uri", e);
    }
    HttpGet httpGet = new HttpGet(uri);
    String credential = hmac.getCredential("GET", uri.getQuery(), uri.getPath());
    httpGet.setHeader(HttpHeaders.AUTHORIZATION, credential);
    return makeRequest(httpGet);
  }

  @Override
  public String post(String endpoint, Map<String, String> params) throws CryptomarketSDKException {
    HttpPost httpPost = new HttpPost(url + apiVersion + endpoint);
    UrlEncodedFormEntity entity = null;
    if (params != null) {
      List<NameValuePair> form = new ArrayList<>();
      params.forEach((key, value) -> form.add(new BasicNameValuePair(key, value)));
      entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
      httpPost.setEntity(entity);
    }
    try {
      String body = "";
      if (entity != null)
        body = EntityUtils.toString(entity);
      String credential = hmac.getCredential("POST", body, this.apiVersion + endpoint);
      httpPost.setHeader(HttpHeaders.AUTHORIZATION, credential);
    } catch (Exception e) {
      throw new CryptomarketSDKException("failed hmac authentication", e);
    }
    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
    httpPost.setHeader("User-Agent", "cryptomarket/java");
    return makeRequest(httpPost);
  }

  @Override
  public String put(String endpoint, Map<String, String> params) throws CryptomarketSDKException {
    HttpPut httpPut = new HttpPut(url + apiVersion + endpoint);
    UrlEncodedFormEntity entity = null;
    if (params != null) {
      List<NameValuePair> form = new ArrayList<>();
      params.forEach((key, value) -> form.add(new BasicNameValuePair(key, value)));
      entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
      httpPut.setEntity(entity);
    }
    try {
      String body = "";
      if (entity != null)
        body = EntityUtils.toString(entity);
      String credential = hmac.getCredential("PUT", body, this.apiVersion + endpoint);
      httpPut.setHeader(HttpHeaders.AUTHORIZATION, credential);
    } catch (Exception e) {
      throw new CryptomarketSDKException("failed hmac authentication", e);
    }

    httpPut.setHeader("Accept", "application/json");
    httpPut.setHeader("Content-type", "application/x-www-form-urlencoded");

    return makeRequest(httpPut);
  }

  @Override
  public String patch(String endpoint, Map<String, String> params) throws CryptomarketSDKException {
    HttpPatch httpPatch = new HttpPatch(url + apiVersion + endpoint);
    UrlEncodedFormEntity entity = null;
    if (params != null) {
      List<NameValuePair> form = new ArrayList<>();
      params.forEach((key, value) -> form.add(new BasicNameValuePair(key, value)));
      entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
      httpPatch.setEntity(entity);
    }
    try {
      String body = "";
      if (entity != null)
        body = EntityUtils.toString(entity);
      String credential = hmac.getCredential("PATCH", body, this.apiVersion + endpoint);
      httpPatch.setHeader(HttpHeaders.AUTHORIZATION, credential);
    } catch (Exception e) {
      throw new CryptomarketSDKException("failed hmac authentication", e);
    }

    httpPatch.setHeader("Accept", "application/json");
    httpPatch.setHeader("Content-type", "application/x-www-form-urlencoded");

    return makeRequest(httpPatch);
  }

  @Override
  public String delete(String endpoint, Map<String, String> params) throws CryptomarketSDKException {
    HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url + apiVersion + endpoint);
    UrlEncodedFormEntity entity = null;
    if (params != null) {
      List<NameValuePair> form = new ArrayList<>();
      params.forEach((key, value) -> form.add(new BasicNameValuePair(key, value)));
      entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
      httpDelete.setEntity(entity);

    }
    try {
      String body = "";
      if (entity != null)
        body = EntityUtils.toString(entity);
      String credential = hmac.getCredential("DELETE", body, this.apiVersion + endpoint);
      httpDelete.setHeader(HttpHeaders.AUTHORIZATION, credential);
    } catch (Exception e) {
      throw new CryptomarketSDKException("failed hmac authentication", e);
    }

    httpDelete.setHeader("Accept", "application/json");
    httpDelete.setHeader("Content-type", "application/x-www-form-urlencoded");
    return makeRequest(httpDelete);
  }

  private String makeRequest(HttpUriRequest request) throws CryptomarketSDKException {
    CloseableHttpResponse response;
    String responseBody;
    Boolean isSuccessful;
    try {
      response = client.execute(request);
    } catch (IOException err) {
      throw new CryptomarketSDKException("Couldn't make the call", err);
    }
    try {
      responseBody = EntityUtils.toString(response.getEntity());
      isSuccessful = response.getStatusLine().getStatusCode() == 200;
    } catch (IOException err) {
      throw new CryptomarketSDKException("Couldn't parse the response body", err);
    }
    try {
      response.close();
    } catch (IOException e) {
    }
    if (isSuccessful)
      return responseBody;
    try {
      ErrorResponse errorResponse = errorJsonAdapter.fromJson(responseBody);
      ErrorBody errorBody = errorResponse.getError();
      throw new CryptomarketAPIException(errorBody);
    } catch (IOException err) {
      throw new CryptomarketSDKException("Couldn't parse the error: " + responseBody, err);
    }
  }
}
