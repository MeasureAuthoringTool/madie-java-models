package gov.cms.madie.models.validators;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.PopulationType;
import gov.cms.madie.models.measure.TestCaseGroupPopulation;
import gov.cms.madie.models.measure.TestCasePopulationValue;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TestCasePopulationValueValidator
    implements ConstraintValidator<ValidTestCasePopulationValue, TestCaseGroupPopulation> {

  @Override
  public boolean isValid(
      TestCaseGroupPopulation testCaseGroupPopulation, ConstraintValidatorContext context) {
    if (testCaseGroupPopulation == null
        || CollectionUtils.isEmpty(testCaseGroupPopulation.getPopulationValues())) {
      return true;
    }
    MeasureScoring measureScoring =
        MeasureScoring.valueOfText(testCaseGroupPopulation.getScoring());

    boolean isValid = true;
    switch (measureScoring) {
      case CONTINUOUS_VARIABLE:
        List<TestCasePopulationValue> measurePopulationObservations =
            testCaseGroupPopulation.getPopulationValues().stream()
                .filter(
                    value ->
                        PopulationType.MEASURE_POPULATION_OBSERVATION
                                .name()
                                .equalsIgnoreCase(value.getName().name())
                            && value.getExpected() != null
                            && StringUtils.hasText(value.getExpected().toString())
                            && (!StringUtils.hasText(value.getCriteriaReference())
                                || !StringUtils.hasText(value.getId())))
                .toList();
        isValid = CollectionUtils.isEmpty(measurePopulationObservations);
        break;
      case RATIO:
        List<TestCasePopulationValue> denomAndNumerObservations =
            testCaseGroupPopulation.getPopulationValues().stream()
                .filter(
                    value ->
                        (PopulationType.DENOMINATOR_OBSERVATION
                                    .name()
                                    .equalsIgnoreCase(value.getName().name())
                                || PopulationType.NUMERATOR_OBSERVATION
                                    .name()
                                    .equalsIgnoreCase(value.getName().name()))
                            && value.getExpected() != null
                            && StringUtils.hasText(value.getExpected().toString())
                            && (!StringUtils.hasText(value.getCriteriaReference())
                                || !StringUtils.hasText(value.getId())))
                .toList();
        isValid = CollectionUtils.isEmpty(denomAndNumerObservations);
        break;
      default:
        break;
    }
    return isValid;
  }
}
