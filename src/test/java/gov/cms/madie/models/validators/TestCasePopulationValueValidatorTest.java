package gov.cms.madie.models.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.PopulationType;
import gov.cms.madie.models.measure.TestCaseGroupPopulation;
import gov.cms.madie.models.measure.TestCasePopulationValue;
import jakarta.validation.ConstraintValidatorContext;

@ExtendWith(SpringExtension.class)
public class TestCasePopulationValueValidatorTest {

  @Mock private ConstraintValidatorContext validatorContext;
  private final TestCasePopulationValueValidator validator = new TestCasePopulationValueValidator();

  private TestCaseGroupPopulation testCaseGroupPopulation;

  @Test
  public void testValidatorReturnsTrueForNull() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueNoPopulationValues() {
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueCohort() {
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder().scoring(MeasureScoring.COHORT.toString()).build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueProportion() {
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder().scoring(MeasureScoring.PROPORTION.toString()).build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseContinuousVariableNoCriteriaReference() {
    TestCasePopulationValue populationValue =
        TestCasePopulationValue.builder()
            .id("id1")
            .name(PopulationType.MEASURE_POPULATION_OBSERVATION)
            .criteriaReference(null)
            .expected("1")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populationValues(List.of(populationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseContinuousVariableNoId() {
    TestCasePopulationValue populationValue =
        TestCasePopulationValue.builder()
            .id(null)
            .name(PopulationType.MEASURE_POPULATION_OBSERVATION)
            .criteriaReference("testCriteriaReference")
            .expected("1")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populationValues(List.of(populationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsTrueContinuousVariable() {
    TestCasePopulationValue populationValue =
        TestCasePopulationValue.builder()
            .id("id1")
            .name(PopulationType.MEASURE_POPULATION_OBSERVATION)
            .criteriaReference("testCriteriaReference")
            .expected("1")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populationValues(List.of(populationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueContinuousVariableNoExpected() {
    TestCasePopulationValue populationValue =
        TestCasePopulationValue.builder()
            .id(null)
            .name(PopulationType.MEASURE_POPULATION_OBSERVATION)
            .criteriaReference(null)
            .expected(null)
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populationValues(List.of(populationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueContinuousVariableExpectedEmpty() {
    TestCasePopulationValue populationValue =
        TestCasePopulationValue.builder()
            .id(null)
            .name(PopulationType.MEASURE_POPULATION_OBSERVATION)
            .criteriaReference(null)
            .expected(" ")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populationValues(List.of(populationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseRatioDenomObservationNoCriteriaReference() {
    TestCasePopulationValue denomObservationPopulationValue =
        TestCasePopulationValue.builder()
            .id("id1")
            .name(PopulationType.DENOMINATOR_OBSERVATION)
            .criteriaReference(null)
            .expected("1")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.RATIO.toString())
            .populationValues(List.of(denomObservationPopulationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseRatioDenomObservationNoId() {
    TestCasePopulationValue denomObservationPopulationValue =
        TestCasePopulationValue.builder()
            .id(null)
            .name(PopulationType.DENOMINATOR_OBSERVATION)
            .criteriaReference("testCriteriaReference")
            .expected("1")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.RATIO.toString())
            .populationValues(List.of(denomObservationPopulationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseRatioNumerObservation() {
    TestCasePopulationValue numerObservationPopulationValue =
        TestCasePopulationValue.builder()
            .id("id2")
            .name(PopulationType.NUMERATOR_OBSERVATION)
            .criteriaReference(null)
            .expected("1")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.RATIO.toString())
            .populationValues(List.of(numerObservationPopulationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsTrueRatioNumerObservationNullExpected() {
    TestCasePopulationValue numerObservationPopulationValue =
        TestCasePopulationValue.builder()
            .id("id2")
            .name(PopulationType.NUMERATOR_OBSERVATION)
            .criteriaReference(null)
            .expected(null)
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.RATIO.toString())
            .populationValues(List.of(numerObservationPopulationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueRatioNumerObservationEmptyExpected() {
    TestCasePopulationValue numerObservationPopulationValue =
        TestCasePopulationValue.builder()
            .id("id2")
            .name(PopulationType.NUMERATOR_OBSERVATION)
            .criteriaReference(null)
            .expected(" ")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.RATIO.toString())
            .populationValues(List.of(numerObservationPopulationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrue() {
    TestCasePopulationValue denomObservationPopulationValue =
        TestCasePopulationValue.builder()
            .id("id1")
            .name(PopulationType.DENOMINATOR_OBSERVATION)
            .criteriaReference("testCriteriaReference")
            .expected("1")
            .build();
    TestCasePopulationValue numerObservationPopulationValue =
        TestCasePopulationValue.builder()
            .id("id2")
            .name(PopulationType.NUMERATOR_OBSERVATION)
            .criteriaReference("testCriteriaReferenc2")
            .expected("1")
            .build();
    testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .scoring(MeasureScoring.RATIO.toString())
            .populationValues(
                List.of(denomObservationPopulationValue, numerObservationPopulationValue))
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }
}
