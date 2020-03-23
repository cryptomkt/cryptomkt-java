package com.cryptomkt.api;

import com.cryptomkt.api.entity.Response;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HTTPClientImpl implements HTTPClient {
    private static final ObjectMapper objectMapper = ObjectMapperProvider.createDefaultMapper();

    private URL baseApiUrl;
    private String apiKey;
    private String apiSecret;

    public HTTPClientImpl(String baseApiUrl, String apiVersion, String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        try {
                this.baseApiUrl = new URL(baseApiUrl+ "/" + apiVersion + "/");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // ========    PRIVATE METHODS =========

    private static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    private static <T> T deserialize(String json, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(json, typeReference);
    }

    private static <T extends Response> T handleErrors(T response) throws CryptoMarketException {
        String errors = response.getError();
        if (errors != null) {
            throw new CryptoMarketException(response.getError());
        }

        if (!response.isSuccess()) {
            throw new CryptoMarketException("Unknown error");
        }

        return response;
    }

    private void doHmacAuthentication (URL url, String body, HttpRequestBase conn) throws IOException {
        String nonce = String.valueOf(System.currentTimeMillis() / 1000L);

        String message = nonce + url.getPath() + (body != null ? body : "");
        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA384");
            mac.init(new SecretKeySpec(this.apiSecret.getBytes(), "HmacSHA384"));
        } catch (Throwable t) {
            throw new IOException(t);
        }

        String signature = new String(Hex.encodeHex(mac.doFinal(message.getBytes())));
        conn.setHeader("X-MKT-APIKEY", this.apiKey);
        conn.setHeader("X-MKT-SIGNATURE", signature);
        conn.setHeader("X-MKT-TIMESTAMP", nonce);
        conn.setHeader("Content-Type", "application/x-www-form-urlencoded");

    }

    private String runRequest(HttpRequestBase httpRequest)
            throws IOException {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        ResponseHandler<String> responseHandler = httpResponse -> {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        String responseBody = httpclient.execute(httpRequest, responseHandler);

        httpclient.close();
        return responseBody;
    }

    @Override
    public <T extends Response> T get(String endpoint, Map<String, String> payload, Class<T> responseClass)
            throws CryptoMarketException {
        StringBuilder spec = new StringBuilder(endpoint);
        if (payload != null) {
            spec.append("?");
            for (Map.Entry<String, String> entry : payload.entrySet()) {
                spec.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            spec.deleteCharAt(spec.length()-1);
        }

        HttpGet getRequest;
        try{
            URL url = new URL(this.baseApiUrl, spec.toString());
            getRequest = new HttpGet(url.toURI());
            doHmacAuthentication(url, null, getRequest);
        } catch (MalformedURLException ex){
            throw new AssertionError(ex);
        } catch (URISyntaxException e) {
            throw new CryptoMarketException("Malformed URL");
        } catch (IOException e) {
            throw new CryptoMarketException(e.getMessage());
        }

        T response;
        try {
            response = deserialize(this.runRequest(getRequest), responseClass);
        } catch (IOException e) {
            throw new CryptoMarketException(e.getMessage());
        }
        return handleErrors(response);
    }

    @Override
    public <T extends Response> T post(String endpoint, Map<String, String> payload, Class<T> responseClass)
            throws CryptoMarketException {
        List<String> keys = new ArrayList<>(payload.keySet());
        Collections.sort(keys);
        StringBuilder body = new StringBuilder();
        for (String key : keys) {
            body.append(payload.get(key));
        }

        HttpPost postRequest;
        try{
            URL url = new URL(this.baseApiUrl, endpoint);
            postRequest = new HttpPost(url.toURI());
            doHmacAuthentication(url, body.toString(), postRequest);
        } catch (MalformedURLException ex){
            throw new AssertionError(ex);
        } catch (URISyntaxException e) {
            throw new CryptoMarketException("Malformed URL");
        } catch (IOException e) {
            throw new CryptoMarketException(e.getMessage());
        }

        StringBuilder data = new StringBuilder();
        T response;
        try {
            for (Map.Entry<String, String> entry : payload.entrySet()) {
                data.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                data.append("=");
                data.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                data.append("&");
            }
            data.deleteCharAt(data.length()-1);
            postRequest.setEntity(new StringEntity(data.toString()));

            response = deserialize(this.runRequest(postRequest), responseClass);
        } catch (IOException e) {
            throw new CryptoMarketException(e.getMessage());
        }
        return handleErrors(response);
    }
}
