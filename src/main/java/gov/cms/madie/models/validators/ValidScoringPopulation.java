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
@Constraint(validatedBy = ScoringPopulationValidator.class)
@Documented
public @interface ValidScoringPopulation {
  String message() default "Populations do not match Scoring";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
