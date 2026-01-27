package gov.cms.madie.models.measure;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CodeConcept implements Serializable {
  private String code;
  private String codeSystem;
  private String display;
  @EqualsAndHashCode.Exclude private String definition;
}
