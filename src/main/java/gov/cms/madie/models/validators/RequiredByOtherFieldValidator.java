package gov.cms.madie.models.validators;

import java.lang.reflect.Field;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequiredByOtherFieldValidator implements
    ConstraintValidator<RequiredOnSelect, Object> {

    private String selectedField;
    private String requiredField;

    @Override
    public void initialize(RequiredOnSelect annotation) {
        selectedField = annotation.selectedField();
        requiredField = annotation.requiredField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        try {
            Object selectedFieldValue = getFieldValue(object, selectedField);
            Object requiredFieldValue = getFieldValue(object, requiredField);
            return (selectedFieldValue != null && !selectedFieldValue.toString().isEmpty()) ? (
                requiredFieldValue != null && !requiredFieldValue.toString().isEmpty()) : true;
        } catch (Exception e) {
            log.error("Error occurred while cross validating fields of {} and {}", selectedField,
                requiredField);
            return false;
        }
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        if (object == null) {
            return null;
        }

        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
