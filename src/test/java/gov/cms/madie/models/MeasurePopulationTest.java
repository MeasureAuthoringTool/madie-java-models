package gov.cms.madie.models;

import gov.cms.madie.models.measure.PopulationType;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;


public class MeasurePopulationTest {
  @Test
  public void testPopulationToCode() {
    Assert.assertEquals(PopulationType.INITIAL_POPULATION.toCode(),
      "initial-population");
    assertEquals(PopulationType.NUMERATOR.toCode(),
      "numerator");
    assertEquals(PopulationType.NUMERATOR_EXCLUSION.toCode(),
      "numerator-exclusion");
    assertEquals(PopulationType.DENOMINATOR.toCode(),
      "denominator");
    assertEquals(PopulationType.DENOMINATOR_EXCLUSION.toCode(),
      "denominator-exclusion");
    assertEquals(PopulationType.DENOMINATOR_EXCEPTION.toCode(),
      "denominator-exception");
    assertEquals(PopulationType.MEASURE_POPULATION.toCode(),
      "measure-population");
    assertEquals(PopulationType.MEASURE_POPULATION_EXCLUSION.toCode(),
      "measure-population-exclusion");
    assertEquals(PopulationType.MEASURE_OBSERVATION.toCode(),
      "measure-observation");
  }

  @Test
  public void testPopulationGetDisplay() {
    assertEquals(PopulationType.INITIAL_POPULATION.getDisplay(), "Initial Population");
    assertEquals(PopulationType.NUMERATOR.getDisplay(), "Numerator");
    assertEquals(PopulationType.NUMERATOR_EXCLUSION.getDisplay(), "Numerator Exclusion");
    assertEquals(PopulationType.DENOMINATOR.getDisplay(), "Denominator");
    assertEquals(PopulationType.DENOMINATOR_EXCLUSION.getDisplay(), "Denominator Exclusion");
    assertEquals(PopulationType.DENOMINATOR_EXCEPTION.getDisplay(), "Denominator Exception");
    assertEquals(PopulationType.MEASURE_POPULATION.getDisplay(), "Measure Population");
    assertEquals(PopulationType.MEASURE_POPULATION_EXCLUSION.getDisplay(), "Measure Population Exclusion");
    assertEquals(PopulationType.MEASURE_OBSERVATION.getDisplay(),"Measure Observation");
  }
}
