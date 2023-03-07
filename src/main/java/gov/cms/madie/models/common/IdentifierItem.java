package gov.cms.madie.models.common;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class IdentifierItem {
  @Id
  private String use;
  private String system;
  private String value;
}
