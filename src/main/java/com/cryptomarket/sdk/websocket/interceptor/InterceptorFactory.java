package com.cryptomarket.sdk.websocket.interceptor;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import com.cryptomarket.params.NotificationType;
import com.cryptomarket.sdk.exceptions.CryptomarketAPIException;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.exceptions.ParseException;
import com.cryptomarket.sdk.models.ErrorBody;
import com.cryptomarket.sdk.models.WSJsonResponse;

public class InterceptorFactory {
  public static <T> Interceptor newOfWSResponseObject(BiConsumer<T, CryptomarketSDKException> resultBiConsumer,
      Class<T> cls) {
    return new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        ErrorBody error = response.getError();
        if (error != null) {
          resultBiConsumer.accept(null, new CryptomarketAPIException(error));
          return;
        }
        T result;
        try {
          result = adapter.objectFromValue(response.getResult(), cls);
        } catch (ParseException e) {
          resultBiConsumer.accept(null, e);
          return;
        }
        resultBiConsumer.accept(result, null);
      }
    };

  }

  public static <T> Interceptor newMapStringListOfChanneledWSResponseObject(
      BiConsumer<Map<String, List<T>>, NotificationType> notificationBiConsumer,
      Class<T> cls) {
    return new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        try {
          if (response.getSnapshot() != null) {
            Map<String, List<T>> data = adapter.listMapFromObject(response.getSnapshot(), cls);
            notificationBiConsumer.accept(data, NotificationType.SNAPSHOT);
          } else if (response.getUpdate() != null) {
            Map<String, List<T>> data = adapter.listMapFromObject(response.getUpdate(), cls);
            notificationBiConsumer.accept(data, NotificationType.UPDATE);
          } else {
            Map<String, List<T>> data = adapter.listMapFromObject(response.getData(), cls);
            notificationBiConsumer.accept(data, NotificationType.DATA);
          }
        } catch (ParseException e) {
          notificationBiConsumer.accept(null, NotificationType.PARSE_ERROR);
        }
      }
    };

  }

  public static <T> Interceptor newOfChanneledWSResponse(
      BiConsumer<Map<String, T>, NotificationType> notificationBiConsumer,
      Class<T> cls) {
    return new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        try {
          if (response.getSnapshot() != null) {
            Map<String, T> data = adapter.mapFromValue(response.getSnapshot(), cls);
            notificationBiConsumer.accept(data, NotificationType.SNAPSHOT);
          } else if (response.getUpdate() != null) {
            Map<String, T> data = adapter.mapFromValue(response.getUpdate(), cls);
            notificationBiConsumer.accept(data, NotificationType.UPDATE);
          } else {
            Map<String, T> data = adapter.mapFromValue(response.getData(), cls);
            notificationBiConsumer.accept(data, NotificationType.DATA);
          }
        } catch (ParseException e) {
          notificationBiConsumer.accept(null, NotificationType.PARSE_ERROR);
        }
      }

    };
  }

  public static <T> Interceptor newOfWSResponseList(BiConsumer<List<T>, CryptomarketSDKException> resultBiConsumer,
      Class<T> cls) {
    return new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        ErrorBody error = response.getError();
        if (error != null) {
          resultBiConsumer.accept(null, new CryptomarketAPIException(error));
          return;
        }
        List<T> result;
        try {
          result = adapter.listFromValue(response.getResult(), cls);
        } catch (ParseException e) {
          resultBiConsumer.accept(null, e);
          return;
        }
        resultBiConsumer.accept(result, null);
      }
    };
  }

  public static Interceptor newOfSubscriptionResponse(
      BiConsumer<List<String>, CryptomarketSDKException> resultBiConsumer) {
    return new Interceptor() {
      @Override
      public void makeCall(WSJsonResponse response) {
        ErrorBody error = response.getError();
        if (error != null) {
          resultBiConsumer.accept(null, new CryptomarketAPIException(error));
          return;
        }
        List<String> result;
        try {
          result = adapter.stringlistFromStringMap(response.getResult(), "subscriptions");
        } catch (ParseException e) {
          resultBiConsumer.accept(null, e);
          return;
        }
        resultBiConsumer.accept(result, null);
      }
    };
  }
}
