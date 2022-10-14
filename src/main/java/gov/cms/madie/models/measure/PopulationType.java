package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum PopulationType {
  @JsonProperty("initialPopulation")
  INITIAL_POPULATION("initial-population", "Initial Population"),
  @JsonProperty("numerator")
  NUMERATOR("numerator","Numerator"),
  @JsonProperty("numeratorExclusion")
  NUMERATOR_EXCLUSION("numerator-exclusion","Numerator Exclusion"),
  @JsonProperty("denominator")
  DENOMINATOR("denominator","Denominator"),
  @JsonProperty("denominatorExclusion")
  DENOMINATOR_EXCLUSION("denominator-exclusion","Denominator Exclusion"),
  @JsonProperty("denominatorException")
  DENOMINATOR_EXCEPTION("denominator-exception","Denominator Exception"),
  @JsonProperty("measurePopulation")
  MEASURE_POPULATION("measure-population","Measure Population"),
  @JsonProperty("measurePopulationExclusion")
  MEASURE_POPULATION_EXCLUSION("measure-population-exclusion","Measure Population Exclusion"),
  @JsonProperty("measureObservation")
  MEASURE_OBSERVATION("measure-observation","Measure Observation");

  final private String code;
  final private String display;

  PopulationType(String code, String display) {
    this.code = code;
    this.display = display;
  }

  public String toCode() {
    return code;
  }

  public String getDisplay() {
    return display;
  }

  public static PopulationType fromDisplay(final String display){
    return Arrays.stream(PopulationType.values()).filter(pt -> pt.display.equals(display)).findFirst().orElse(null);
  }

  public static PopulationType fromCode(final String code){
    return Arrays.stream(PopulationType.values()).filter(pt -> pt.code.equals(code)).findFirst().orElse(null);
  }


}
