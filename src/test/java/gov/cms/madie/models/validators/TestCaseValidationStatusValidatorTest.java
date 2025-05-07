package gov.cms.madie.models.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import gov.cms.madie.models.measure.TestCase;
import gov.cms.madie.models.measure.TestCaseValidationStatus;
import jakarta.validation.ConstraintValidatorContext;

@ExtendWith(SpringExtension.class)
public class TestCaseValidationStatusValidatorTest {

  private final TestCaseValidationStatusValidatorImpl validator =
      new TestCaseValidationStatusValidatorImpl();

  @Mock private ConstraintValidatorContext validatorContext;

  private TestCase testCase;

  @BeforeEach
  public void setUp() {
    testCase = TestCase.builder().build();
  }

  @Test
  public void testValidatorReturnsTrueForNulTestCase() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForNullTestCaseValidationStatus() {
    boolean output = validator.isValid(testCase, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForValidStatus() {
    testCase.setTestCaseValidationStatus(TestCaseValidationStatus.PENDING.toString());
    boolean output = validator.isValid(testCase, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseForInvalidStatus() {
    testCase.setTestCaseValidationStatus("invalid status");
    boolean output = validator.isValid(testCase, validatorContext);
    assertFalse(output);
  }
}
