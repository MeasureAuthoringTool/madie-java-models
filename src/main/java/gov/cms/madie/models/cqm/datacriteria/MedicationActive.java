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
public class MedicationActive extends DataElement {
  private LocalDateTime relevantDatetime;
  private Interval relevantPeriod;
  private Quantity dosage;
  private Code frequency;
  private Code route;
  private List<Entity> recorder;
  private String qdmTitle = "Medication, Active";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.44";
  private String qdmCategory = "medication";
  private String qdmStatus = "active";
  private String qdmVersion = "5.6";
}