package gov.cms.madie.models.measure;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BaseConfigurationTypes {
  @JsonProperty("Appropriate Use Process")
  APPROPRIATE_USE_PROCESS("Appropriate Use Process"),
  @JsonProperty("Resource Use")
  RESOURCE_USE("Resource Use"),
  @JsonProperty("Efficiency")
  EFFICIENCY("Efficiency"),
  @JsonProperty("Intermediate Clinical Outcome")
  INTERMEDIATE_CLINICAL_OUTCOME("Intermediate Clinical Outcome"),
  @JsonProperty("Intermediate Outcome")
  INTERMEDIATE_OUTCOME("Intermediate Outcome"),
  @JsonProperty("Outcome")
  OUTCOME("Outcome"),
  @JsonProperty("Experience")
  EXPERIENCE("Experience"),
  @JsonProperty("Patient Reported Outcome")
  PATIENT_REPORTED_OUTCOME("Patient Reported Outcome"),
  @JsonProperty("Performance")
  PERFORMANCE("Performance"),
  @JsonProperty("Patient Reported Outcome Performance")
  PATIENT_REPORTED_OUTCOME_PERFORMANCE("Patient Reported Outcome Performance"),
  @JsonProperty("Process")
  PROCESS("Process"),
  @JsonProperty("Structure")
  STRUCTURE("Structure");

  private final String text;

  BaseConfigurationTypes(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return this.text;
  }

  public static BaseConfigurationTypes valueOfText(String text) {
    return Arrays.stream(BaseConfigurationTypes.values())
        .filter(s -> s.text.equals(text))
        .findFirst()
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    "No enum constant "
                        + BaseConfigurationTypes.class.getCanonicalName()
                        + "."
                        + text));
  }
}
