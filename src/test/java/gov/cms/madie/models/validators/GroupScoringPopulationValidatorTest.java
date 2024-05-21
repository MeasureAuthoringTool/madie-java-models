package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.Population;
import gov.cms.madie.models.measure.PopulationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class GroupScoringPopulationValidatorTest {
  private final GroupScoringPopulationValidator validator = new GroupScoringPopulationValidator();

  @Mock private ConstraintValidatorContext validatorContext;

  private Group group;
  private Population ip1;

  private Population denominator;
  private Population numerator;
  private Population numeratorExclusion;
  private Population numeratorObservation;

  @BeforeEach
  public void setUp() {
    ip1 =
        Population.builder()
            .name(PopulationType.INITIAL_POPULATION)
            .definition("Initial Population")
            .build();
    denominator =
        Population.builder().name(PopulationType.DENOMINATOR).definition("denominator").build();
    numerator = Population.builder().name(PopulationType.NUMERATOR).definition("numerator").build();
    numeratorExclusion =
        Population.builder()
            .name(PopulationType.NUMERATOR_EXCLUSION)
            .definition("numeratorExclusion")
            .build();
    numeratorObservation =
        Population.builder()
            .name(PopulationType.NUMERATOR_OBSERVATION)
            .definition("numeratorObservation")
            .build();

    List<Population> populations = new ArrayList<>();
    populations.add(ip1);
    group =
        Group.builder()
            .id("GroupId")
            .scoring("Cohort")
            .populations(populations)
            .groupDescription("Description")
            .build();
  }

  @Test
  public void testValidatorReturnsTrueForNullGroup() {
    boolean output = validator.isValid(null, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorReturnsFalseIfPopulationKeyIsNull() {
    ip1.setDefinition(null);
    boolean output = validator.isValid(group, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorNotAllowedPopulationForCohortScoringReturnsFalse() {
    // denominator not allowed for cohort
    group.getPopulations().add(denominator);
    boolean output = validator.isValid(group, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorMisMatchedPopulationValues() {
    // denominator not allowed for cohort
    group.setScoring("Ratio");

    group.getPopulations().add(denominator);
    group.getPopulations().add(numerator);

    group.getPopulations().add(numeratorObservation);
    boolean output = validator.isValid(group, validatorContext);
    assertTrue(output);
  }

  @Test
  public void testValidatorDefinitionMissingForRequiredPopulationReturnsFalse() {
    // remove define for IP and test
    group.getPopulations().get(0).setDefinition(null);
    boolean output = validator.isValid(group, validatorContext);
    assertFalse(output);
  }

  @Test
  public void testValidatorPopulationDefinitionCanBeEmptyForOptionalPopulation() {
    group.setScoring("Ratio");
    // set optional numeratorExclusion define to null
    numeratorExclusion.setDefinition(null);
    group.getPopulations().add(denominator);
    group.getPopulations().add(numerator);
    group.getPopulations().add(numeratorExclusion);
    boolean output = validator.isValid(group, validatorContext);
    assertTrue(output);
  }
}
