package gov.cms.madie.models.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.cms.madie.models.measure.BaseConfigurationTypes;
import gov.cms.madie.models.measure.MeasureScoring;
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
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsFalseForEmptyScoring() {
		measure.setModel("QDM v5.6");
		measure.setScoring("");
		boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
		
  }
	
	@Test
  public void testValidatorReturnsFalseForInvalidScoring() {
		measure.setModel("QDM v5.6");
		measure.setScoring("invalidModel");
		boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }
	
	@Test
  public void testValidatorReturnsTrueForValidMeasureScoring() {
		measure.setModel("QDM v5.6");
		measure.setScoring("Cohort");
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }
	
	
	@Test
  public void testValidatorReturnsTrueForNullBaseConfigurationTypes() {
		measure.setModel("QDM v5.6");
		measure.setScoring(MeasureScoring.COHORT.toString());
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
	
	@Test
  public void testValidatorReturnsTrueForValidBaseConfiguration() {
		measure.setModel("QDM v5.6");
		measure.setScoring(MeasureScoring.COHORT.toString());
		measure.setBaseConfigurationTypes( Arrays.asList(BaseConfigurationTypes.values()));

		boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
    
  }
	
	@Test
	public void testDeserializeInvalidBaseConfigurationTypesThrowsException() throws JsonMappingException, JsonProcessingException {
		String qdmMeasureStr = "{\n"
				+ "    \"model\": \"QDM v5.6\",\n"
				+ "    \"measureSetId\": \"testMeasureSetId\",\n"
				+ "    \"cqlLibraryName\": \"TestLibraryName\",\n"
				+ "    \"ecqmTitle\": \"testEcqmTitle\",\n"
				+ "    \"measureName\": \"test QDM measure\",\n"
				+ "    \"versionId\": \"0.0.000\",\n"
				+ "    \"scoring\": \"Cohort\",\n"
				+ "    \"baseConfigurationTypes\": [\n"
				+ "        \"test\"\n"
				+ "    ]\n"
				+ "}";
		ObjectMapper mapper = new ObjectMapper();
		
		assertThrows(com.fasterxml.jackson.databind.exc.InvalidFormatException.class, () -> mapper.readValue(qdmMeasureStr, QdmMeasure.class));

	}
}
