package gov.cms.madie.models.validators;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.FhirMeasure;
import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.MeasureMetaData;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class PurposeValidatorTest {
  private final PurposeValidator validator = new PurposeValidator();

  @Mock private ConstraintValidatorContext validatorContext;

  private Measure measure;

  @BeforeEach
  public void setUp() {
    measure =
        FhirMeasure.builder()
            .model(ModelType.QDM_5_6.getValue())
            .id("testId")
            .measureSetId("testMeasureSetId")
            .cqlLibraryName("TestCqlLibraryName")
            .ecqmTitle("testECqm")
            .measureName("testMeasureName")
            .versionId("0.0.000")
            .build();
  }

  @Test
  public void testValidatorReturnsTrueForNullMeasure() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForMeasureWithoutMetadata() {
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseForQDMMeasureWithPurpose() {
    Measure qdmMeasure =
        measure.toBuilder()
            .measureMetaData(MeasureMetaData.builder().purpose("Test").build())
            .build();
    boolean output = validator.isValid(qdmMeasure, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsTrueForQDMMeasureWithoutPurpose() {
    Measure qdmMeasure =
        measure.toBuilder().measureMetaData(MeasureMetaData.builder().build()).build();
    boolean output = validator.isValid(qdmMeasure, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForQICoreMeasureWithPurpose() {
    Measure qdmMeasure =
        measure.toBuilder()
            .model(ModelType.QI_CORE.getValue())
            .measureMetaData(MeasureMetaData.builder().purpose("Test").build())
            .build();
    boolean output = validator.isValid(qdmMeasure, validatorContext);
    assertTrue(output);
  }
}
