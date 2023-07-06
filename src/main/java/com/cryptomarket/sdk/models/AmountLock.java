package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/** Amount lock. amount of user balance locked */
public class AmountLock {
  private Long id;
  private String currency;
  private String amount;
  @Json(name = "date_end")
  private String dateEnd;
  private String description;
  private Boolean canceled;
  @Json(name = "canceled_at")
  private String canceledAt;
  @Json(name = "cancel_description")
  private String cancelDescription;
  @Json(name = "created_at")
  private String createdAt;

  /**
   * Gets the lock id
   *
   * @return An id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the amount lock id
   * @param id An id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the lock currency
   *
   * @return A currency code
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the lock currency
   *
   * @param currency A currency
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the locked amount
   *
   * @return An amount
   */
  public String getAmount() {
    return amount;
  }

  /**
   * Sets the locked amount
   *
   * @param amount An amount
   */
  public void setAmount(String amount) {
    this.amount = amount;
  }

  /**
   * Gets the date and time of lock expiration
   *
   * @return the datetime
   */
  public String getDateEnd() {
    return dateEnd;
  }

  /**
   * Sets the datetime of lock expiration
   *
   * @param dateEnd a datetime
   */
  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  /**
   * Gets the lock description
   *
   * @return A description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the lock description
   *
   * @param description A description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   *
   * @return True if the lock is canceled
   */
  public Boolean isCanceled() {
    return canceled;
  }

  /**
   * Sets the canceled flag
   *
   * @param canceled A boolean
   */
  public void setCanceled(Boolean canceled) {
    this.canceled = canceled;
  }

  /**
   * Gets the datetime of cancelation
   *
   * @return A datetime
   */
  public String getCanceledAt() {
    return canceledAt;
  }

  /**
   * Sets the datetime of canceltion
   *
   * @param canceledAt a datetime
   */
  public void setCanceledAt(String canceledAt) {
    this.canceledAt = canceledAt;
  }

  /**
   * Gets the cancelation description
   *
   * @return A description
   */
  public String getCancelDescription() {
    return cancelDescription;
  }

  /**
   * Sets the cancelation description
   *
   * @param cancelDescription A description
   */
  public void setCancelDescription(String cancelDescription) {
    this.cancelDescription = cancelDescription;
  }

  /**
   * Gets the created at datetime
   *
   * @return A datetime
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the created at datetime
   *
   * @param createdAt
   */
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
