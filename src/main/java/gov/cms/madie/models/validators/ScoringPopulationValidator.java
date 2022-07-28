package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.PopulationType;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.measure.TestCaseGroupPopulation;
import gov.cms.madie.models.measure.TestCasePopulationValue;
import gov.cms.madie.models.utils.ScoringPopulationDefinition;
import gov.cms.madie.models.measure.MeasureScoring;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScoringPopulationValidator
  implements ConstraintValidator<ValidScoringPopulation, TestCaseGroupPopulation> {

  @Override
  public boolean isValid(
    TestCaseGroupPopulation testCaseGroupPopulation, ConstraintValidatorContext context) {
    if (testCaseGroupPopulation == null) {
      return true;
    }
    if (testCaseGroupPopulation.getScoring() == null
      || testCaseGroupPopulation.getScoring().trim().isEmpty()) {
      return false;
    }

    MeasureScoring scoring = MeasureScoring.valueOfText(testCaseGroupPopulation.getScoring());
    List<TestCasePopulationValue> populationValues = testCaseGroupPopulation.getPopulationValues();
    if (scoring == null || populationValues == null || populationValues.isEmpty()) {
      return false;
    }

    List<PopulationType> requiredPopulations =
      ScoringPopulationDefinition.SCORING_POPULATION_MAP.get(scoring).stream()
        .filter(MeasurePopulationOption::isRequired)
        .map(MeasurePopulationOption::getMeasurePopulation)
        .collect(Collectors.toList());
    List<PopulationType> receivedPopulations =
      populationValues.stream()
        .map(TestCasePopulationValue::getName)
        .distinct()
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    return receivedPopulations.size() >= requiredPopulations.size()
      && receivedPopulations.containsAll(requiredPopulations);
  }
}