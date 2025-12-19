package gov.cms.madie.models.measure;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ManifestExpansion implements Serializable {
  private String fullUrl;
  private String id;
  private String title;
}
