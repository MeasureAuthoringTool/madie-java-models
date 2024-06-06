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
public class AssessmentPerformed extends DataElement {
  private LocalDateTime authorDatetime;
  private LocalDateTime relevantDatetime;
  private Interval relevantPeriod;
  private Code negationRationale;
  private Code reason;
  private Code method;
  private Object result;
  private Code interpretation;
  private Object[] components;
  private Object[] relatedTo;
  private Entity performer;
  private String qdmTitle = "Assessment, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.117";
  private String qdmCategory = "assessment";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
  private String _type = "QDM::AssessmentPerformed";
}
