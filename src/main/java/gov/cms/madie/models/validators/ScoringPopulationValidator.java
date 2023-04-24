package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.PopulationType;
import gov.cms.madie.models.measure.MeasurePopulationOption;
import gov.cms.madie.models.measure.TestCaseGroupPopulation;
import gov.cms.madie.models.measure.TestCasePopulationValue;
import gov.cms.madie.models.utils.ScoringPopulationDefinition;
import gov.cms.madie.models.measure.MeasureScoring;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class ScoringPopulationValidator
    implements ConstraintValidator<ValidScoringPopulation, TestCaseGroupPopulation> {

  @Override
  public boolean isValid(
      TestCaseGroupPopulation testCaseGroupPopulation, ConstraintValidatorContext context) {
    if (testCaseGroupPopulation == null) {
      return true;
    }
    if (testCaseGroupPopulation.getScoring() == null
        || testCaseGroupPopulation.getScoring().trim().isEmpty()) {
      return false;
    }

    MeasureScoring scoring = MeasureScoring.valueOfText(testCaseGroupPopulation.getScoring());
    List<TestCasePopulationValue> populationValues = testCaseGroupPopulation.getPopulationValues();
    if (scoring == null || populationValues == null || populationValues.isEmpty()) {
      return false;
    }

    List<PopulationType> requiredPopulations =
        ScoringPopulationDefinition.SCORING_POPULATION_MAP.get(scoring).stream()
            .filter(MeasurePopulationOption::isRequired)
            .map(MeasurePopulationOption::getMeasurePopulation)
            .collect(Collectors.toList());
    List<PopulationType> receivedPopulations =
        populationValues.stream()
            .filter(Objects::nonNull)
            .map(TestCasePopulationValue::getName)
            .distinct()
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    final String popBasis = testCaseGroupPopulation.getPopulationBasis();

    boolean allValuesMatchPopulationBasis = popBasis == null || populationValues.stream().allMatch(popVal -> {
      
      if (popVal == null || popVal.getExpected() == null || popVal.getExpected().toString().isEmpty()) {
        return true;
      } else {
        //isObservation needs to be a number, regardless of populationBasis
        //!isObservation needs to match popBasis type (ie., Boolean or Number)
        if(isObservation(popVal.getName())) {
          return valIsNumber(popVal);
        } else if (!isObservation(popVal.getName()) ) {
          if ("boolean".equals(popBasis) && popVal.getExpected() instanceof Boolean ) {
            return true ; 
          }
          else {
            return valIsNumber(popVal);
          }
        }
      }
      return false ; 
    });
    return receivedPopulations.size() >= requiredPopulations.size()
        && receivedPopulations.containsAll(requiredPopulations)
        && allValuesMatchPopulationBasis;
  }

  private boolean valIsNumber(TestCasePopulationValue popVal) {
    try {
      if(!isObservation(popVal.getName())){
        Integer.parseInt(popVal.getExpected().toString());
        return true;
      }
      else{
        Float.parseFloat(popVal.getExpected().toString());
        return true;
      }
    } catch (Exception ex) {
      return false;
    }
  }
  
  private boolean isObservation(PopulationType popType ) {
    List<PopulationType> obvs = new ArrayList<>() {{
      add(PopulationType.MEASURE_OBSERVATION);
      add(PopulationType.MEASURE_POPULATION_OBSERVATION);
      add(PopulationType.NUMERATOR_OBSERVATION);
      add(PopulationType.DENOMINATOR_OBSERVATION);
    }};
    return obvs.contains(popType);
  }
}
