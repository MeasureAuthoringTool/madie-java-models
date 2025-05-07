package gov.cms.madie.models.validators;

import org.apache.commons.lang3.StringUtils;

import gov.cms.madie.models.measure.TestCase;
import gov.cms.madie.models.measure.TestCaseValidationStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TestCaseValidationStatusValidatorImpl
    implements ConstraintValidator<TestCaseValidationStatusValidator, TestCase> {

  @Override
  public boolean isValid(TestCase testCase, ConstraintValidatorContext context) {
    if (testCase == null || StringUtils.isEmpty(testCase.getTestCaseValidationStatus())) {
      return true;
    }

    return java.util.Arrays.stream(TestCaseValidationStatus.values())
        .anyMatch(status -> status.toString().equals(testCase.getTestCaseValidationStatus()));
  }
}
