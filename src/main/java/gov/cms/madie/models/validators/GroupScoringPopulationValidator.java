package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Population;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.utils.ScoringPopulationDefinition;
import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasureScoring;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * 1. Required populations must be present in population map 2. Populations keys that are present
 * must have a value 3. No extraneous populations that are not associated with the scoring can be
 * present
 */
@Slf4j
public class GroupScoringPopulationValidator
  implements ConstraintValidator<ValidGroupScoringPopulation, Group> {
  @Override
  public boolean isValid(Group gsp, ConstraintValidatorContext context) {
    if (gsp == null) {
      return true;
    }

    List<Population> populations = gsp.getPopulations();
    if (gsp.getScoring() == null
      || gsp.getScoring().trim().isEmpty()
      || CollectionUtils.isEmpty(populations)) {
      return false;
    }

    try {
      MeasureScoring scoring = MeasureScoring.valueOfText(gsp.getScoring());
      List<MeasurePopulationOption> measurePopulationOptions = ScoringPopulationDefinition.SCORING_POPULATION_MAP.get(scoring);
      return measurePopulationOptions.stream()
        .allMatch(option -> {
          Population population = populations.stream()
            .filter(p -> Objects.equals(option.getMeasurePopulation(), p.getName()))
            .findAny()
            .orElse(null);
          boolean isPopulationNameMatched = Objects.equals(option.getMeasurePopulation(), population.getName());
          return (!option.isRequired() || isPopulationNameMatched)
            && (!isPopulationNameMatched || StringUtils.hasText(population.getDefinition()));
        })
        && populations.stream()
        .allMatch(
          p ->
            measurePopulationOptions.stream()
              .anyMatch(option -> option.getMeasurePopulation().equals(p.getName())));
    } catch (Exception ex) {
      log.error("An error occurred while validation measure group", ex);
      return false;
    }
  }
}
