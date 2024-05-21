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
import gov.cms.madie.models.measure.FhirMeasure;
import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasureGroupTypes;
import gov.cms.madie.models.measure.MeasureScoring;

@ExtendWith(SpringExtension.class)
public class FhirGroupValidatorTest {
  private final ValidFhirGroupValidator validator = new ValidFhirGroupValidator();

  @Mock private ConstraintValidatorContext validatorContext;

  private FhirMeasure measure;

  @BeforeEach
  public void setUp() {
    measure =
        FhirMeasure.builder()
            .model(ModelType.QI_CORE.getValue())
            .id("testId")
            .measureSetId("testMeasureSetId")
            .cqlLibraryName("TestCqlLibraryName")
            .ecqmTitle("testECqm")
            .measureName("testMeasureName")
            .versionId("0.0.000")
            .build();
  }

  @Test
  public void testValidatorReturnsTrueForNullMeasure() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForNullGroups() {
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseForNullMeasureGroupTypes() {
    Group group1 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.COHORT.toString())
            .populationBasis("true")
            .build();
    Group group2 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.COHORT.toString())
            .populationBasis("true")
            .measureGroupTypes(Arrays.asList(MeasureGroupTypes.OUTCOME))
            .build();
    measure.setGroups(Arrays.asList(group1, group2));
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseForNullPopulationBasis() {
    Group group1 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.COHORT.toString())
            .measureGroupTypes(Arrays.asList(MeasureGroupTypes.OUTCOME))
            .build();
    Group group2 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.COHORT.toString())
            .populationBasis("true")
            .measureGroupTypes(Arrays.asList(MeasureGroupTypes.OUTCOME))
            .build();
    measure.setGroups(Arrays.asList(group1, group2));
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsTrueForValidMeasureGroupTypes() {
    Group group1 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.COHORT.toString())
            .populationBasis("true")
            .measureGroupTypes(Arrays.asList(MeasureGroupTypes.OUTCOME))
            .build();
    Group group2 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.COHORT.toString())
            .populationBasis("true")
            .measureGroupTypes(Arrays.asList(MeasureGroupTypes.OUTCOME))
            .build();
    measure.setGroups(Arrays.asList(group1, group2));
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
}
