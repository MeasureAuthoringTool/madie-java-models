package gov.cms.madie.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

  private List<String> acceptedValues;
  private boolean allowNull;

  @Override
  public void initialize(EnumValidator annotation) {
    acceptedValues =
        Stream.of(annotation.enumClass().getEnumConstants())
            .map(Enum::toString)
            .collect(Collectors.toList());
    allowNull = annotation.allowNull();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return allowNull;
    }
    return acceptedValues.contains(value);
  }
}
