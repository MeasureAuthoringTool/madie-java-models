package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.Reference;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidReferencesValidator implements ConstraintValidator<ValidReferences, Measure> {
  @Override
  public boolean isValid(Measure measure, ConstraintValidatorContext context) {
    if (measure == null || measure.getMeasureMetaData() == null) {
      return true;
    }

    List<Reference> references = measure.getMeasureMetaData().getReferences();
    if (references == null || references.isEmpty()) {
      return true;
    }

    String model = measure.getModel();
    Set<String> allowedTypes = new HashSet<>();
    allowedTypes.add("Citation");
    allowedTypes.add("Justification");
    if ("QDM v5.6".equals(model)) {
      allowedTypes.add("Unknown");
    }

    boolean allValid = true;
    context.disableDefaultConstraintViolation();

    for (int i = 0; i < references.size(); i++) {
      Reference ref = references.get(i);
      if (StringUtils.isBlank(ref.getReferenceText())) {
        allValid = false;
        context
            .buildConstraintViolationWithTemplate("Reference text cannot be null or empty")
            .addPropertyNode("measureMetaData.references[" + i + "].referenceText")
            .addConstraintViolation();
      }
      if (ref.getReferenceType() == null || !allowedTypes.contains(ref.getReferenceType())) {
        allValid = false;
        context
            .buildConstraintViolationWithTemplate("Reference type must be one of " + allowedTypes)
            .addPropertyNode("measureMetaData.references[" + i + "].referenceType")
            .addConstraintViolation();
      }
    }

    return allValid;
  }
}
