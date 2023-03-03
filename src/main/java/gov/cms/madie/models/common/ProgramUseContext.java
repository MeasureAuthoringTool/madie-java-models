package gov.cms.madie.models.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProgramUseContext {
  private String codeSystem;
  private String code;
  private String display;
}
