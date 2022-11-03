package gov.cms.madie.models.measure;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class PopulationTypeTest {

  @Test
  void fromDisplay() {
    assertThat(PopulationType.fromDisplay("Initial Population"), is(equalTo(PopulationType.INITIAL_POPULATION)));
    assertThat(PopulationType.fromDisplay("Numerator"), is(equalTo(PopulationType.NUMERATOR)));
    assertThat(PopulationType.fromDisplay("Denominator"), is(equalTo(PopulationType.DENOMINATOR)));
    assertThat(PopulationType.fromDisplay("Measure Population"), is(equalTo(PopulationType.MEASURE_POPULATION)));
    assertThat(PopulationType.fromDisplay("Measure Observation"), is(equalTo(PopulationType.MEASURE_OBSERVATION)));
    assertThat(PopulationType.fromDisplay("FLIBBERTY"), is(nullValue()));
  }

  @Test
  void fromCode() {
    assertThat(PopulationType.fromCode("initial-population"), is(equalTo(PopulationType.INITIAL_POPULATION)));
    assertThat(PopulationType.fromCode("numerator"), is(equalTo(PopulationType.NUMERATOR)));
    assertThat(PopulationType.fromCode("denominator"), is(equalTo(PopulationType.DENOMINATOR)));
    assertThat(PopulationType.fromCode("measure-population"), is(equalTo(PopulationType.MEASURE_POPULATION)));
    assertThat(PopulationType.fromCode("measure-observation"), is(equalTo(PopulationType.MEASURE_OBSERVATION)));
    assertThat(PopulationType.fromCode("flibberty"), is(nullValue()));
  }
}
