package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public enum AssociationType {
  @JsonProperty("Denominator")
  DENOMINATOR("Denominator"),

  @JsonProperty("Numerator")
  NUMERATOR("Numerator");

  private final String value;

  AssociationType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.getValue();
  }
}
