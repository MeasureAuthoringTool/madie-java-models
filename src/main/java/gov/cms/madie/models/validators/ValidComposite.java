package gov.cms.madie.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = gov.cms.madie.models.validators.ValidCompositeValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface ValidComposite {
  String message() default "QDM 5.6 cannot be composite.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
