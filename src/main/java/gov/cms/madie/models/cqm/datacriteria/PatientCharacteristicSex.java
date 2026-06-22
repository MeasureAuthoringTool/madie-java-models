package gov.cms.madie.models.cqm.datacriteria;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCharacteristicSex extends DataElement {
  private String qdmTitle = "Patient Characteristic Sex";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.55";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "gender";
  private String qdmVersion = "5.6";

  @JsonProperty("_type")
  private String _type = "QDM::PatientCharacteristicSex";

  public void shiftDates(int shifted) {
    // GAK MAT-8269.. No implementation for this data type
  }
}
