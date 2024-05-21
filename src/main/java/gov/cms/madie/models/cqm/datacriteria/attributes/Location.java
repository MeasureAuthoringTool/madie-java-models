package gov.cms.madie.models.cqm.datacriteria.attributes;

import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends Entity {
  private Code locationType;
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.142";
}
