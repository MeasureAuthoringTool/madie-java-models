package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.AggregateMethodType;
import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasureObservation;
import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.Population;
import gov.cms.madie.models.measure.PopulationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
class MeasureObservationValidatorTest {

  private final MeasureObservationValidator validator = new MeasureObservationValidator();

  @Mock
  private ConstraintValidatorContext validatorContext;

  private Group cohortGroup;
  private Group cvGroup;
  private Group ratioGroup;
  private Population ip1;
  private Population denominator;
  private Population numerator;
  private Population measurePop;

  @BeforeEach
  void setup() {
    ip1 = Population
        .builder()
        .id("uuid-1")
        .name(PopulationType.INITIAL_POPULATION)
        .definition("Initial Population")
        .build();
    denominator = Population
        .builder()
        .id("uuid-2")
        .name(PopulationType.DENOMINATOR)
        .definition("denominator")
        .build();
    numerator = Population
        .builder()
        .id("uuid-3")
        .name(PopulationType.NUMERATOR)
        .definition("numerator")
        .build();
    measurePop = Population
        .builder()
        .id("uuid-4")
        .name(PopulationType.MEASURE_POPULATION)
        .definition("measurePopulation")
        .build();
    cohortGroup =
        Group.builder()
            .id("GroupId1")
            .scoring(MeasureScoring.COHORT.toString())
            .populations(List.of(ip1))
            .groupDescription("Description")
            .build();
    cvGroup =
        Group.builder()
            .id("GroupId2")
            .scoring(MeasureScoring.CONTINUOUS_VARIABLE.toString())
            .populations(List.of(ip1, measurePop))
            .groupDescription("Description")
            .build();
    ratioGroup =
        Group.builder()
            .id("GroupId3")
            .scoring(MeasureScoring.RATIO.toString())
            .populations(List.of(ip1, denominator, numerator))
            .groupDescription("Description")
            .build();
  }

  @Test
  void testValidatorReturnsFalseForNullGroup() {
    boolean output = validator.isValid(null, validatorContext);
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForMissingScoring() {
    boolean output = validator.isValid(Group.builder().scoring(null).build(), validatorContext);
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForNullPopulations() {
    Group g = Group.builder().build();
    g.setPopulations(null);
    boolean output = validator.isValid(g, validatorContext);
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsTrueForCohortGroupNoObservations() {
    boolean output = validator.isValid(cohortGroup, validatorContext);
    assertThat(output, is(true));
  }

  @Test
  void testValidatorReturnsTrueForCohortGroupWithEmptyObservations() {
    boolean output = validator.isValid(cohortGroup.toBuilder().measureObservations(List.of()).build(), validatorContext);
    assertThat(output, is(true));
  }

  @Test
  void testValidatorReturnsFalseForCohortGroupWithObservations() {
    boolean output = validator.isValid(
        cohortGroup
            .toBuilder()
            .measureObservations(List.of(MeasureObservation.builder().build()))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForProportionGroupWithObservations() {
    boolean output = validator.isValid(
        cohortGroup
            .toBuilder()
            .scoring(MeasureScoring.PROPORTION.toString())
            .measureObservations(List.of(MeasureObservation.builder().build()))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForContinuousVariableGroupWithNoObservations() {
    boolean output = validator.isValid(
        cvGroup
            .toBuilder()
            .measureObservations(List.of())
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForContinuousVariableGroupWithObservationNoDefinition() {
    boolean output = validator.isValid(
        cvGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder().build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForContinuousVariableGroupWithObservationNoAggregateMethod() {
    boolean output = validator.isValid(
        cvGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForContinuousVariableGroupWithObservationNoId() {
    boolean output = validator.isValid(
        cvGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsTrueForContinuousVariableGroupWithObservation() {
    boolean output = validator.isValid(
        cvGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .id(UUID.randomUUID().toString())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(true));
  }

  @Test
  void testValidatorReturnsFalseForContinuousVariableGroupWithMultipleObservations() {
    boolean output = validator.isValid(
        cvGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .id(UUID.randomUUID().toString())
                    .build(),
                MeasureObservation.builder()
                    .definition("obsABC2")
                    .aggregateMethod(AggregateMethodType.COUNT.getValue())
                    .id(UUID.randomUUID().toString())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsTrueForRatioGroupWithNoObservations() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of())
            .build(),
        validatorContext
    );
    assertThat(output, is(true));
  }

  @Test
  void testValidatorReturnsFalseForRatioGroupWithOneObservationNoDefinition() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .id(UUID.randomUUID().toString())
                    .criteriaReference(denominator.getId())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForRatioGroupWithOneObservationNoAggregateMethod() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .id(UUID.randomUUID().toString())
                    .criteriaReference(denominator.getId())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForRatioGroupWithOneObservationNoId() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .criteriaReference(denominator.getId())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsFalseForRatioGroupWithOneObservationNoCriteriaReference() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .id(UUID.randomUUID().toString())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsTrueForRatioGroupWithOneObservation() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .id(UUID.randomUUID().toString())
                    .criteriaReference(denominator.getId())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(true));
  }

  @Test
  void testValidatorReturnsFalseForRatioGroupWithTwoObservationsSameReference() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .id(UUID.randomUUID().toString())
                    .criteriaReference(denominator.getId())
                    .build(),
                MeasureObservation.builder()
                    .definition("obcDEF")
                    .aggregateMethod(AggregateMethodType.COUNT.getValue())
                    .id(UUID.randomUUID().toString())
                    .criteriaReference(denominator.getId())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(false));
  }

  @Test
  void testValidatorReturnsTrueForRatioGroupWithTwoObservationsDifferentReference() {
    boolean output = validator.isValid(
        ratioGroup
            .toBuilder()
            .measureObservations(List.of(
                MeasureObservation.builder()
                    .definition("obsABC")
                    .aggregateMethod(AggregateMethodType.AVERAGE.getValue())
                    .id(UUID.randomUUID().toString())
                    .criteriaReference(denominator.getId())
                    .build(),
                MeasureObservation.builder()
                    .definition("obcDEF")
                    .aggregateMethod(AggregateMethodType.COUNT.getValue())
                    .id(UUID.randomUUID().toString())
                    .criteriaReference(numerator.getId())
                    .build()
            ))
            .build(),
        validatorContext
    );
    assertThat(output, is(true));
  }

}
