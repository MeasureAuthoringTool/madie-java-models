package gov.cms.madie.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PurposeValidator.class)
@Documented
public @interface ValidPurpose {
  String message() default "Purpose is not allowed for a QDM measure";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
