package gov.cms.madie.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OtherImprovementNotationValidator.class)
@Documented
public @interface ValidOtherImprovementNotation {

  String message() default "Improvement Notation Description is required when Other is selected";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
