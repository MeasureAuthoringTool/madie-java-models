package gov.cms.madie.models.common;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Identifier {
  @Id
  private List<IdentifierItem> Identifier;
}
