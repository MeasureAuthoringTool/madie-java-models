package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCharacteristicEthnicity extends DataElement {
  private String qdmTitle = "Patient Characteristic Ethnicity";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.56";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "ethnicity";
  private String qdmVersion = "5.6";
}
