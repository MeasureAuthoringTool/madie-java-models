package gov.cms.madie.models.measure;

import java.util.Arrays;

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
  PENDING("Pending");

  private final String text;

  TestCaseValidationStatus(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return this.text;
  }

  public static TestCaseValidationStatus valueOfText(String text) {
    return Arrays.stream(TestCaseValidationStatus.values())
        .filter(s -> s.text.equals(text))
        .findFirst()
        .orElse(null);
  }
}
