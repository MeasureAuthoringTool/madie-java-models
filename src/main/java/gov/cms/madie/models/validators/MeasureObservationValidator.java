package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Group;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class MeasureObservationValidator implements ConstraintValidator<ValidMeasureObservation, Group> {
  @Override
  public boolean isValid(Group value, ConstraintValidatorContext context) {
    // TODO - fill this in based on the following logic
    // If an observation exists, it must only be for CV or Ratio scoring types.
    // CV can have only one measure observation
    // Ratio can have only two, and must reference the NUMER or DENOM populations (cannot have two referencing same population)
    return true;
  }
}
