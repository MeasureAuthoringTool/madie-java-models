package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.QdmMeasure;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ImprovementNotationValidatorTest {
  private final ImprovementNotationValidator validator = new ImprovementNotationValidator();

  @Mock private ConstraintValidatorContext validatorContext;

  private QdmMeasure measure;

  @BeforeEach
  public void setUp() {
    measure =
        QdmMeasure.builder()
            .id("testId")
            .measureSetId("testMeasureSetId")
            .cqlLibraryName("TestCqlLibraryName")
            .ecqmTitle("testECqm")
            .measureName("testMeasureName")
            .versionId("0.0.000")
            .build();
  }

  @Test
  void testImprovementNotationIncrease() {
    measure.setImprovementNotation("Increased score indicates improvement");
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testImprovementNotationDecrease() {
    measure.setImprovementNotation("Decreased score indicates improvement");
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testImprovementNotationOtherHasDescription() {
    measure.setImprovementNotation("Other");
    measure.setImprovementNotationDescription("desc");
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testImprovementNotationOtherMissingDescription() {
    measure.setImprovementNotation("Other");
    measure.setImprovementNotationDescription("");
    assertFalse(validator.isValid(measure, validatorContext));
  }

  @Test
  void testInvalidImprovementNotation() {
    measure.setImprovementNotation("Invalid");
    assertFalse(validator.isValid(measure, validatorContext));
  }

  @Test
  void testEmptyImprovementNotation() {
    assertTrue(validator.isValid(measure, validatorContext));
  }
}
