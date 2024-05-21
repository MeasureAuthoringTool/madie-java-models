package gov.cms.madie.models.cqm.datacriteria.basetypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class DataElement {

  // Codes that describe this datatype.
  private String[] dataElementCodes = {};

  // Optional description.
  private String description;

  // Valueset oid of the specific type.
  private String codeListId;

  private String id = null;

  //  private String codeId;
  //  private String desc;
}
