package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Quantity;
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
public class EncounterPerformed extends DataElement {

  private LocalDateTime authorDatetime;
  private Code admissionSource;
  private Code clazz;
  private Interval relevantPeriod;
  private Code dischargeDisposition;
  private List<Code> facilityLocations;
  private List<Code> diagnoses;
  private Quantity lengthOfStay;
  private Code priority;
  private List<Entity> participant;
  private Object[] relatedTo;
  private String qdmTitle = "Encounter, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.5";
  private String qdmCategory = "encounter";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
}