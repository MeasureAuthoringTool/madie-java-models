package gov.cms.madie.models.validators;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.Measure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCompositeValidator implements ConstraintValidator<ValidComposite, Measure> {
  @Override
  public boolean isValid(Measure measure, ConstraintValidatorContext context) {
    return measure == null
        || measure.getMeasureMetaData() == null
        || !(ModelType.QDM_5_6.getValue().equals(measure.getModel())
            && measure.getMeasureMetaData().isComposite());
  }
}
