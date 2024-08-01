package gov.cms.madie.models.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncludedLibrary {
  private String name;
  private String version;
}
