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
public class PatientCharacteristicRace extends DataElement {
  private String qdmTitle = "Patient Characteristic Race";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.59";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "race";
  private String qdmVersion = "5.6";
  private String _type = "QDM::PatientCharacteristicRace";
}
