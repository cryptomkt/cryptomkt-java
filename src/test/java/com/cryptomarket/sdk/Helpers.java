package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.cryptomarket.params.NotificationType;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;

public class Helpers {
  public static void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      fail();
    }
  }

  public static class FailChecker {
    private Boolean _failed = false;

    public void fail() {
      _failed = true;
    }

    public Boolean failed() {
      return _failed;
    }
  }

  public static <T> BiConsumer<T, NotificationType> checker(FailChecker failChecker, Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail();
        return;
      }
      try {
        checker.accept(data);
      } catch (AssertionError e) {
        failChecker.fail();
      }
    };
  }

  public static <T> BiConsumer<T, CryptomarketSDKException> objectAndExceptionChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, error) -> {
      if (error != null) {
        failChecker.fail();
      }
      try {
        checker.accept(data);
      } catch (AssertionError e) {
        failChecker.fail();
      }
    };
  }

  public static <T> BiConsumer<List<T>, CryptomarketSDKException> listAndExceptionChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, error) -> {
      if (error != null) {
        failChecker.fail();
      }
      try {
        data.forEach(v -> checker.accept(v));
      } catch (AssertionError e) {
        failChecker.fail();
      }
    };
  }

  public static <T> BiConsumer<List<T>, NotificationType> notificationListChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail();
        return;
      }
      try {
        data.forEach(v -> checker.accept(v));
      } catch (AssertionError e) {
        failChecker.fail();
      }
    };
  }

  public static <T> BiConsumer<Map<String, T>, NotificationType> notificationMapChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail();
        return;
      }
      try {
        data.forEach((k, v) -> checker.accept(v));
      } catch (AssertionError e) {
        failChecker.fail();
      }
    };
  }

  public static <T> BiConsumer<Map<String, List<T>>, NotificationType> notificationMapListChecker(
      FailChecker failChecker,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail();
        return;
      }
      try {
        data.forEach((k, v) -> v.forEach(checker));
      } catch (AssertionError e) {
        failChecker.fail();
      }
    };
  }
}
