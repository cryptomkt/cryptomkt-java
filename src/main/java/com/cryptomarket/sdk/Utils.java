package com.cryptomarket.sdk;

public class Utils {
  public static String fromCamelCaseToCapSnakeCase(String str) {
    StringBuilder builder = new StringBuilder();
    for (char ch : str.toCharArray()) {
      if (Character.isUpperCase(ch)) {
        builder.append("_");
      }
      builder.append(Character.toUpperCase(ch));
    }
    return builder.toString();
  }
}
