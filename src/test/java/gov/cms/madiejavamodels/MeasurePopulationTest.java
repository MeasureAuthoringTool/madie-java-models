package gov.cms.madiejavamodels;

import gov.cms.madiejavamodels.measure.MeasurePopulation;
import org.junit.Test;


public class MeasurePopulationTest {
  @Test
  public void testPopulationToCode() {
    assert MeasurePopulation.INITIAL_POPULATION.toCode() == "initial-population";
    assert MeasurePopulation.NUMERATOR.toCode() == "numerator";
    assert MeasurePopulation.NUMERATOR_EXCLUSION.toCode() == "numerator-exclusion";
    assert MeasurePopulation.DENOMINATOR.toCode() == "denominator";
    assert MeasurePopulation.DENOMINATOR_EXCLUSION.toCode() == "denominator-exclusion";
    assert MeasurePopulation.DENOMINATOR_EXCEPTION.toCode() == "denominator-exception";
    assert MeasurePopulation.MEASURE_POPULATION.toCode() == "measure-population";
    assert MeasurePopulation.MEASURE_POPULATION_EXCLUSION.toCode() == "measure-population-exclusion";
    assert MeasurePopulation.MEASURE_OBSERVATION.toCode() == "measure-observation";
  }

  @Test
  public void testPopulationGetDisplay() {
    assert MeasurePopulation.INITIAL_POPULATION.getDisplay() == "Initial Population";
    assert MeasurePopulation.NUMERATOR.getDisplay() == "Numerator";
    assert MeasurePopulation.NUMERATOR_EXCLUSION.getDisplay() == "Numerator Exclusion";
    assert MeasurePopulation.DENOMINATOR.getDisplay() == "Denominator";
    assert MeasurePopulation.DENOMINATOR_EXCLUSION.getDisplay() == "Denominator Exclusion";
    assert MeasurePopulation.DENOMINATOR_EXCEPTION.getDisplay() == "Denominator Exception";
    assert MeasurePopulation.MEASURE_POPULATION.getDisplay() == "Measure Population";
    assert MeasurePopulation.MEASURE_POPULATION_EXCLUSION.getDisplay() == "Measure Population Exclusion";
    assert MeasurePopulation.MEASURE_OBSERVATION.getDisplay() == "Measure Observation";
  }
}
