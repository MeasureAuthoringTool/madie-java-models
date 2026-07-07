package gov.cms.madie.models.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModelTypeTest {
  @Test
  void testGetModelVersionNumber() {
    assertThat(ModelType.FHIR_4_0_1.getVersionNumber(), is(equalTo("4.0.1")));
    assertThat(ModelType.QDM_5_6.getVersionNumber(), is(equalTo("5.6")));
    assertThat(ModelType.QI_CORE.getVersionNumber(), is(equalTo("4.1.1")));
    assertThat(ModelType.QI_CORE_6_0_0.getVersionNumber(), is(equalTo("6.0.0")));
    assertThat(ModelType.QI_CORE_7_0_0.getVersionNumber(), is(equalTo("7.0.0")));
    assertThat(ModelType.QI_CORE_7_0_2.getVersionNumber(), is(equalTo("7.0.2")));
    assertThat(ModelType.US_CORE_6_1_0.getVersionNumber(), is(equalTo("6.1.0")));
    assertThat(ModelType.US_QUALITY_CORE_0_5_0.getVersionNumber(), is(equalTo("0.5.0")));
  }

  @Test
  void testByShortValueReturnsExpectedModelType() {
    assertThat(ModelType.byShortValue("fhir4"), is(equalTo(ModelType.FHIR_4_0_1)));
    assertThat(ModelType.byShortValue("qicore"), is(equalTo(ModelType.QI_CORE)));
    assertThat(ModelType.byShortValue("qicore6"), is(equalTo(ModelType.QI_CORE_6_0_0)));
    assertThat(ModelType.byShortValue("qicore7"), is(equalTo(ModelType.QI_CORE_7_0_2)));
    assertThat(ModelType.byShortValue("uscore6"), is(equalTo(ModelType.US_CORE_6_1_0)));
    assertThat(
        ModelType.byShortValue("usqualitycore05"), is(equalTo(ModelType.US_QUALITY_CORE_0_5_0)));
    assertThat(ModelType.byShortValue("qdm"), is(equalTo(ModelType.QDM_5_6)));
  }

  @Test
  void testByShortValueReturnsNullForUnknownShortValue() {
    assertThat(ModelType.byShortValue("not-a-model"), is(nullValue()));
  }

  @Test
  void testByShortValueReturnsNullForNullShortValue() {
    assertThat(ModelType.byShortValue(null), is(nullValue()));
  }
}
