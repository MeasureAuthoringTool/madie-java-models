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

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.QdmMeasure;

@ExtendWith(SpringExtension.class)
public class QDMGroupScoringValidatorTest {

  private final QDMGroupScoringValidator validator = new QDMGroupScoringValidator();

  @Mock private ConstraintValidatorContext validatorContext;

  private QdmMeasure measure;

  @BeforeEach
  public void setUp() {
    measure =
        QdmMeasure.builder()
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
  public void testValidatorReturnsFalseForGroupScoringNotMatchingMeasureScoring() {
    measure.setScoring(MeasureScoring.COHORT.toString());
    Group group =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populationBasis("true")
            .build();
    measure.setGroups(Arrays.asList(group));
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseForMultipleGroupsHavingDifferentScoring() {
    measure.setScoring(MeasureScoring.COHORT.toString());
    Group group1 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populationBasis("true")
            .build();
    Group group2 =
        Group.builder()
            .id("testGroupId")
            .scoring(MeasureScoring.COHORT.toString())
            .populationBasis("true")
            .build();
    measure.setGroups(Arrays.asList(group1, group2));
    boolean output = validator.isValid(measure, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsTrueForSameScoringGroupLevelAndMeasureLevel() {
    measure.setScoring(MeasureScoring.COHORT.toString());
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
            .build();
    measure.setGroups(Arrays.asList(group1, group2));
    boolean output = validator.isValid(measure, validatorContext);
    assertTrue(output);
  }
}
