package com.cryptomarket.sdk;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.ParseException;
import com.cryptomarket.sdk.models.adapters.OrderBookLevelAdapter;
import com.cryptomarket.sdk.models.adapters.OrderStatusAdapter;
import com.cryptomarket.sdk.models.adapters.OrderTypeAdapter;
import com.cryptomarket.sdk.models.adapters.ReportTypeAdapter;
import com.cryptomarket.sdk.models.adapters.SideAdapter;
import com.cryptomarket.sdk.models.adapters.SubAccountStatusAdapter;
import com.cryptomarket.sdk.models.adapters.UseOffchainAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

public class Adapter {
  private final Moshi moshi = new Moshi.Builder()
      .add(new OrderBookLevelAdapter())
      .add(new OrderStatusAdapter())
      .add(new OrderTypeAdapter())
      .add(new SideAdapter())
      .add(new UseOffchainAdapter())
      .add(new ReportTypeAdapter())
      .add(new SubAccountStatusAdapter())
      .build();

  private ParameterizedType mapStringString = Types.newParameterizedType(Map.class, String.class, Object.class);
  private final JsonAdapter<Map<String, Object>> mapStrStrJsonAdapter = moshi.adapter(mapStringString);

  public <T> String objectToJson(T object, Class<T> cls) throws ParseException {
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::toJson, object);
  }

  public String mapStrStrToJson(Map<String, Object> map) throws ParseException {
    return ConvertingException.ioAndJsonDataAndAssertionToParse(mapStrStrJsonAdapter::toJson, map);
  }

  public <T> T objectFromJson(String json, Class<T> cls) throws ParseException {
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJson, json);
  }

  public <T> List<T> listFromJson(String json, Class<T> cls) throws ParseException {
    Type type = Types.newParameterizedType(List.class, cls);
    JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJson, json);
  }

  public <T> Map<String, List<T>> listMapFromJson(String json, Class<T> cls) throws ParseException {
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
    } catch (IOException | JsonDataException | AssertionError e) {
      throw new ParseException("failed to parse json response", e);
    }
  }

  public <T> Map<String, List<T>> listMapFromObject(Object object, Class<T> cls) throws ParseException {
    Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
    JsonAdapter<Map<String, Object>> mapAdapter = moshi.adapter(type);
    Map<String, Object> objectMap = mapAdapter.fromJsonValue(object);
    type = Types.newParameterizedType(List.class, cls);
    JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);

    Map<String, List<T>> parsed = new HashMap<String, List<T>>();
    try {
      objectMap.forEach((key, value) -> {
        List<T> parsedValue = jsonAdapter.fromJsonValue(value);
        parsed.put(key, parsedValue);
      });
    } catch (AssertionError | JsonDataException e) {
      throw new ParseException("failed to parse json response", e);
    }
    return parsed;
  }

  public <T> Map<String, T> mapFromJson(String json, Class<T> cls) throws ParseException {
    try {
      Type type = Types.newParameterizedType(Map.class, String.class, cls);
      JsonAdapter<Map<String, T>> mapAdapter = moshi.adapter(type);
      return mapAdapter.fromJson(json);
    } catch (IOException | AssertionError | JsonDataException e) {
      throw new ParseException("failed to parse json response", e);
    }
  }

  public <T> T objectFromJsonValue(String json, String key, Class<T> cls) throws ParseException {
    Map<String, Object> objectMap = mapFromJson(json, Object.class);
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    Object value = objectMap.get(key);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);
  }

  public <T> List<T> listFromJsonValue(String json, String key, Class<T> cls) throws ParseException {
    Map<String, Object> objectMap = mapFromJson(json, Object.class);
    Type type = Types.newParameterizedType(List.class, cls);
    JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
    Object value = objectMap.get(key);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);
  }

  public <T> T objectFromValue(Object value, Class<T> cls) throws ParseException {
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);

  }

  public <T> Map<String, T> mapFromValue(Object value, Class<T> cls) throws ParseException {
    Type type = Types.newParameterizedType(Map.class, String.class, cls);
    JsonAdapter<Map<String, T>> mapAdapter = moshi.adapter(type);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(mapAdapter::fromJsonValue, value);
  }

  public <T> List<T> listFromValue(Object value, Class<T> cls) throws ParseException {
    Type type = Types.newParameterizedType(List.class, cls);
    JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);
  }

  public List<String> stringlistFromStringMap(Object value, String key) throws ParseException {
    Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
    JsonAdapter<Map<String, Object>> mapAdapter = moshi.adapter(type);
    Map<String, Object> map = mapAdapter.fromJsonValue(value);
    Object list = map.get(key);
    if (key == null) {
      return Arrays.asList();
    }
    return listFromValue(list, String.class);
  }
}