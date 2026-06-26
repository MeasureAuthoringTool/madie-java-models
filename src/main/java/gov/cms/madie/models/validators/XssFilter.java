package gov.cms.madie.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = XssValidator.class)
@Documented
public @interface XssFilter {
  String message() default "Content contains invalid characters.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
