package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class AmountLock {
  private String id;
  private String currency;
  private String amount;
  @Json(name = "date_end")
  private String dateEnd;
  private String description;
  private String canceled;
  @Json(name = "canceled_at")
  private String canceledAt;
  @Json(name = "cancel_description")
  private String cancelDescription;
  @Json(name = "created_at")
  private String createdAt;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCanceled() {
    return canceled;
  }

  public void setCanceled(String cancelled) {
    this.canceled = cancelled;
  }

  public String getCanceledAt() {
    return canceledAt;
  }

  public void setCanceledAt(String cancelledAt) {
    this.canceledAt = cancelledAt;
  }

  public String getCancelDescription() {
    return cancelDescription;
  }

  public void setCancelDescription(String cancelDescription) {
    this.cancelDescription = cancelDescription;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "AmountLock [id=" + id + ", amount=" + amount + ", cancelDescription=" + cancelDescription + ", cancelled="
        + canceled + ", cancelledAt=" + canceledAt + ", createdAt=" + createdAt + ", currency=" + currency
        + ", dateEnd=" + dateEnd + ", description=" + description + "]";
  }
}
