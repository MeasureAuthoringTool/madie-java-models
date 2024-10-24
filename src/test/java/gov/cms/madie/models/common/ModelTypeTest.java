package gov.cms.madie.models.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModelTypeTest {
  @Test
  void testGetModelVersionNumber() {
    assertThat(ModelType.QDM_5_6.getVersionNumber(), is(equalTo("5.6")));
    assertThat(ModelType.QI_CORE.getVersionNumber(), is(equalTo("4.1.1")));
    assertThat(ModelType.QI_CORE_6_0_0.getVersionNumber(), is(equalTo("6.0.0")));
  }
}
