package gov.cms.madie.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MeasureObservationValidator.class)
@Documented
public @interface ValidMeasureObservation {
  String message() default "Measure observations are invalid for the chosen scoring type";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
