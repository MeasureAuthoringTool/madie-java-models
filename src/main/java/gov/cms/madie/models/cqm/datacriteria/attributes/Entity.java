package gov.cms.madie.models.cqm.datacriteria.attributes;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Entity implements Attribute {
  private DataElement dataElement;
  private String id;
  private Identifier identifier;
  private String qdmVersion = "5.6";
}
