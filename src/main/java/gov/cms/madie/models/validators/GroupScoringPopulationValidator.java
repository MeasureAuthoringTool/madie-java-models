package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.Population;
import gov.cms.madie.models.utils.ScoringPopulationDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * 1. Required populations must be present in population list
 * 2. Population names that are present must have a value
 * 3. No extraneous populations that are not associated with the scoring can be present
 */
@Slf4j
public class GroupScoringPopulationValidator
  implements ConstraintValidator<ValidGroupScoringPopulation, Group> {
  @Override
  public boolean isValid(Group group, ConstraintValidatorContext context) {
    if (group == null) {
      return true;
    }

    List<Population> populations = group.getPopulations();
    if (group.getScoring() == null
      || group.getScoring().trim().isEmpty()
      || CollectionUtils.isEmpty(populations)) {
      return false;
    }

    try {
      MeasureScoring scoring = MeasureScoring.valueOfText(group.getScoring());
      // get the allowed list of populations for selected scoring
      List<MeasurePopulationOption> measurePopulationOptions = ScoringPopulationDefinition.SCORING_POPULATION_MAP.get(scoring);
      // make sure populations are from allowed list for group scoring
      return measurePopulationOptions.stream()
        .allMatch(
          option -> {
            Population matchingPopulation = populations.stream()
              .filter(population -> Objects.equals(option.getMeasurePopulation(), population.getName()))
              .findAny()
              .orElse(null);
            // required population must be present
            if (matchingPopulation == null && option.isRequired()) {
              return false;
            } else if (matchingPopulation == null && !option.isRequired()) {
              return true;
            }
            // both optional and required populations must have cql define selected
            return StringUtils.hasText(matchingPopulation.getDefinition());
          })
        && populations.stream()
        .allMatch(
          population ->
            measurePopulationOptions.stream()
              .anyMatch(option -> Objects.equals(option.getMeasurePopulation(), population.getName())));
    } catch (Exception ex) {
      log.error("An error occurred while validation measure group", ex);
      return false;
    }
  }
}