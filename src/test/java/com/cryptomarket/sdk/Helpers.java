package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.cryptomarket.params.NotificationType;

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

  public static <T> BiConsumer<T, NotificationType> checker(FailChecker failCheck, Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failCheck.fail();
        return;
      }
      System.out.println(notificationType);
      System.out.println(data);
      try {
        checker.accept(data);
      } catch (AssertionError e) {
        failCheck.fail();
      }
    };
  }

  public static <T> BiConsumer<List<T>, NotificationType> listChecker(FailChecker failCheck, Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failCheck.fail();
        return;
      }
      try {
        data.forEach(v -> checker.accept(v));
      } catch (AssertionError e) {
        failCheck.fail();
      }
    };
  }

  public static <T> BiConsumer<Map<String, T>, NotificationType> mapChecker(FailChecker failCheck,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failCheck.fail();
        return;
      }
      try {
        data.forEach((k, v) -> checker.accept(v));
      } catch (AssertionError e) {
        failCheck.fail();
      }
    };
  }

  public static <T> BiConsumer<Map<String, List<T>>, NotificationType> mapListChecker(FailChecker failCheck,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failCheck.fail();
        return;
      }
      try {
        data.forEach((k, v) -> v.forEach(checker));
      } catch (AssertionError e) {
        failCheck.fail();
      }
    };
  }
}
