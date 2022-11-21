package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class AmountLock {
  @Json(name = "id")
  private String ID;
  private String currency;
  private String amount;
  @Json(name = "date_end")
  private String dateEnd;
  private String description;
  private String cancelled;
  @Json(name = "cancelled_at")
  private String cancelledAt;
  @Json(name = "cancel_description")
  private String cancelDescription;
  @Json(name = "created_at")
  private String createdAt;

  public String getID() {
    return ID;
  }

  public void setID(String iD) {
    ID = iD;
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

  public String getCancelled() {
    return cancelled;
  }

  public void setCancelled(String cancelled) {
    this.cancelled = cancelled;
  }

  public String getCancelledAt() {
    return cancelledAt;
  }

  public void setCancelledAt(String cancelledAt) {
    this.cancelledAt = cancelledAt;
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
    return "AmountLock [ID=" + ID + ", amount=" + amount + ", cancelDescription=" + cancelDescription + ", cancelled="
        + cancelled + ", cancelledAt=" + cancelledAt + ", createdAt=" + createdAt + ", currency=" + currency
        + ", dateEnd=" + dateEnd + ", description=" + description + "]";
  }
}
