package com.cryptomkt.api;

import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
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

public class CryptoMarketImpl implements CryptoMarket {

    private static final ObjectMapper objectMapper = ObjectMapperProvider.createDefaultMapper();

    private URL baseApiUrl;
    private String apiKey;
    private String apiSecret;

    public CryptoMarketImpl(CryptoMarketBuilder cryptoMarketBuilder) {
        this.baseApiUrl = cryptoMarketBuilder.getBaseApiUrl();
        this.apiKey = cryptoMarketBuilder.getApiKey();
        this.apiSecret = cryptoMarketBuilder.getApiSecret();

        try {
            if (this.baseApiUrl == null) {
                this.baseApiUrl = new URL("https://api.cryptomkt.com/v1/");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public MarketsResponse getMarkets() throws IOException, CryptoMarketException{
        URL marketsUrl;
        try{
            marketsUrl = new URL(this.baseApiUrl, "market");
        }catch (MalformedURLException ex){
            throw new AssertionError(ex);
        }

        return get(marketsUrl, MarketsResponse.class);
    }

    @Override
    public TickersResponse getTickers() throws IOException, CryptoMarketException {
        URL tickerUrl;
        try{
            tickerUrl = new URL(this.baseApiUrl, "ticker");
        }catch (MalformedURLException ex){
            throw new AssertionError(ex);
        }

        return get(tickerUrl, TickersResponse.class);
    }

    @Override
    public TickersResponse getTickers(String market) throws IOException, CryptoMarketException {
        URL tickerUrl;
        try{
            tickerUrl = new URL(this.baseApiUrl, "ticker?" + "market=" + market);
        }catch (MalformedURLException ex){
            throw new AssertionError(ex);
        }

        return get(tickerUrl, TickersResponse.class);
    }

    @Override
    public BookResponse getBook(String market, String type) throws IOException, CryptoMarketException {
        return this.getBook(market, type, 0, 20);
    }

    @Override
    public BookResponse getBook(String market, String type, int page) throws IOException, CryptoMarketException {
        return this.getBook(market, type, page, 20);
    }

    @Override
    public BookResponse getBook(String market, String type, int page, int limit) throws IOException, CryptoMarketException {
        URL bookUrl;
        try{
            bookUrl = new URL(this.baseApiUrl, "book?" +
                    "market=" + market +
                    "&type=" + type +
                    "&page=" + page +
                    "&limit=" + limit);
        }catch (MalformedURLException ex){
            throw new AssertionError(ex);
        }

        return get(bookUrl, BookResponse.class);
    }

    @Override
    public TradeResponse getTrades(String market) throws IOException, CryptoMarketException {
        return this.getTrades(market, "", "", 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start) throws IOException, CryptoMarketException {
        return this.getTrades(market, start, "", 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end) throws IOException, CryptoMarketException {
        return this.getTrades(market, start, end, 0, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end, int page) throws IOException, CryptoMarketException {
        return this.getTrades(market, start, end, page, 20);
    }

    @Override
    public TradeResponse getTrades(String market, String start, String end, int page, int limit) throws IOException, CryptoMarketException {
        URL tradeUrl;
        try{
            String _start = start.isEmpty()?"":("&start=" + start);
            String _end = end.isEmpty()?"":("&end=" + end);

            tradeUrl = new URL(this.baseApiUrl, "trades?" +
                    "market=" + market +
                    _start +
                    _end +
                    "&page=" + page +
                    "&limit=" + limit);
        }catch (MalformedURLException ex){
            throw new AssertionError(ex);
        }

        return get(tradeUrl, TradeResponse.class);
    }

    @Override
    public OrderResponse getActiveOrders(String market) throws IOException, CryptoMarketException {
        return this.getActiveOrders(market, 0, 20);
    }

    @Override
    public OrderResponse getActiveOrders(String market, int page) throws IOException, CryptoMarketException {
        return this.getActiveOrders(market, page, 20);
    }

    @Override
    public OrderResponse getActiveOrders(String market, int page, int limit) throws IOException, CryptoMarketException {
        URL orderUrl;
        try{

            orderUrl = new URL(this.baseApiUrl, "orders/active?" +
                    "market=" + market +
                    "&page=" + page +
                    "&limit=" + limit);

        }catch (MalformedURLException ex){
            throw new AssertionError(ex);
        }

        return get(orderUrl, OrderResponse.class);
    }


    // ========    PRIVATE METHODS =========

    private static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    private static <T> T deserialize(String json, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(json, typeReference);
    }


    private <T extends Response> T get(URL url, Class<T> responseClass) throws IOException, CryptoMarketException {
        return handleErrors(deserialize(doHttp(url, "GET", null), responseClass));
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

        Mac mac = null;
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

    private String doHttp(URL url, String method, List<NameValuePair> requestBody) throws IOException, CryptoMarketException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
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

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };

        String responseBody = httpclient.execute(httpRequest, responseHandler);

        httpclient.close();
        return responseBody;
    }
}
