package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class CommitRisk {
  private Integer score;
  private Boolean rbf;
  @Json(name = "low_fee")
  private Boolean lowFee;

  /**
   * Gets the risk score of the associated transaction. For more information see
   * the
   * api docs at
   * https://api.exchange.cryptomkt.com/#get-transactions-history.
   *
   * @return
   */
  public Integer getScore() {
    return score;
  }

  /**
   * Sets the risk score of the associated transaction
   *
   * @param score
   */
  public void setScore(Integer score) {
    this.score = score;
  }

  /**
   * Gets a flag indicating if the associated transaction can be replaced by an
   * aditional fee (replace-by-fee). For more information see the api docs at
   * https://api.exchange.cryptomkt.com/#get-transactions-history.
   *
   * @return
   */
  public Boolean getRbf() {
    return rbf;
  }

  /**
   * Sets the replace-by-fee flag of the associated transaction
   *
   * @param rbf
   */
  public void setRbf(Boolean rbf) {
    this.rbf = rbf;
  }

  /**
   * Gets a flag indicating whether the actual network fee is lower than the
   * estimated one.
   * For more information see the api docs at.
   * https://api.exchange.cryptomkt.com/#get-transactions-history
   *
   * @return
   */
  public Boolean getLowFee() {
    return lowFee;
  }

  /**
   * Sets the low fee flag
   *
   * @param lowFee
   */
  public void setLowFee(Boolean lowFee) {
    this.lowFee = lowFee;
  }

  @Override
  public String toString() {
    return "CommitRisk [source=" + score + ", rbg=" + rbf + ", lowFee=" + lowFee + "]";
  }
}
