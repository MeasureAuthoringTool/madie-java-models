package gov.cms.madie.models.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import gov.cms.madie.models.measure.QdmMeasure;


@ExtendWith(SpringExtension.class)
public class MeasureScoringValidatorTest {
	private final MeasureScoringValidator validator =  new MeasureScoringValidator();
	
	@Mock
  private ConstraintValidatorContext validatorContext;
	
	private QdmMeasure measure;
	
	@BeforeEach
  public void setUp() {
		measure = QdmMeasure.builder().id("testId").measureSetId("testMeasureSetId")
				.cqlLibraryName("TestCqlLibraryName").ecqmTitle("testECqm")
    		.measureName("testMeasureName").versionId("0.0.000").build();
	}
	
	@Test
  public void testValidatorReturnsTrueForNullMeasure() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsTrueForNullMeasureScoring() {
		measure.setModel("QDM v5.6");
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorThrowsExceptionForEmptyScoring() {
		measure.setModel("QDM v5.6");
		measure.setScoring("");
		assertThrows(
    		IllegalArgumentException.class, () -> validator.isValid(measure, validatorContext));
  }
	
	@Test
  public void testValidatorThrowsExceptionForInvalidScoring() {
		measure.setModel("QDM v5.6");
		measure.setScoring("invalidModel");
		assertThrows(
    		IllegalArgumentException.class, () -> validator.isValid(measure, validatorContext));
  }
	
	@Test
  public void testValidatorReturnsTrueForValidMeasureScoring() {
		measure.setModel("QDM v5.6");
		measure.setScoring("Cohort");
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }
}
