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
public class MedicationDispensed extends DataElement {
  private LocalDateTime authorDatetime;
  private LocalDateTime relevantDatetime;
  private Interval relevantPeriod;
  private Integer refills;
  private Quantity dosage;
  private Quantity supply;
  private Code frequency;
  private Integer daysSupplied;
  private Code route;
  private List<Entity> prescriber;
  private List<Entity> dispenser;
  private Code negationRationale;
  private List<Object> relatedTo;
  private String qdmTitle = "Medication, Dispensed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.49";
  private String qdmCategory = "medication";
  private String qdmStatus = "dispensed";
  private String qdmVersion = "5.6";
}
