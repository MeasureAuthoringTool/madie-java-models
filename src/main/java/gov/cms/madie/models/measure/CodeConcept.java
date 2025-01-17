package gov.cms.madie.models.measure;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CodeConcept {
  private String code;
  private String codeSystem;
  private String display;
  @EqualsAndHashCode.Exclude private String definition;
}
