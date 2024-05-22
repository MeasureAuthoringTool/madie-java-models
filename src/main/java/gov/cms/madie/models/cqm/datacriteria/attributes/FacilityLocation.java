package gov.cms.madie.models.cqm.datacriteria.attributes;

import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityLocation implements Attribute {

  private Code code;

  private Interval locationPeriod;

  private String qdmVersion = "5.6";
}
