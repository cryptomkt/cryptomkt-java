package com.cryptomarket.sdk;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

public class Adapter {
    private final Moshi moshi = new Moshi.Builder().build();

    public <T> T objectFromJson(String json, Class<T> cls) throws CryptomarketSDKException {
        JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
        try {
            return jsonAdapter.fromJson(json);
        } catch (IOException e) {
            throw new CryptomarketSDKException("failed to parse json response", e);
        }
    }

    public <T> List<T> listFromJson(String json, Class<T> cls) throws CryptomarketSDKException {
        Type type = Types.newParameterizedType(List.class, cls);
        JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
        try {
            return jsonAdapter.fromJson(json);
        } catch (IOException e) {
            throw new CryptomarketSDKException("failed to parse json response", e);
        }
    }

	public <T> Map<String, List<T>> listMapFromJson(String json, Class<T> cls) throws CryptomarketSDKException {
		try {
            Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
            JsonAdapter<Map<String, Object>> mapAdapter = moshi.adapter(type);
            Map<String, Object> objectMap = mapAdapter.fromJson(json);
            type = Types.newParameterizedType(List.class, cls);
            JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
            
            Map<String, List<T>> parsed = new HashMap<String, List<T>>();
            objectMap.forEach((key, value) -> {
                parsed.put(key, jsonAdapter.fromJsonValue(value));
            });
            return parsed;
        } catch (IOException e) {
            throw new CryptomarketSDKException("failed to parse json response", e);
        }
    }

    public <T> Map<String, T> mapFromJson(String json, Class<T> cls) throws CryptomarketSDKException {
		try {
            Type type = Types.newParameterizedType(Map.class, String.class, cls);
            JsonAdapter<Map<String, T>> mapAdapter = moshi.adapter(type);
            return  mapAdapter.fromJson(json);
        } catch (IOException e) {
            throw new CryptomarketSDKException("failed to parse json response", e);
        }
    }

    public <T> T objectFromJsonValue(String json, String key, Class<T> cls) throws CryptomarketSDKException {
        Map<String, Object> objectMap = mapFromJson(json, Object.class);
        JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
        Object value = objectMap.get(key);
        return jsonAdapter.fromJsonValue(value);   
    }

    public <T> List<T> listFromJsonValue(String json, String key, Class<T> cls) throws CryptomarketSDKException {
        Map<String, Object> objectMap = mapFromJson(json, Object.class);
        Type type = Types.newParameterizedType(List.class, cls);
        JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
        Object value = objectMap.get(key);
        return jsonAdapter.fromJsonValue(value);   
    }

    public <T> T objectFromValue(Object value, Class<T> cls) {
        JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
        return jsonAdapter.fromJsonValue(value);
    }

    public <T> List<T> listFromValue(Object value, Class<T> cls) {
        Type type = Types.newParameterizedType(List.class, cls);
        JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
        return jsonAdapter.fromJsonValue(value);
    }
}