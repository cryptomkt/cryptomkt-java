package com.cryptomarket.sdk.rest;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Nullable;

import com.cryptomarket.sdk.HMAC;
import com.cryptomarket.sdk.exceptions.CryptomarketAPIException;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.ErrorResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

public class HttpClientImpl implements CloseableHttpClient {
  private static final String APPLICATION_JSON = "application/json";
  private static final String USER_AGENT = "cryptomarket/java";
  private static final String APPLICATOIN_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

  private final String url;
  private final String apiVersion;
  private final Moshi moshi = new Moshi.Builder().build();
  private final JsonAdapter<ErrorResponse> errorJsonAdapter = moshi.adapter(ErrorResponse.class);
  private final ParameterizedType mapStringString = Types.newParameterizedType(Map.class, String.class, String.class);
  private final JsonAdapter<Map<String, String>> mapStrStrJsonAdapter = moshi.adapter(mapStringString);
  private final org.apache.http.impl.client.CloseableHttpClient client;
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

  public HttpClientImpl(String url, String apiVersion, String apiKey, String apiSecret) {
    this(url, apiVersion, apiKey, apiSecret, 0);
  }

  public HttpClientImpl(org.apache.http.impl.client.CloseableHttpClient client, String url, String apiVersion,
      String apiKey, String apiSecret) {
    this(client, url, apiVersion, apiKey, apiSecret, 0);
  }

  public HttpClientImpl(String url, String apiVersion, String apiKey, String apiSecret, Integer window) {
    this.client = HttpClients.createDefault();
    this.hmac = new HMAC(apiKey, apiSecret, window);
    this.url = url;
    this.apiVersion = apiVersion;
  }

  public HttpClientImpl(org.apache.http.impl.client.CloseableHttpClient client, String url, String apiVersion,
      String apiKey, String apiSecret, Integer window) {
    this.client = client;
    this.hmac = new HMAC(apiKey, apiSecret, window);
    this.url = url;
    this.apiVersion = apiVersion;
  }

  public void close() throws IOException {
    client.close();
  }

  @Override
  public String publicGet(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException {
    URI uri = null;
    try {
      URIBuilder uriBuilder = new URIBuilder(url + apiVersion + endpoint);
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
    URI uri = buildUri(endpoint, params);
    HttpGet httpGet = new HttpGet(uri);
    new HttpPost(uri);
    String credential = hmac.getCredential(HttpMethod.GET.toString(), uri.getQuery(), uri.getPath());
    httpGet.setHeader(HttpHeaders.AUTHORIZATION, credential);
    return makeRequest(httpGet);
  }

  private URI buildUri(String endpoint, Map<String, String> params) throws CryptomarketSDKException {
    try {
      URIBuilder uriBuilder = new URIBuilder(url + apiVersion + endpoint);
      if (params != null)
        params.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(e -> uriBuilder.addParameter(e.getKey(), e.getValue()));
      return uriBuilder.build();
    } catch (URISyntaxException e) {
      throw new CryptomarketSDKException("Failed to build the uri", e);
    }
  }

  public String post(String endpoint, Map<String, String> payload) throws CryptomarketSDKException {
    String strPayload = "";
    if (payload != null)
      strPayload = mapStrStrJsonAdapter.toJson(payload);

    return post(endpoint, strPayload);
  }

  @Override
  public String post(String endpoint, String payload) throws CryptomarketSDKException {
    HttpPost httpPost = new HttpPost(url + apiVersion + endpoint);
    if (!payload.equals("")) {
      httpPost.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));
      httpPost.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
    }
    String credential = hmac.getCredential(HttpMethod.POST.toString(), payload, apiVersion + endpoint);
    httpPost.setHeader(HttpHeaders.AUTHORIZATION, credential);
    return makeRequest(httpPost);
  }

  @Override
  public String put(String endpoint, @Nullable Map<String, String> params) throws CryptomarketSDKException {
    HttpPut httpPut = new HttpPut(url + apiVersion + endpoint);
    UrlEncodedFormEntity entity = null;
    if (params != null) {
      entity = paramsToUrlEncodedEntity(params);
      httpPut.setEntity(entity);
    }
    addAuthorizationHeader(httpPut, HttpMethod.PUT, endpoint, entity);

    httpPut.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
    httpPut.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATOIN_X_WWW_FORM_URLENCODED);
    return makeRequest(httpPut);
  }

  private HttpUriRequest addAuthorizationHeader(HttpUriRequest request, HttpMethod method, String endpoint,
      @Nullable HttpEntity entity) throws CryptomarketSDKException {
    String strEntity = entityAsStr(entity);
    String credential = hmac.getCredential(
        method.toString(),
        strEntity,
        apiVersion + endpoint);
    request.setHeader(HttpHeaders.AUTHORIZATION, credential);
    return request;
  }

  private String entityAsStr(@Nullable HttpEntity entity) throws CryptomarketSDKException {
    if (entity == null) {
      return "";
    }
    try {
      return EntityUtils.toString(entity);
    } catch (ParseException | IOException e) {
      throw new CryptomarketSDKException("failed hmac authentication", e);
    }
  }

  private UrlEncodedFormEntity paramsToUrlEncodedEntity(Map<String, String> params) {
    List<NameValuePair> form = new ArrayList<>();
    params.forEach((key, value) -> form.add(new BasicNameValuePair(key, value)));
    return new UrlEncodedFormEntity(form, Consts.UTF_8);
  }

  @Override
  public String patch(String endpoint, Map<String, String> params) throws CryptomarketSDKException {
    HttpPatch httpPatch = new HttpPatch(url + apiVersion + endpoint);
    UrlEncodedFormEntity entity = null;
    if (params != null) {
      entity = paramsToUrlEncodedEntity(params);
      httpPatch.setEntity(entity);
    }
    try {
      String body = "";
      if (entity != null)
        body = EntityUtils.toString(entity);
      String credential = hmac.getCredential("PATCH", body, apiVersion + endpoint);
      httpPatch.setHeader(HttpHeaders.AUTHORIZATION, credential);
    } catch (Exception e) {
      throw new CryptomarketSDKException("failed hmac authentication", e);
    }

    httpPatch.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
    httpPatch.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATOIN_X_WWW_FORM_URLENCODED);

    return makeRequest(httpPatch);
  }

  @Override
  public String delete(String endpoint, Map<String, String> params) throws CryptomarketSDKException {
    HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url + apiVersion + endpoint);
    UrlEncodedFormEntity entity = null;
    if (params != null) {
      entity = paramsToUrlEncodedEntity(params);
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

    httpDelete.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
    httpDelete.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATOIN_X_WWW_FORM_URLENCODED);
    return makeRequest(httpDelete);
  }

  private String makeRequest(HttpUriRequest request) throws CryptomarketSDKException {
    request.setHeader(HttpHeaders.CONNECTION, "Keep-Alive");
    request.setHeader(HttpHeaders.USER_AGENT, USER_AGENT);
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
