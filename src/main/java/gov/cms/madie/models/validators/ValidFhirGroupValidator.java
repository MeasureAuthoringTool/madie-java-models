package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasureScoring;
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
        Group group = measure.getGroups().get(i);
        if (CollectionUtils.isEmpty(group.getMeasureGroupTypes())) {
          return false;
        }
        if (!StringUtils.hasLength(group.getPopulationBasis())) {
          return false;
        }
        // TODO: if group is composite, cannot have populations
        if (MeasureScoring.COMPOSITE.toString().equals(group.getScoring())) {
          if (!CollectionUtils.isEmpty(group.getPopulations())) {
            return false;
          }
        }
      }
    }

    return true;
  }
}
