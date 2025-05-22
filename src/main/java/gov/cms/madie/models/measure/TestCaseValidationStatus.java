package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TestCaseValidationStatus {
  @JsonProperty("Valid")
  VALID("Valid"),
  @JsonProperty("Invalid")
  INVALID("Invalid"),
  @JsonProperty("Invalid JSON")
  INVALID_JSON("Invalid JSON"),
  @JsonProperty("Not Complete")
  NOT_COMPLETE("Not Complete"),
  @JsonProperty("Pending")
  PENDING("Pending"),
  @JsonProperty("Validating")
  VALIDATING("Validating");

  private final String text;

  TestCaseValidationStatus(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return this.text;
  }
}
