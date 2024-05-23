package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCharacteristic extends DataElement {
  private LocalDateTime authorDatetime;
  private String qdmTitle = "Patient Characteristic";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.53";
  private String qdmCategory = "patient_characteristic";
  private String qdmVersion = "5.6";
  private String _type = "QDM::PatientCharacteristic";
}
