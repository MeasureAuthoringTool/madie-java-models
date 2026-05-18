package gov.cms.madie.models.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CmsIdFormatterTest {
  @Test
  void padReturnsEmptyForNull() {
    assertThat(CmsIdFormatter.pad(null), is(equalTo("")));
  }

  @Test
  void padDigitsToMakeItFourDigits() {
    assertThat(CmsIdFormatter.pad(1), is(equalTo("0001")));
    assertThat(CmsIdFormatter.pad(20), is(equalTo("0020")));
    assertThat(CmsIdFormatter.pad(999), is(equalTo("0999")));
  }

  @Test
  void formatReturnsEmptyWhenCmsIdIsNullRegardlessOfModel() {
    assertThat(CmsIdFormatter.format(null, "QI-Core v4.1.1"), is(equalTo("")));
    assertThat(CmsIdFormatter.format(null, "QDM v5.6"), is(equalTo("")));
    assertThat(CmsIdFormatter.format(null, null), is(equalTo("")));
  }

  @Test
  void formatReturnsEmptyWhenCmsIdIsZero() {
    assertThat(CmsIdFormatter.format(0, "QI-Core v4.1.1"), is(equalTo("")));
    assertThat(CmsIdFormatter.format(0, "QDM v5.6"), is(equalTo("")));
  }

  @Test
  void formatAppendsFhirSuffixForQiCoreModels() {
    assertThat(CmsIdFormatter.format(2, "QI-Core v4.1.1"), is(equalTo("0002FHIR")));
    assertThat(CmsIdFormatter.format(22, "QI-Core v6.0.0"), is(equalTo("0022FHIR")));
    assertThat(CmsIdFormatter.format(222, "QI-Core v7.0.0"), is(equalTo("0222FHIR")));
    assertThat(CmsIdFormatter.format(2222, "QI-Core v7.0.2"), is(equalTo("2222FHIR")));
  }

  @Test
  void formatOmitsFhirSuffixForQdmModels() {
    assertThat(CmsIdFormatter.format(2, "QDM v5.6"), is(equalTo("0002")));
    assertThat(CmsIdFormatter.format(22, "QDM v5.6"), is(equalTo("0022")));
    assertThat(CmsIdFormatter.format(2222, "QDM v5.6"), is(equalTo("2222")));
  }

  @Test
  void formatOmitsFhirSuffixForNullAndEmptyModel() {
    assertThat(CmsIdFormatter.format(2, null), is(equalTo("0002")));
    assertThat(CmsIdFormatter.format(2222, null), is(equalTo("2222")));
    assertThat(CmsIdFormatter.format(2, ""), is(equalTo("0002")));
    assertEquals("FHIR", CmsIdFormatter.FHIR_SUFFIX);
  }

  @Test
  void formatOmitsFhirSuffixForModelsThatDoNotStartWithQiCorePrefix() {
    assertThat(CmsIdFormatter.format(2, "FHIR"), is(equalTo("0002")));
    assertThat(CmsIdFormatter.format(2, "Some Other Model"), is(equalTo("0002")));
    assertThat(CmsIdFormatter.format(2, "qi-core v4.1.1"), is(equalTo("0002")));
  }

  @Test
  void formatLeavesValuesWiderThanFourDigitsUnchangedAndStillAppendsFhir() {
    assertThat(CmsIdFormatter.format(12345, "QI-Core v4.1.1"), is(equalTo("12345FHIR")));
    assertThat(CmsIdFormatter.format(12345, "QDM v5.6"), is(equalTo("12345")));
  }
}
