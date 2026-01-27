package gov.cms.madie.models.validators;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.Measure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class PurposeValidator implements ConstraintValidator<ValidPurpose, Measure> {
  @Override
  public boolean isValid(Measure measure, ConstraintValidatorContext context) {
    // for QDM measures, purpose is not allowed. Therefore, make sure it is empty
    if (measure != null
        && measure.getMeasureMetaData() != null
        && StringUtils.equalsIgnoreCase(measure.getModel(), ModelType.QDM_5_6.getValue())) {
      return StringUtils.isEmpty(measure.getMeasureMetaData().getPurpose());
    }
    return true;
  }
}
