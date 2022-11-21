package com.cryptomarket.params;

public enum NotificationType {
  SNAPSHOT("snapshot"),
  UPDATE("update"),
  DATA("data");

  private final String label;

  private NotificationType(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }

  public boolean isSnapshot() {
    return this == NotificationType.SNAPSHOT;
  }

  public boolean isUpdate() {
    return this == NotificationType.UPDATE;
  }

  public boolean isData() {
    return this == NotificationType.DATA;
  }

}