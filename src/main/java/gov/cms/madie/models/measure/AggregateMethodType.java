package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

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
  @JsonProperty("Mode")
  MODE("Mode"),
  @JsonProperty("Population Standard Deviation")
  POPULATION_STANDARD_DEVIATION("Population Standard Deviation"),
  @JsonProperty("Population Variance")
  POPULATION_VARIANCE("Population Variance"),
  @JsonProperty("Sample Standard Deviation")
  SAMPLE_STANDARD_DEVIATION("Sample Standard Deviation"),
  @JsonProperty("Sample Variance")
  SAMPLE_VARIANCE("Sample Variance"),
  @JsonProperty("Sum")
  SUM("Sum");

  private final String value;

  AggregateMethodType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.getValue();
  }

}
