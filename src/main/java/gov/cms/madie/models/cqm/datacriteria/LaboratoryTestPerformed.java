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

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryTestPerformed extends DataElement {
  private LocalDateTime authorDatetime;
  private LocalDateTime relevantDatetime;
  private Interval relevantPeriod;
  private Code status;
  private Code method;
  private Object result;
  private LocalDateTime resultDatetime;
  private Code reason;
  private Interval referenceRange;
  private Code interpretation;
  private Code negationRationale;
  private Object[] components;
  private Entity performer;
  private Object[] relatedTo;
  private String qdmTitle = "Laboratory Test, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.42";
  private String qdmCategory = "laboratory_test";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
  private String _type = "QDM::LaboratoryTestPerformed";
}
