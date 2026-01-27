package gov.cms.madie.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImprovementNotationValidator.class)
@Documented
public @interface ValidImprovementNotation {

  String message() default "Improvement Notation Description is invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
