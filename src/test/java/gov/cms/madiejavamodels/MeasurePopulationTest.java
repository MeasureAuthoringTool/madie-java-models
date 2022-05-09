package gov.cms.madiejavamodels;

import gov.cms.madiejavamodels.measure.MeasurePopulation;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class MeasurePopulationTest {
  @Test
  public void testPopulationToCode() {
    assertEquals(MeasurePopulation.INITIAL_POPULATION.toCode(),
      "initial-population");
    assertEquals(MeasurePopulation.NUMERATOR.toCode(),
      "numerator");
    assertEquals(MeasurePopulation.NUMERATOR_EXCLUSION.toCode(),
      "numerator-exclusion");
    assertEquals(MeasurePopulation.DENOMINATOR.toCode(),
      "denominator");
    assertEquals(MeasurePopulation.DENOMINATOR_EXCLUSION.toCode(),
      "denominator-exclusion");
    assertEquals(MeasurePopulation.DENOMINATOR_EXCEPTION.toCode(),
      "denominator-exception");
    assertEquals(MeasurePopulation.MEASURE_POPULATION.toCode(),
      "measure-population");
    assertEquals(MeasurePopulation.MEASURE_POPULATION_EXCLUSION.toCode(),
      "measure-population-exclusion");
    assertEquals(MeasurePopulation.MEASURE_OBSERVATION.toCode(),
      "measure-observation");
  }

  @Test
  public void testPopulationGetDisplay() {
    assertEquals(MeasurePopulation.INITIAL_POPULATION.getDisplay(), "Initial Population");
    assertEquals(MeasurePopulation.NUMERATOR.getDisplay(), "Numerator");
    assertEquals(MeasurePopulation.NUMERATOR_EXCLUSION.getDisplay(), "Numerator Exclusion");
    assertEquals(MeasurePopulation.DENOMINATOR.getDisplay(), "Denominator");
    assertEquals(MeasurePopulation.DENOMINATOR_EXCLUSION.getDisplay(), "Denominator Exclusion");
    assertEquals(MeasurePopulation.DENOMINATOR_EXCEPTION.getDisplay(), "Denominator Exception");
    assertEquals(MeasurePopulation.MEASURE_POPULATION.getDisplay(), "Measure Population");
    assertEquals(MeasurePopulation.MEASURE_POPULATION_EXCLUSION.getDisplay(), "Measure Population Exclusion");
    assertEquals(MeasurePopulation.MEASURE_OBSERVATION.getDisplay(),"Measure Observation");
  }
}
