package gov.cms.madie.models.utils;

import gov.cms.madie.models.measure.PopulationType;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.measure.MeasureScoring;

import java.util.List;
import java.util.Map;

public interface ScoringPopulationDefinition {
  Map<MeasureScoring, List<MeasurePopulationOption>> SCORING_POPULATION_MAP =
    Map.of(
      MeasureScoring.RATIO,
      List.of(
        new MeasurePopulationOption(PopulationType.INITIAL_POPULATION, true),
        new MeasurePopulationOption(PopulationType.NUMERATOR, true),
        new MeasurePopulationOption(PopulationType.NUMERATOR_OBSERVATION, false),
        new MeasurePopulationOption(PopulationType.NUMERATOR_EXCLUSION, false),
        new MeasurePopulationOption(PopulationType.DENOMINATOR, true),
        new MeasurePopulationOption(PopulationType.DENOMINATOR_OBSERVATION, false),
        new MeasurePopulationOption(PopulationType.DENOMINATOR_EXCLUSION, false)),
      MeasureScoring.PROPORTION,
      List.of(
        new MeasurePopulationOption(PopulationType.INITIAL_POPULATION, true),
        new MeasurePopulationOption(PopulationType.NUMERATOR, true),
        new MeasurePopulationOption(PopulationType.NUMERATOR_EXCLUSION, false),
        new MeasurePopulationOption(PopulationType.DENOMINATOR, true),
        new MeasurePopulationOption(PopulationType.DENOMINATOR_EXCLUSION, false),
        new MeasurePopulationOption(PopulationType.DENOMINATOR_EXCEPTION, false)),
      MeasureScoring.CONTINUOUS_VARIABLE,
      List.of(
        new MeasurePopulationOption(PopulationType.INITIAL_POPULATION, true),
        new MeasurePopulationOption(PopulationType.MEASURE_POPULATION, true),
        new MeasurePopulationOption(PopulationType.MEASURE_POPULATION_EXCLUSION, false),
        new MeasurePopulationOption(PopulationType.MEASURE_POPULATION_OBSERVATION, false)
        ),
      MeasureScoring.COHORT,
      List.of(new MeasurePopulationOption(PopulationType.INITIAL_POPULATION, true)));
}
