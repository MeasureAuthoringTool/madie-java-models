package gov.cms.madie.models.validators;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import gov.cms.madie.models.measure.FhirMeasure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFhirGroupValidator implements ConstraintValidator<ValidFhirGroup, FhirMeasure> {
  @Override
  public boolean isValid(FhirMeasure measure, ConstraintValidatorContext context) {
    if (measure == null || CollectionUtils.isEmpty(measure.getGroups())) {
      return true;
    }
    if (measure.getGroups() != null) {
      for (int i = 0; i < measure.getGroups().size(); i++) {
        if (CollectionUtils.isEmpty(measure.getGroups().get(i).getMeasureGroupTypes())) {
          return false;
        }
        if (!StringUtils.hasLength(measure.getGroups().get(i).getPopulationBasis())) {
          return false;
        }
      }
    }

    return true;
  }
}
