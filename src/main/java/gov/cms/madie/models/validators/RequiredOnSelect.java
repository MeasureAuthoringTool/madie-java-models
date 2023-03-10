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
@Constraint(validatedBy = RequiredByOtherFieldValidator.class)
@Documented
public @interface RequiredOnSelect {

    String message() default "Required field is missing";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String selectedField();

    String requiredField();
}
