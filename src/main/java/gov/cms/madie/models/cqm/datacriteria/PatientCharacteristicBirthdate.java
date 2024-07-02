package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.basetypes.LocalDateTimeFormatConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientCharacteristicBirthdate extends DataElement {
  @DateTimeFormat(
      iso = ISO.DATE_TIME,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  private LocalDateTime birthDatetime;

  private String qdmTitle = "Patient Characteristic Birthdate";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.54";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "birthdate";
  private String qdmVersion = "5.6";
  private String _type = "QDM::PatientCharacteristicBirthdate";

  public void shiftDates(int shifted) {

    if (this.birthDatetime != null) {
      this.birthDatetime = this.birthDatetime.plusYears(shifted);
    }
  }
}
