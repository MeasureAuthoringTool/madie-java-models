package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.CodeConcept;
import gov.cms.madie.models.measure.FhirMeasure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntendedVenueValidator
    implements ConstraintValidator<ValidIntendedVenue, FhirMeasure> {
  private final CodeConcept eh =
      CodeConcept.builder()
          .code("eh")
          .codeSystem("http://hl7.org/fhir/us/cqfmeasures/CodeSystem/intended-venue-codes")
          .display("EH")
          .definition(
              "An eligible hospital is an acute care facility that is eligible to participate in a quality measurement initiative.")
          .build();

  private final CodeConcept ec =
      CodeConcept.builder()
          .code("ec")
          .codeSystem("http://hl7.org/fhir/us/cqfmeasures/CodeSystem/intended-venue-codes")
          .display("EC")
          .definition(
              "An eligible clinician is a clinician who is eligible to participate in a quality measurement initiative.")
          .build();

  @Override
  public boolean isValid(FhirMeasure measure, ConstraintValidatorContext context) {
    if (measure != null && measure.getIntendedVenue() != null) {
      return measure.getIntendedVenue().equals(eh) || measure.getIntendedVenue().equals(ec);
    }

    return true;
  }
}
