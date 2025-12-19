package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.CompositeMeasureScoring;
import gov.cms.madie.models.measure.MeasureScoring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintValidatorContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class EnumValidatorImplTest {
  private EnumValidatorImpl validator;
  private ConstraintValidatorContext context;

  @BeforeEach
  public void setUp() {
    validator = new EnumValidatorImpl();
    context = mock(ConstraintValidatorContext.class);
  }

  @Test
  public void testValidMeasureScoringValues() {

    AnnotationAttributes attrs = new AnnotationAttributes();
    attrs.put("enumClass", MeasureScoring.class);
    attrs.put("message", "Scoring must be a valid MADiE scoring type");

    EnumValidator annotation =
        AnnotationUtils.synthesizeAnnotation(attrs, EnumValidator.class, null);

    validator.initialize(annotation);
    assertTrue(validator.isValid("Cohort", context));
    assertTrue(validator.isValid("Continuous Variable", context));
    assertTrue(validator.isValid("Proportion", context));
    assertTrue(validator.isValid("Ratio", context));
    assertTrue(validator.isValid("Composite", context));
    assertFalse(validator.isValid("InvalidScoring", context));
    assertFalse(validator.isValid(null, context));
  }

  @Test
  public void testValidCompositeMeasureScoringValues() {
    AnnotationAttributes attrs = new AnnotationAttributes();
    attrs.put("enumClass", CompositeMeasureScoring.class);
    attrs.put("message", "Composite Scoring must be a valid MADiE Composite scoring type");

    EnumValidator annotation =
        AnnotationUtils.synthesizeAnnotation(attrs, EnumValidator.class, null);

    validator.initialize(annotation);
    assertTrue(validator.isValid("All-or-nothing", context));
    assertTrue(validator.isValid("Opportunity", context));
    assertTrue(validator.isValid("Linear", context));
    assertFalse(validator.isValid("InvalidComposite", context));
    assertFalse(validator.isValid(null, context));
  }

  @Test
  public void testAllowNullTrueAcceptsNull() {
    AnnotationAttributes attrs = new AnnotationAttributes();
    attrs.put("enumClass", CompositeMeasureScoring.class);
    attrs.put("message", "Composite Scoring must be a valid MADiE Composite scoring type");
    attrs.put("allowNull", true);
    EnumValidator annotation =
        AnnotationUtils.synthesizeAnnotation(attrs, EnumValidator.class, null);
    validator.initialize(annotation);
    assertTrue(validator.isValid(null, context));
    assertTrue(validator.isValid("All-or-nothing", context));
    assertTrue(validator.isValid("Opportunity", context));
  }

  @Test
  public void testAllowNullFalseRejectsNull() {
    AnnotationAttributes attrs = new AnnotationAttributes();
    attrs.put("enumClass", CompositeMeasureScoring.class);
    attrs.put("message", "Composite Scoring must be a valid MADiE Composite scoring type");
    attrs.put("allowNull", false);
    EnumValidator annotation =
        AnnotationUtils.synthesizeAnnotation(attrs, EnumValidator.class, null);
    validator.initialize(annotation);
    assertFalse(validator.isValid(null, context));
    assertTrue(validator.isValid("All-or-nothing", context));
    assertTrue(validator.isValid("Opportunity", context));
  }
}
