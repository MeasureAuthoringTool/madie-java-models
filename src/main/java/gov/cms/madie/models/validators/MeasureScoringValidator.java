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
      MeasureScoring scoring = MeasureScoring.valueOfText(measure.getScoring());
      if (scoring == MeasureScoring.COMPOSITE) {
        return false;
      }
      return scoring != null;
    }
    return true;
  }
}
