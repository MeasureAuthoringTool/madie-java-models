package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.*;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ImprovementNotationValidator
    implements ConstraintValidator<ValidImprovementNotation, QdmMeasure> {

  @Override
  public boolean isValid(QdmMeasure measure, ConstraintValidatorContext context) {
    if (StringUtils.equalsIgnoreCase(
            measure.getImprovementNotation(), "Increased score indicates improvement")
        || StringUtils.equalsIgnoreCase(
            measure.getImprovementNotation(), "Decreased score indicates improvement")) {
      return true;
    } else if (StringUtils.equalsIgnoreCase(measure.getImprovementNotation(), "Other")) {
      return StringUtils.isNotBlank(measure.getImprovementNotationDescription());
    }
    return false;
  }
}
