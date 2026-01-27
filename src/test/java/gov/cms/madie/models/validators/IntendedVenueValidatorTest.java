package gov.cms.madie.models.validators;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.CodeConcept;
import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.MeasureMetaData;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntendedVenueValidatorTest {
  private final IntendedVenueValidator validator = new IntendedVenueValidator();

  @Mock private ConstraintValidatorContext validatorContext;

  private Measure measure;
  private CodeConcept eh;
  private CodeConcept ec;

  @BeforeEach
  public void setUp() {
    measure =
        Measure.builder()
            .model(ModelType.QI_CORE.getValue())
            .id("testId")
            .measureSetId("testMeasureSetId")
            .cqlLibraryName("TestCqlLibraryName")
            .ecqmTitle("testECqm")
            .measureName("testMeasureName")
            .versionId("0.0.000")
            .measureMetaData(MeasureMetaData.builder().build())
            .build();

    eh =
        CodeConcept.builder()
            .code("eh")
            .codeSystem("http://hl7.org/fhir/us/cqfmeasures/CodeSystem/intended-venue-codes")
            .display("EH")
            .definition(
                "An eligible hospital is an acute care facility that is eligible to participate in a quality measurement initiative.")
            .build();

    ec =
        CodeConcept.builder()
            .code("ec")
            .codeSystem("http://hl7.org/fhir/us/cqfmeasures/CodeSystem/intended-venue-codes")
            .display("EC")
            .definition(
                "An eligible clinician is a clinician who is eligible to participate in a quality measurement initiative.")
            .build();
  }

  @Test
  void testNullIntendedVenueForQiCoreMeasure() {
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testIntendedVenueForQdmMeasure() {
    measure.setModel(ModelType.QDM_5_6.getValue());
    measure.getMeasureMetaData().setIntendedVenue(eh);

    assertFalse(validator.isValid(measure, validatorContext));
  }

  @Test
  void testEhIntendedVenueForQiCoreMeasure() {
    measure.getMeasureMetaData().setIntendedVenue(eh);
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testEcIntendedVenueForQiCoreMeasure() {
    measure.getMeasureMetaData().setIntendedVenue(ec);
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testInvalidEhIntendedVenueForQiCoreMeasure() {
    eh.setCode("invalidEh");
    measure.getMeasureMetaData().setIntendedVenue(eh);
    assertFalse(validator.isValid(measure, validatorContext));
  }

  @Test
  void testInvalidEcIntendedVenueForQiCoreMeasure() {
    ec.setCode("invalidEc");
    measure.getMeasureMetaData().setIntendedVenue(ec);
    assertFalse(validator.isValid(measure, validatorContext));
  }
}
