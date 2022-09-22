package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.PopulationType;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.measure.TestCaseGroupPopulation;
import gov.cms.madie.models.measure.TestCasePopulationValue;
import gov.cms.madie.models.utils.ScoringPopulationDefinition;
import gov.cms.madie.models.measure.MeasureScoring;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
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
    final String popBasis = testCaseGroupPopulation.getPopulationBasis();

    log.info("testCaseGroupPop: {}, popBasis: {}", testCaseGroupPopulation.getScoring(), popBasis);
    boolean allValuesMatchPopulationBasis = popBasis == null || populationValues.stream().allMatch(popVal -> {
      if (popVal == null || popVal.getExpected() == null) {
        log.info("popVal {} missing", popVal);
        return true;
      } else if ("Boolean".equals(popBasis)) {
        var output = popVal.getExpected() instanceof Boolean;
        log.info("popVal.expected {} validating boolean result: {}", popVal.getExpected(), output);
        return popVal.getExpected() instanceof Boolean;
      } else if (popVal.getExpected().toString().isEmpty()) {
        log.info("popVal.expected {} non-boolean and empty", popVal.getExpected());
        return true;
      } else {
        log.info("popVal.expected {} validating numeric", popVal.getExpected());
        try {
          Integer.parseInt(popVal.getExpected().toString());
          return true;
        } catch (Exception ex) {
          log.info("popVal.expected {} failed numeric check", popVal.getExpected());
          return false;
        }
      }
    });
    return receivedPopulations.size() >= requiredPopulations.size()
      && receivedPopulations.containsAll(requiredPopulations)
        && allValuesMatchPopulationBasis;
  }
}
