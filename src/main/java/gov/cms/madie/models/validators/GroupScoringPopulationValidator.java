package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.MeasurePopulation;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.utils.ScoringPopulationDefinition;
import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasureScoring;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;

/**
 * 1. Required populations must be present in population list
 * 2. Population keys that are present must have a value
 * 3. No extraneous populations that are not associated with the scoring can be present
 */
@Slf4j
public class GroupScoringPopulationValidator
  implements ConstraintValidator<ValidGroupScoringPopulation, Group> {
  @Override
  public boolean isValid(Group gsp, ConstraintValidatorContext context) {
    if (gsp == null) {
      return true;
    }

    Map<MeasurePopulation, String> population = gsp.getPopulation();
    if (gsp.getScoring() == null
      || gsp.getScoring().trim().isEmpty()
      || population == null
      || population.isEmpty()) {
      return false;
    }

    try {
      MeasureScoring scoring = MeasureScoring.valueOfText(gsp.getScoring());
      List<MeasurePopulationOption> measurePopulationOptions = ScoringPopulationDefinition.SCORING_POPULATION_MAP.get(scoring);
      return measurePopulationOptions.stream()
        .allMatch(
          option -> {
            String define = population.get(option.getMeasurePopulation());
            return (!option.isRequired()
              || population.containsKey(option.getMeasurePopulation()))
              && (!population.containsKey(option.getMeasurePopulation())
              || define != null && !define.trim().isEmpty());
          })
        && population.keySet().stream()
        .allMatch(
          key ->
            measurePopulationOptions.stream()
              .anyMatch(option -> option.getMeasurePopulation().equals(key)));
    } catch (Exception ex) {
      log.error("An error occurred while validation measure group", ex);
      return false;
    }
  }
}
