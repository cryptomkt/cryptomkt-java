package com.cryptomarket.sdk;

import java.io.IOException;

import com.cryptomarket.sdk.exceptions.ParseException;
import com.squareup.moshi.JsonDataException;

public class ConvertingException {
  static public <T1, T2> T2 jsonDataToParse(
      Throwing.Specific.Function<T1, T2, JsonDataException> fn,
      T1 arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (JsonDataException e) {
      throw new ParseException("unable to parse", e);
    }
  }

  static public <T1, T2> T2 assertionToParse(
      Throwing.Specific.Function<T1, T2, AssertionError> fn,
      T1 arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (AssertionError e) {
      throw new ParseException("unable to parse", e);
    }
  }

  static public <T1, T2> T2 jsonDataAndAssertionToParse(
      Throwing.Specific.Function2<T1, T2, JsonDataException, AssertionError> fn,
      T1 arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (JsonDataException | AssertionError e) {
      throw new ParseException("unable to parse", e);
    }
  }

  static public <T, R> R ioAndJsonDataAndAssertionToParse(
      Throwing.Specific.Function3<T, R, JsonDataException, AssertionError, IOException> fn,
      T arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (JsonDataException | AssertionError | IOException e) {
      throw new ParseException("unable to parse", e);
    }
  }

  static public <T1, T2> T2 ioToParse(
      Throwing.Specific.Function<T1, T2, IOException> fn,
      T1 arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (IOException e) {
      throw new ParseException("unable to parse", e);
    }
  }
}
