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
@Constraint(validatedBy = QDMGroupScoringValidator.class)
@Documented
public @interface ValidQDMGroupScoring {

  String message() default "Groups must have same scoring";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
