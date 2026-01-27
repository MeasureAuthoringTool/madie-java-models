package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MeasureObservationValidator
    implements ConstraintValidator<ValidMeasureObservation, Group> {
  @Override
  public boolean isValid(Group group, ConstraintValidatorContext context) {
    // If an observation exists, it must only be for CV or Ratio scoring types.
    // CV can have only one measure observation
    // Ratio can have only two, and must reference the NUMER or DENOM populations (cannot have two
    // referencing same population)
    if (group == null || group.getScoring() == null || group.getPopulations() == null) {
      return false;
    }
    MeasureScoring measureScoring = MeasureScoring.valueOfText(group.getScoring());
    List<Population> populations = group.getPopulations();
    List<MeasureObservation> measureObservations = group.getMeasureObservations();

    boolean isValid;
    switch (measureScoring) {
      case CONTINUOUS_VARIABLE:
        isValid =
            measureObservations != null
                && measureObservations.size() == 1
                && measureObservations.stream()
                    .allMatch(
                        obs ->
                            StringUtils.hasText(obs.getDefinition())
                                && StringUtils.hasText(obs.getAggregateMethod())
                                && AggregateMethodType.fromValue(obs.getAggregateMethod()) != null
                                && StringUtils.hasText(obs.getId()));
        break;
      case RATIO:
        isValid =
            measureObservations == null
                || measureObservations.isEmpty()
                || (measureObservations.size() <= 2
                    && measureObservations.stream()
                        .allMatch(
                            obs ->
                                StringUtils.hasText(obs.getDefinition())
                                    && StringUtils.hasText(obs.getAggregateMethod())
                                    && StringUtils.hasText(obs.getId())
                                    && AggregateMethodType.fromValue(obs.getAggregateMethod())
                                        != null
                                    && StringUtils.hasText(obs.getCriteriaReference())
                                    && populations.stream()
                                        .anyMatch(
                                            p -> obs.getCriteriaReference().equals(p.getId())))
                    && measureObservations.size()
                        == measureObservations.stream()
                            .map(
                                obs ->
                                    populations.stream()
                                        .map(Population::getId)
                                        .filter(id -> obs.getCriteriaReference().equals(id))
                                        .findFirst()
                                        .orElse(null))
                            .collect(Collectors.toSet())
                            .size());
        break;
      default:
        isValid =
            group.getMeasureObservations() == null || group.getMeasureObservations().isEmpty();
        break;
    }

    return isValid;
  }
}
