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
public class PhysicalExamPerformed extends DataElement {
  private LocalDateTime authorDatetime;
  private LocalDateTime relevantDatetime;
  private Interval relevantPeriod;
  private Code reason;
  private Code method;
  private Object result;
  private Code anatomicalLocationSite;
  private Code negationRationale;
  private Object[] components;
  private List<Entity> performer;
  private Object[] relatedTo;
  private String qdmTitle = "Physical Exam, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.62";
  private String qdmCategory = "physical_exam";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
}