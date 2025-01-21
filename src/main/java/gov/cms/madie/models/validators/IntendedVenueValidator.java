package gov.cms.madie.models.validators;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.CodeConcept;
import gov.cms.madie.models.measure.Measure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntendedVenueValidator implements ConstraintValidator<ValidIntendedVenue, Measure> {
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
  public boolean isValid(Measure measure, ConstraintValidatorContext context) {
    if (measure != null
        && measure.getMeasureMetaData() != null
        && measure.getMeasureMetaData().getIntendedVenue() != null) {
      if (ModelType.QDM_5_6.getValue().equals(measure.getModel())) {
        return false;
      }

      return measure.getMeasureMetaData().getIntendedVenue().equals(eh)
          || measure.getMeasureMetaData().getIntendedVenue().equals(ec);
    }

    return true;
  }
}
