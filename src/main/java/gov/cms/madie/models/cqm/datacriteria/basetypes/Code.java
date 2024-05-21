package gov.cms.madie.models.cqm.datacriteria.basetypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Code {
  private String code;
  private String display;
  private String system;
  private String version;
}
