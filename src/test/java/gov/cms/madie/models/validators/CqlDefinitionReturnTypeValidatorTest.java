package gov.cms.madie.models.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.utils.ResourceUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class CqlDefinitionReturnTypeValidatorTest implements ResourceUtil {

  @Mock
  private ConstraintValidatorContext constraintValidatorContext;

  private final CqlDefinitionReturnTypeValidator returnTypeValidator = new CqlDefinitionReturnTypeValidator();

  private Measure measure;

  @BeforeEach
  public void setup() throws Exception {
    String measureData = getData("/cms108reduced.json");
    ObjectMapper objectMapper = new ObjectMapper();
    measure = objectMapper.readValue(measureData, Measure.class);
  }

  @Test
  public void testMeasureValidityWhenNoCqlElmAvailable() {
    measure.setElmJson(null);
    boolean output = returnTypeValidator.isValid(measure, constraintValidatorContext);
    assertTrue(output);
  }

  @Test
  public void testMeasureValidity() {
    boolean output = returnTypeValidator.isValid(measure, constraintValidatorContext);
    assertTrue(output);
  }

  @Test
  public void testMeasureValidityWhenPopulationDefinitionReturnTypesNotMatchingWithPopulationBasis() {
    measure.getGroups().get(0).setPopulationBasis("Procedure");
    boolean output = returnTypeValidator.isValid(measure, constraintValidatorContext);
    assertFalse(output);
  }
}
