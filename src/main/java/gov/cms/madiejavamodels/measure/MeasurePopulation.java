package gov.cms.madiejavamodels.measure;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MeasurePopulation {
  @JsonProperty("initialPopulation")
  INITIAL_POPULATION,
  @JsonProperty("numerator")
  NUMERATOR,
  @JsonProperty("numeratorExclusion")
  NUMERATOR_EXCLUSION,
  @JsonProperty("denominator")
  DENOMINATOR,
  @JsonProperty("denominatorExclusion")
  DENOMINATOR_EXCLUSION,
  @JsonProperty("denominatorException")
  DENOMINATOR_EXCEPTION,
  @JsonProperty("measurePopulation")
  MEASURE_POPULATION,
  @JsonProperty("measurePopulationExclusion")
  MEASURE_POPULATION_EXCLUSION,
  @JsonProperty("measureObservation")
  MEASURE_OBSERVATION;

  public String toCode() {
    switch (this) {
      case INITIAL_POPULATION: return "initial-population";
      case NUMERATOR: return "numerator";
      case NUMERATOR_EXCLUSION: return "numerator-exclusion";
      case DENOMINATOR: return "denominator";
      case DENOMINATOR_EXCLUSION: return "denominator-exclusion";
      case DENOMINATOR_EXCEPTION: return "denominator-exception";
      case MEASURE_POPULATION: return "measure-population";
      case MEASURE_POPULATION_EXCLUSION: return "measure-population-exclusion";
      case MEASURE_OBSERVATION: return "measure-observation";
      default: return null;
    }
  }

  public String getDisplay() {
    switch (this) {
      case INITIAL_POPULATION:
        return "Initial Population";
      case NUMERATOR:
        return "Numerator";
      case NUMERATOR_EXCLUSION:
        return "Numerator Exclusion";
      case DENOMINATOR:
        return "Denominator";
      case DENOMINATOR_EXCLUSION:
        return "Denominator Exclusion";
      case DENOMINATOR_EXCEPTION:
        return "Denominator Exception";
      case MEASURE_POPULATION:
        return "Measure Population";
      case MEASURE_POPULATION_EXCLUSION:
        return "Measure Population Exclusion";
      case MEASURE_OBSERVATION:
        return "Measure Observation";
      default:
        return null;
    }
  }
}
