package gov.cms.madie.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ImprovementNotation {

  @JsonProperty("Increased score indicates improvement")
  INCREASED_SCORE_INDICATES_IMPROVEMENT("Increased score indicates improvement"),

  @JsonProperty("Decreased score indicates improvement")
  DECREASED_SCORE_INDICATES_IMPROVEMENT("Decreased score indicates improvement");

  private final String value;

  ImprovementNotation(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}