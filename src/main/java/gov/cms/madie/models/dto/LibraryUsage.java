package gov.cms.madie.models.dto;

import gov.cms.madie.models.common.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUsage {
  private String name;
  // TODO: getting version in string would be beneficial
  private Version version;
  private String owner;
}
