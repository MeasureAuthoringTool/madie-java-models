package gov.cms.madie.models.validators;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import jakarta.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasureGroupTypes;
import gov.cms.madie.models.measure.MeasureScoring;



@ExtendWith(SpringExtension.class)
public class ValidLibraryNameValidatorTest {
private final ValidLibraryNameValidator validator =  new ValidLibraryNameValidator();
	
	@Mock
  private ConstraintValidatorContext validatorContext;
	
	private Measure measure;
	
	@BeforeEach
  public void setUp() {
		measure = Measure.builder().model(ModelType.QI_CORE.getValue()).id("testId").measureSetId("testMeasureSetId")
				.cqlLibraryName("TestCqlLibraryName").ecqmTitle("testECqm")
    		.measureName("testMeasureName").versionId("0.0.000").build();
	}
	
	@Test
  public void testValidatorReturnsTrueForQiCoreMeasureWithoutSpecialCharater() {
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsFalseFirstLetterLowerCase() {
		measure.setCqlLibraryName("testCqlLibraryName");
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }
	
	@Test
  public void testValidatorReturnsFalseForQiCoreMeasureWithSpecialCharater() {
		measure.setCqlLibraryName("Test_CqlLibraryName");
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }
	
	@Test
  public void testValidatorReturnsTrueForQdmMeasureWithoutSpecialCharater() {
		measure.setModel("QDM v5.6");
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsTrueForQdmMeasureWithSpecialCharaterUnderscore() {
		measure.setModel("QDM v5.6");
		measure.setCqlLibraryName("Test_CqlLibraryName");
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsFalseForQdmMeasureWithOtherSpecialCharaters() {
		measure.setModel("QDM v5.6");
		measure.setCqlLibraryName("Test$%^&*CqlLibraryName");
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }
}
