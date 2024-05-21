package gov.cms.madie.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.QdmMeasure;

public class MeasureScoringValidator
    implements ConstraintValidator<ValidMeasureScoring, QdmMeasure> {

  @Override
  public boolean isValid(QdmMeasure measure, ConstraintValidatorContext context) {
    if (measure != null && measure.getScoring() != null) {
      return MeasureScoring.valueOfText(measure.getScoring()) == null ? false : true;
    }
    return true;
  }
}
