package gov.cms.madie.models.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gov.cms.madie.models.measure.Endorsement;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RequiredByOtherFieldValidatorTest {

    private final RequiredByOtherFieldValidator validator = new RequiredByOtherFieldValidator();

    @Mock
    private ConstraintValidatorContext validatorContext;

    @Mock
    RequiredOnSelect annotation;

    private Endorsement endorsement;

    @BeforeEach
    public void setUp() {
        Mockito.when(annotation.selectedField()).thenReturn("endorser");
        Mockito.when(annotation.requiredField()).thenReturn("endorsementId");
        endorsement = Endorsement
            .builder()
            .endorser("NQF")
            .endorsementId("1234")
            .endorserSystemId("456")
            .build();
        validator.initialize(annotation);
    }

    @Test
    public void testValidatorReturnsTrueForNullEndorsement() {
        boolean output = validator.isValid(null, validatorContext);
        assertTrue(output);
    }

    @Test
    public void testValidatorReturnsFalseIfSystemIdIsNull() {
        endorsement.setEndorsementId(null);
        boolean output = validator.isValid(endorsement, validatorContext);
        assertFalse(output);
    }

    @Test
    public void testValidatorNullValuesReturnsTrue() {
        endorsement.setEndorsementId(null);
        endorsement.setEndorser(null);
        boolean output = validator.isValid(endorsement, validatorContext);
        assertTrue(output);
    }

    @Test
    public void testValidatorEmptyEndorsementReturnsTrue() {
        boolean output = validator.isValid(new Endorsement(), validatorContext);
        assertTrue(output);
    }
    @Test
    public void testValidatorNullEndorserNotNullEndorserIdValuesReturnsFalse() {
        endorsement.setEndorsementId("testEndorsementId");
        endorsement.setEndorser(null);
        boolean output = validator.isValid(endorsement, validatorContext);
        assertFalse(output);
    }
}