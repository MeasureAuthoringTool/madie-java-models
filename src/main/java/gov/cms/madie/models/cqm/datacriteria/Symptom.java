package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Symptom extends DataElement {
  private Interval prevalencePeriod;
  private Code severity;
  private List<Entity> recorder;
  private String qdmTitle = "Symptom";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.116";
  private String qrdaOid = "2.16.840.1.113883.10.20.24.3.136";
  private String qdmCategory = "symptom";
  private String qdmVersion = "5.6";
  private String _type = "QDM::Symptom";
}
