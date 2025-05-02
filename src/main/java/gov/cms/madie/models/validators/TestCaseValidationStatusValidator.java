package gov.cms.madie.models.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TestCaseValidationStatusValidatorImpl.class)
@Documented
public @interface TestCaseValidationStatusValidator {
  Class<? extends Enum<?>> enumClass();

  String message() default "Validation Status provided is not a valid status.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
