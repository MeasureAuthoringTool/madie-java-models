package gov.cms.madie.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntendedVenueValidator.class)
@Documented
public @interface ValidIntendedVenue {

  String message() default "Intended Venue is invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
