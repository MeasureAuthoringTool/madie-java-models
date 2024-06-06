package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AllergyIntolerance extends DataElement {
  private LocalDateTime authorDatetime;
  private Interval prevalencePeriod;
  private Code type;
  private Code severity;
  private List<Entity> recorder;
  private String qdmTitle = "Allergy/Intolerance";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.119";
  private String qdmCategory = "allergy";
  private String qdmStatus = "intolerance";
  private String qdmVersion = "5.6";
  private String _type = "QDM::AllergyIntolerance";
}
