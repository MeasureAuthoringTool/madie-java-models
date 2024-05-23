package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Participation extends DataElement {
  private Interval participationPeriod;
  private String qdmTitle = "Participation";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.130";
  private String qdmCategory = "participation";
  private String qdmVersion = "5.6";
  private String _type = "QDM::Participation";
}
