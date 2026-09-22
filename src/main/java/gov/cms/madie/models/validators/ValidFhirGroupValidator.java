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

    // measure group types are optional for composites
    boolean isComposite =
        measure.getMeasureMetaData() != null && measure.getMeasureMetaData().isComposite();

    if (measure.getGroups() != null) {
      for (int i = 0; i < measure.getGroups().size(); i++) {
        Group group = measure.getGroups().get(i);
        if (!isComposite && CollectionUtils.isEmpty(group.getMeasureGroupTypes())) {
          return false;
        }
        if (!StringUtils.hasLength(group.getPopulationBasis())) {
          return false;
        }
        // TODO: if group is composite, cannot have populations
        if (MeasureScoring.COMPOSITE.toString().equals(group.getScoring())
            && !CollectionUtils.isEmpty(group.getPopulations())) {
          return false;
        }
      }
    }

    return true;
  }
}
