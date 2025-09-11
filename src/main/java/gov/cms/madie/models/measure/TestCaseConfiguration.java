package gov.cms.madie.models.measure;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class TestCaseConfiguration implements Serializable {
  @Id private String id;
  private boolean sdeIncluded;
  @Default private boolean ravIncluded = true;
  private ManifestExpansion manifestExpansion;
}
