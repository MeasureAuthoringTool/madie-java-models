package gov.cms.madie.models.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import gov.cms.madie.models.measure.Measure;

@ExtendWith(SpringExtension.class)
public class QDMMeasureScoringValidatorTest {

	private final QDMMeasureScoringValidator validator =  new QDMMeasureScoringValidator();
	
	@Mock
  private ConstraintValidatorContext validatorContext;
	
	private Measure measure;
	
	@BeforeEach
  public void setUp() {
		measure = Measure.builder().id("testId").measureSetId("testMeasureSetId")
				.cqlLibraryName("TestCqlLibraryName").ecqmTitle("testECqm")
    		.measureName("testMeasureName").versionId("0.0.000").build();
	}
	
	@Test
  public void testValidatorReturnsTrueForNullMeasure() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsFalseForQDMMeasure() {
		measure.setModel("QDM v5.6");
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }
	
	@Test
  public void testValidatorThrowsExceptionForInvalidScoring() {
		measure.setModel("QDM v5.6");
		measure.setScoring("test");
    assertThrows(
    		IllegalArgumentException.class, () -> validator.isValid(measure, validatorContext));
  }
	
	@Test
  public void testValidatorReturnsTrueForQDMMeasure() {
		measure.setModel("QDM v5.6");
		measure.setScoring("Cohort");
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsTrueForQICoreMeasure() {
		measure.setModel("QI-Core v4.1.1");
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
}
