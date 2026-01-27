package gov.cms.madie.models.validators;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.MeasureMetaData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ValidCompositeValidatorTest {
  private ValidCompositeValidator validator;
  private ConstraintValidatorContext context;

  @BeforeEach
  public void setUp() {
    validator = new ValidCompositeValidator();
    context = mock(ConstraintValidatorContext.class);
  }

  @Test
  public void testNullMeasureIsValid() {
    assertTrue(validator.isValid(null, context));
  }

  @Test
  public void testNullModelOrMetaDataIsValid() {
    Measure measure = new Measure();
    measure.setModel(null);
    measure.setMeasureMetaData(null);
    assertTrue(validator.isValid(measure, context));
  }

  @Test
  public void testQdm56CompositeIsInvalid() {
    Measure measure = new Measure();
    measure.setModel(ModelType.QDM_5_6.getValue());
    MeasureMetaData metaData = new MeasureMetaData();
    metaData.setComposite(true);
    measure.setMeasureMetaData(metaData);
    assertFalse(validator.isValid(measure, context));
  }

  @Test
  public void testQdm56NonCompositeIsValid() {
    Measure measure = new Measure();
    measure.setModel(ModelType.QDM_5_6.getValue());
    MeasureMetaData metaData = new MeasureMetaData();
    metaData.setComposite(false);
    measure.setMeasureMetaData(metaData);
    assertTrue(validator.isValid(measure, context));
  }

  @Test
  public void testNonQdm56CompositeIsValid() {
    Measure measure = new Measure();
    measure.setModel(ModelType.QI_CORE.getValue());
    MeasureMetaData metaData = new MeasureMetaData();
    metaData.setComposite(true);
    measure.setMeasureMetaData(metaData);
    assertTrue(validator.isValid(measure, context));
  }
}
