package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AggregateMethodType {
  @JsonProperty("Average")
  AVERAGE("Average"),
  @JsonProperty("Count")
  COUNT("Count"),
  @JsonProperty("Maximum")
  MAXIMUM("Maximum"),
  @JsonProperty("Median")
  MEDIAN("Median"),
  @JsonProperty("Minimum")
  MINIMUM("Minimum"),
  @JsonProperty("Sum")
  SUM("Sum");

  private final String value;

  AggregateMethodType(String value) {
    this.value = value;
  }

  public static AggregateMethodType fromValue(String value) {
    return Arrays.stream(AggregateMethodType.values())
        .filter(amt -> amt.value.equals(value))
        .findFirst()
        .orElse(null);
  }

  @Override
  public String toString() {
    return this.getValue();
  }
}
