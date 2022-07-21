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
      // get the allowed list of populations for selected scoring
      List<MeasurePopulationOption> measurePopulationOptions = ScoringPopulationDefinition.SCORING_POPULATION_MAP.get(scoring);

      // make sure populations are from allowed list of populations for scoring
      // and each required population has definition
      boolean allPopulationMatched = populations.stream()
        .allMatch(population -> {
          // population name can't be null
          if (population.getName() == null) {
            return false;
          }
          MeasurePopulationOption populationOption = measurePopulationOptions.stream()
            .filter(option -> Objects.equals(option.getMeasurePopulation(), population.getName()))
            .findAny()
            .orElse(null);

          // no match found for population
          if (populationOption == null) {
            return false;
          } else if (populationOption.isRequired()) {
            // required population must have definition
            return StringUtils.hasText(population.getDefinition());
          }
          return true;
        });
      return allPopulationMatched;
    } catch (Exception ex) {
      log.error("An error occurred while validation measure group", ex);
      return false;
    }
  }
}
