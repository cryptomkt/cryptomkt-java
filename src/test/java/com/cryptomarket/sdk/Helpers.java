package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private Optional<String> errMsg;

    public void fail(String errMsg) {
      this.errMsg = Optional.of(errMsg);
    }

    public Boolean failed() {
      return errMsg.isPresent();
    }

    public Optional<String> getErrMsg() {
      return errMsg;
    }
  }

  public static <T> BiConsumer<T, NotificationType> checker(FailChecker failChecker, Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail("failed to parse notification");
        return;
      }
      try {
        checker.accept(data);
      } catch (AssertionError e) {
        failChecker.fail(e.getMessage());
      }
    };
  }

  public static <T> BiConsumer<T, CryptomarketSDKException> objectAndExceptionChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, error) -> {
      if (error != null) {
        failChecker.fail(error.getMessage());
      }
      try {
        checker.accept(data);
      } catch (AssertionError e) {
        failChecker.fail(e.getMessage());
      }
    };
  }

  public static <T> BiConsumer<List<T>, CryptomarketSDKException> listAndExceptionChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, error) -> {
      if (error != null) {
        failChecker.fail(error.getMessage());
      }
      try {
        data.forEach(v -> checker.accept(v));
      } catch (AssertionError e) {
        failChecker.fail(e.getMessage());
      }
    };
  }

  public static <T> BiConsumer<List<T>, NotificationType> notificationListChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail("invalid notification type");
        return;
      }
      try {
        data.forEach(v -> checker.accept(v));
      } catch (AssertionError e) {
        failChecker.fail(e.getMessage());
      }
    };
  }

  public static <T> BiConsumer<Map<String, T>, NotificationType> notificationMapChecker(FailChecker failChecker,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail("invalid notification type");
        return;
      }
      try {
        data.forEach((k, v) -> checker.accept(v));
      } catch (AssertionError e) {
        failChecker.fail(e.getMessage());
      }
    };
  }

  public static <T> BiConsumer<Map<String, List<T>>, NotificationType> notificationMapListChecker(
      FailChecker failChecker,
      Consumer<T> checker) {
    return (data, notificationType) -> {
      if (notificationType.isError()) {
        failChecker.fail("invalid notification type");
        return;
      }
      try {
        data.forEach((k, v) -> v.forEach(checker));
      } catch (AssertionError e) {
        failChecker.fail(e.getMessage());
      }
    };
  }
}
