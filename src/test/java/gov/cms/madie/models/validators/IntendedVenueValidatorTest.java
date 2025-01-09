package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.CodeConcept;
import gov.cms.madie.models.measure.FhirMeasure;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntendedVenueValidatorTest {
  private final IntendedVenueValidator validator = new IntendedVenueValidator();

  @Mock private ConstraintValidatorContext validatorContext;

  private FhirMeasure measure;
  private CodeConcept eh;
  private CodeConcept ec;

  @BeforeEach
  public void setUp() {
    measure =
        FhirMeasure.builder()
            .id("testId")
            .measureSetId("testMeasureSetId")
            .cqlLibraryName("TestCqlLibraryName")
            .ecqmTitle("testECqm")
            .measureName("testMeasureName")
            .versionId("0.0.000")
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
  void testNullIntendedVenue() {
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testEhIntendedVenue() {
    measure.setIntendedVenue(eh);
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testEcIntendedVenue() {
    measure.setIntendedVenue(ec);
    assertTrue(validator.isValid(measure, validatorContext));
  }

  @Test
  void testInvalidEhIntendedVenue() {
    eh.setCode("invalidEh");
    measure.setIntendedVenue(eh);
    assertFalse(validator.isValid(measure, validatorContext));
  }

  @Test
  void testInvalidEcIntendedVenue() {
    ec.setCode("invalidEc");
    measure.setIntendedVenue(ec);
    assertFalse(validator.isValid(measure, validatorContext));
  }
}
