package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.Population;
import gov.cms.madie.models.measure.PopulationType;
import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.TestCaseGroupPopulation;
import gov.cms.madie.models.measure.TestCasePopulationValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class ScoringPopulationValidatorTest {

  @Mock private ConstraintValidatorContext validatorContext;
  private final ScoringPopulationValidator validator = new ScoringPopulationValidator();

  private Measure measure;

  @BeforeEach
  public void setUp() {
    Population population = Population
      .builder()
      .name(PopulationType.INITIAL_POPULATION)
      .definition("Initial Population")
      .build();
    Group group1 =
      Group.builder()
        .id("GroupId")
        .scoring("Cohort")
        .populations(List.of(population))
        .groupDescription("Description")
        .build();
    List<Group> groups = new ArrayList<>();
    groups.add(group1);
    measure =
      Measure.builder()
        .active(true)
        .id("xyz-p13r-13ert")
        .cql("test cql")
        .measureSetId("IDIDID")
        .measureName("MSR01")
        .version(new gov.cms.madie.models.common.Version(0, 0,1))
        .groups(groups)
        .createdAt(Instant.now())
        .createdBy("test user")
        .lastModifiedAt(Instant.now())
        .lastModifiedBy("test user")
        .build();
  }

  @Test
  public void testValidatorReturnsTrueForNull() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseForMissingScoring() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .scoring(null)
        .populationValues(
          List.of(
            TestCasePopulationValue.builder()
              .name(PopulationType.INITIAL_POPULATION)
              .build()))
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseForNullPopulationsList() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .populationValues(null)
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseForEmptyPopulationsList() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .populationValues(List.of())
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseForMissingPopulation() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .groupId("GroupId")
        .populationValues(List.of(TestCasePopulationValue.builder().build()))
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsFalseForIncorrectPopulation() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .groupId("GroupId")
        .populationValues(
          List.of(
            TestCasePopulationValue.builder().name(PopulationType.DENOMINATOR).build()))
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsTrueForCorrectPopulation() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .groupId("GroupId")
        .populationValues(
          List.of(
            TestCasePopulationValue.builder()
              .name(PopulationType.INITIAL_POPULATION)
              .build()))
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForMissingPopulationBasis() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .groupId("GroupId")
        .populationValues(
          List.of(
            TestCasePopulationValue.builder()
              .name(PopulationType.INITIAL_POPULATION)
              .expected("BAD")
              .build()))
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseForExpectedValueMismatchPopulationBasisNonBoolean() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .groupId("GroupId")
          .populationBasis("Encounter")
        .populationValues(
          List.of(
            TestCasePopulationValue.builder()
              .name(PopulationType.INITIAL_POPULATION)
              .expected("BAD")
              .build()))
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }
  
  @Test
  public void testValidatorReturnsTrueForExpectedValueNumberCVBoolean() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .groupId("GroupId")
          .populationBasis("boolean")
        .populationValues(
          List.of(
            TestCasePopulationValue.builder()
              .name(PopulationType.INITIAL_POPULATION)
              .expected(true)
              .build(),
              TestCasePopulationValue.builder()
              .name(PopulationType.MEASURE_POPULATION)
              .expected(true)
              .build(),
              TestCasePopulationValue.builder()
              .name(PopulationType.MEASURE_POPULATION_OBSERVATION)
              .expected(2)
              .build()
              ))
        .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
        .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForExpectedValueMismatchPopulationBasisNonBoolean() {
    var testCaseGroupPopulation =
      TestCaseGroupPopulation.builder()
        .groupId("GroupId")
          .populationBasis("Encounter")
        .scoring(MeasureScoring.COHORT.toString())
        .build();
    List<TestCasePopulationValue> pops = new ArrayList<>();
    pops.add(null);
    testCaseGroupPopulation.setPopulationValues(pops);
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorReturnsTrueForNullExpectedValue() {
    var testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .groupId("GroupId")
            .populationBasis("Encounter")
            .populationValues(
                List.of(
                    TestCasePopulationValue.builder()
                        .name(PopulationType.INITIAL_POPULATION)
                        .expected(null)
                        .build()))
            .scoring(MeasureScoring.COHORT.toString())
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForEmptyStringExpectedValuePopulationBasisNonBoolean() {
    var testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .groupId("GroupId")
            .populationBasis("Encounter")
            .populationValues(
                List.of(
                    TestCasePopulationValue.builder()
                        .name(PopulationType.INITIAL_POPULATION)
                        .expected("")
                        .build()))
            .scoring(MeasureScoring.COHORT.toString())
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForNumericStringExpectedValuePopulationBasisNonBoolean() {
    var testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .groupId("GroupId")
            .populationBasis("Encounter")
            .populationValues(
                List.of(
                    TestCasePopulationValue.builder()
                        .name(PopulationType.INITIAL_POPULATION)
                        .expected("7")
                        .build()))
            .scoring(MeasureScoring.COHORT.toString())
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForBooleanExpectedValuePopulationBasisBoolean() {
    var testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .groupId("GroupId")
            .populationBasis("boolean")
            .populationValues(
                List.of(
                    TestCasePopulationValue.builder()
                        .name(PopulationType.INITIAL_POPULATION)
                        .expected(true)
                        .build()))
            .scoring(MeasureScoring.COHORT.toString())
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsTrueForNonBooleanExpectedValuePopulationBasisBoolean() {
    var testCaseGroupPopulation =
        TestCaseGroupPopulation.builder()
            .groupId("GroupId")
            .populationBasis("Boolean")
            .populationValues(
                List.of(
                    TestCasePopulationValue.builder()
                        .name(PopulationType.INITIAL_POPULATION)
                        .expected("BAD")
                        .build()))
            .scoring(MeasureScoring.COHORT.toString())
            .build();
    boolean output = validator.isValid(testCaseGroupPopulation, validatorContext);
    assertFalse(output);
  }
}
