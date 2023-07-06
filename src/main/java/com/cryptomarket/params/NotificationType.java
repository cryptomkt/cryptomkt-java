package com.cryptomarket.params;

/** Notification type of a websocket subscription */
public enum NotificationType {
  /** Snapshot notification type */
  SNAPSHOT("snapshot"),
  /** Update notification type */
  UPDATE("update"),
  /** Data notification type */
  DATA("data"),
  /** Type used to signal parse erroors */
  PARSE_ERROR("error");

  private final String label;

  private NotificationType(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }

  /** True if it is snapshot */
  public boolean isSnapshot() {
    return this == NotificationType.SNAPSHOT;
  }

  /** True if it is update */
  public boolean isUpdate() {
    return this == NotificationType.UPDATE;
  }

  /** True if it is data */
  public boolean isData() {
    return this == NotificationType.DATA;
  }

  /** True if it is a type of error */
  public boolean isError() {
    return this == NotificationType.PARSE_ERROR;
  }

}
