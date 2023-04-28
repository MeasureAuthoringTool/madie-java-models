package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.measure.MeasureScoring;
import gov.cms.madie.models.measure.Population;
import gov.cms.madie.models.utils.ScoringPopulationDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 1. Required populations must be present in population list
 * 2. No extraneous populations that are not associated with the scoring can be present
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
            Stream<Population> pops = populations.stream()
                .filter(population -> {

                  return Objects.equals(option.getMeasurePopulation(), population.getName()); 
                });
            Population matchingPopulation =  pops.findAny().orElse(null);
            // required population must be present and has definition selected
            if(option.isRequired()) {
              return matchingPopulation != null && StringUtils.hasText(matchingPopulation.getDefinition());
            }
            return true;
          })
        && populations.stream()
        .allMatch(
          population ->
            measurePopulationOptions.stream()
              .anyMatch(option -> {      
                
                return Objects.equals(option.getMeasurePopulation(), population.getName()); 
              }
              ));
    } catch (Exception ex) {
      log.error("An error occurred while validation measure group", ex);
      return false;
    }
  }
}