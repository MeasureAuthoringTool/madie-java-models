package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum CompositeMeasureScoring {
  @JsonProperty("All-or-nothing")
  ALL_OR_NOTHING("All-or-nothing"),
  @JsonProperty("Opportunity")
  OPPORTUNITY("Opportunity"),
  @JsonProperty("Linear")
  LINEAR("Linear");

  private final String text;

  CompositeMeasureScoring(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return this.text;
  }

  public static CompositeMeasureScoring valueOfText(String text) {
    return Arrays.stream(CompositeMeasureScoring.values())
        .filter(s -> s.text.equals(text))
        .findFirst()
        .orElse(null);
  }
}
