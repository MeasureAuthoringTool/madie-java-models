package gov.cms.madie.models.cqm.datacriteria.attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity implements Attribute {
  private DataElement dataElement;
  private String id;
  private Identifier identifier;
  private String qdmVersion = "5.6";
}
