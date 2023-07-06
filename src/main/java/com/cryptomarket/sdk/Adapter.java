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

/**
 * Json Adapter, uses moshi
 */
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

  /**
   * Converts an object to its json representation
   *
   * @param <T>    Class type of the object
   * @param object The object to convert to json
   * @param cls    Class of the object
   * @return A json string representatiton of the object
   * @throws ParseException If conversion fails
   */
  public <T> String objectToJson(T object, Class<T> cls) throws ParseException {
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::toJson, object);
  }

  /**
   * Converts a Map(String, String) to json
   *
   * @param map The map to convert
   * @return The json version of the map
   * @throws ParseException If conversion fails
   */
  public String mapStrStrToJson(Map<String, Object> map) throws ParseException {
    return ConvertingException.ioAndJsonDataAndAssertionToParse(mapStrStrJsonAdapter::toJson, map);
  }

  /**
   * Converts json string to a java class
   *
   * @param <T>  The java class type for conversion
   * @param json The json representation of the class
   * @param cls  The class to convert to
   * @return A new object of the class
   * @throws ParseException If conversion fails
   */
  public <T> T objectFromJson(String json, Class<T> cls) throws ParseException {
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJson, json);
  }

  /**
   * Converts a json to a list of java classes
   *
   * @param <T>  The class type for conversion
   * @param json The json with the list
   * @param cls  The class to convert the elements of the lists
   * @return A lists of new objects
   * @throws ParseException If conversion fails
   */
  public <T> List<T> listFromJson(String json, Class<T> cls) throws ParseException {
    Type type = Types.newParameterizedType(List.class, cls);
    JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJson, json);
  }

  /**
   * Converts a json string to a Map(String, List(T)), with T a java class
   *
   * @param <T>  The class type for conversion
   * @param json The json with the map of lists
   * @param cls  The class to convert the elements of the lists of the map
   * @return A Map of lists of new objects
   * @throws ParseException If conversion fails
   */
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

  /**
   * Converts a json object to a Map(String, List(T))
   *
   * @param <T>    The class type to use for conversion
   * @param object The json object with the map of lists
   * @param cls    The class to converts the elements of the lists of the map
   * @return A map of lists of new objects
   * @throws ParseException If conversion fails
   */
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

  /**
   * Converts a json String to a Map(String, T)
   *
   * @param <T>  The class type to use for conversion
   * @param json The json object with the map of objects
   * @param cls  The class to convert the elements of map
   * @return A map of new objects
   * @throws ParseException If conversion fails
   */
  public <T> Map<String, T> mapFromJson(String json, Class<T> cls) throws ParseException {
    try {
      Type type = Types.newParameterizedType(Map.class, String.class, cls);
      JsonAdapter<Map<String, T>> mapAdapter = moshi.adapter(type);
      return mapAdapter.fromJson(json);
    } catch (IOException | AssertionError | JsonDataException e) {
      throw new ParseException("failed to parse json response", e);
    }
  }

  /**
   * converts and returns an entry from a json string map
   *
   * @param <T>  The class type to convert the json entry
   * @param json The json string map with the expected object
   * @param key  The key of the entry to convert
   * @param cls  The class to convert the json entry
   * @return A new object
   * @throws ParseException If conversion fails
   */
  public <T> T objectFromJsonValue(String json, String key, Class<T> cls) throws ParseException {
    Map<String, Object> objectMap = mapFromJson(json, Object.class);
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    Object value = objectMap.get(key);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);
  }

  /**
   * converts a json String map value to a List(T)
   *
   * @param <T>  The class type of the elements of the list
   * @param json The json String map with the entry to convert
   * @param key  The key of the entry to convert
   * @param cls  The class to convert list elements
   * @return A list of new objects
   * @throws ParseException IF conversion fails
   */
  public <T> List<T> listFromJsonValue(String json, String key, Class<T> cls) throws ParseException {
    Map<String, Object> objectMap = mapFromJson(json, Object.class);
    Type type = Types.newParameterizedType(List.class, cls);
    JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
    Object value = objectMap.get(key);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);
  }

  /**
   * Convert a json object to convert to a java class T
   *
   * @param <T>   The class type of the object
   * @param value The json object to convert
   * @param cls   The class to convert the object
   * @return A new object
   * @throws ParseException If conversion fails
   */
  public <T> T objectFromValue(Object value, Class<T> cls) throws ParseException {
    JsonAdapter<T> jsonAdapter = moshi.adapter(cls);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);

  }

  /**
   * Converts a json object to a Map(String, T)
   *
   * @param <T>   The class type of the map values
   * @param value The json object to convert
   * @param cls   The class of the map values
   * @return A map of new objects
   * @throws ParseException If conversion fails
   */
  public <T> Map<String, T> mapFromValue(Object value, Class<T> cls) throws ParseException {
    Type type = Types.newParameterizedType(Map.class, String.class, cls);
    JsonAdapter<Map<String, T>> mapAdapter = moshi.adapter(type);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(mapAdapter::fromJsonValue, value);
  }

  /**
   * Converts a json object list to a List(T)
   *
   * @param <T>   The class type of list elements
   * @param value The json object to convert
   * @param cls   The class of list elements
   * @return A list of new objects
   * @throws ParseException If conversion fails
   */
  public <T> List<T> listFromValue(Object value, Class<T> cls) throws ParseException {
    Type type = Types.newParameterizedType(List.class, cls);
    JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
    return ConvertingException.ioAndJsonDataAndAssertionToParse(jsonAdapter::fromJsonValue, value);
  }

  /**
   * Converts a json object entry to a List(String)
   *
   * @param value The json object map with the entry to convert
   * @param key   The key of the value to convert
   * @return A list of strings
   * @throws ParseException If conversion fails
   */
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
