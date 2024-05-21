package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum MeasureGroupTypes {
  @JsonProperty("Outcome")
  OUTCOME("Outcome"),

  @JsonProperty("Patient Reported Outcome")
  PATIENT_REPORTED_OUTCOME("Patient Reported Outcome"),

  @JsonProperty("Process")
  PROCESS("Process"),

  @JsonProperty("Structure")
  STRUCTURE("Structure");

  private final String value;

  MeasureGroupTypes(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.getValue();
  }
}
