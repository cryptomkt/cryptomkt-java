package com.cryptomkt.api;

import com.cryptomkt.api.entity.Response;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
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
//            if (errors.contains("device_confirmation_required")) {
//                throw new UnauthorizedDeviceException();
//            } else if (errors.contains("2fa_required")) {
//                throw new TwoFactorRequiredException();
//            } else if (errors.contains("2fa_incorrect")) {
//                throw new TwoFactorIncorrectException();
//            } else if (errors.contains("incorrect_credentials")) {
//                throw new CredentialsIncorrectException();
//            }
            throw new CryptoMarketException(response.getError());
        }

        if (!response.isSuccess()) {
            throw new CryptoMarketException("Unknown error");
        }

        return response;
    }

    private void doHmacAuthentication (URL url, List<NameValuePair> body, HttpRequestBase conn) throws IOException {
        String nonce = String.valueOf(System.currentTimeMillis());

        String message = nonce + url.toString() + (body != null ? body : "");

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
    }

    private String doHttp(URL url, String method, List<NameValuePair> requestBody)
            throws IOException, CryptoMarketException {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        HttpRequestBase httpRequest;
        try {
            if(method.equals("POST")){
                httpRequest = new HttpPost(url.toURI());

            }else if(method.equals("GET")){
                httpRequest = new HttpGet(url.toURI());
            }else{
                throw new CryptoMarketException("Method not supported");
            }
        } catch (URISyntaxException e) {
            throw new CryptoMarketException("Malformed URL");
        }

        String body = null;
        if (requestBody != null) {

        }

        if (this.apiKey != null && this.apiSecret != null) {
            doHmacAuthentication(url, requestBody, httpRequest);
        }

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
            throws IOException, CryptoMarketException {
        StringBuilder spec = new StringBuilder(endpoint + "?");
        if (payload != null) {
            for (Map.Entry<String, String> entry : payload.entrySet()) {
                spec.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        URL url;
        try{
            url = new URL(this.baseApiUrl, spec.toString());
        } catch (MalformedURLException ex){
            throw new AssertionError(ex);
        }
        return handleErrors(deserialize(doHttp(url, "GET", null), responseClass));
    }

    @Override
    public <T extends Response> T post(String endpoint, Map<String, String> payload, Class<T> responseClass)
            throws IOException, CryptoMarketException {
        return null;
    }
}
