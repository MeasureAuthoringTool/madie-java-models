package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCharacteristicExpired extends DataElement {
  private LocalDateTime expiredDatetime;
  private Code cause;
  private String qdmTitle = "Patient Characteristic Expired";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.57";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "expired";
  private String qdmVersion = "5.6";
}
