package com.cryptomarket.sdk;

import java.io.IOException;

import com.cryptomarket.sdk.exceptions.ParseException;
import com.squareup.moshi.JsonDataException;

/**
 * Converts exceptions
 */
public class ConvertingException {
  /**
   * calls a function that may throw a json data exception and converts it to a
   * parse exception
   *
   * @param <T> The argument type of the function
   * @param <R> The return type of the function
   * @param fn  The function
   * @param arg The argument of the function
   * @return The result of calling the function with the argument
   * @throws ParseException if a json data exception was thrown
   */
  static public <T, R> R jsonDataToParse(
      Throwing.Specific.Function<T, R, JsonDataException> fn,
      T arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (JsonDataException e) {
      throw new ParseException("unable to parse", e);
    }
  }

  /**
   * calls a function that may throw an assertion error and converts it to a
   * parse exception
   *
   * @param <T> The argument type of the function
   * @param <R> The return type of the function
   * @param fn  The function
   * @param arg The argument of the function
   * @return The result of calling the function with the argument
   * @throws ParseException if an assertion error was thrown
   */
  static public <T, R> R assertionToParse(
      Throwing.Specific.Function<T, R, AssertionError> fn,
      T arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (AssertionError e) {
      throw new ParseException("unable to parse", e);
    }
  }

  /**
   * calls a function that may throw a json data exception or an assertion error
   * and converts it to a parse exception
   *
   * @param <T> The argument type of the function
   * @param <R> The return type of the function
   * @param fn  The function
   * @param arg The argument of the function
   * @return The result of calling the function with the argument
   * @throws ParseException if a json data exception or an assertion error was
   *                        thrown
   */
  static public <T, R> R jsonDataAndAssertionToParse(
      Throwing.Specific.Function2<T, R, JsonDataException, AssertionError> fn,
      T arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (JsonDataException | AssertionError e) {
      throw new ParseException("unable to parse", e);
    }
  }

  /**
   * calls a function that may throw a json data exception or an assertion error
   * or an IO exception and converts it to a parse exception
   *
   * @param <T> The argument type of the function
   * @param <R> The return type of the function
   * @param fn  The function
   * @param arg The argument of the function
   * @return The result of calling the function with the argument
   * @throws ParseException if a json data exception or an assertion error or an
   *                        IO exception was thrown
   */
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

  /**
   * calls a function that may throw an IO exception and converts it to a
   * parse exception
   *
   * @param <T> The argument type of the function
   * @param <R> The return type of the function
   * @param fn  The function
   * @param arg The argument of the function
   * @return The result of calling the function with the argument
   * @throws ParseException if an IO exception was thrown
   */
  static public <T, R> R ioToParse(
      Throwing.Specific.Function<T, R, IOException> fn,
      T arg)
      throws ParseException {
    try {
      return fn.apply(arg);
    } catch (IOException e) {
      throw new ParseException("unable to parse", e);
    }
  }
}
