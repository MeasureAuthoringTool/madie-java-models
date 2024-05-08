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
public class PatientCharacteristicBirthdate extends DataElement {
    private LocalDateTime birthDatetime;
    private String qdmTitle = "Patient Characteristic Birthdate";
    private String hqmfOid = "2.16.840.1.113883.10.20.28.4.54";
    private String qdmCategory = "patient_characteristic";
    private String qdmStatus = "birthdate";
    private String qdmVersion = "5.6";
}