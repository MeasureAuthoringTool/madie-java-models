package gov.cms.madie.models.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupPopulationIdValidator.class)
@Documented
public @interface ValidGroupPopulationId {
	String message() default "Populations Id is required";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
