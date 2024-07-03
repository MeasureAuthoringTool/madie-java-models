package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientCharacteristicExpired extends DataElement {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime expiredDatetime;

  private Code cause;
  private String qdmTitle = "Patient Characteristic Expired";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.57";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "expired";
  private String qdmVersion = "5.6";
  private String _type = "QDM::PatientCharacteristicExpired";

  public void shiftDates(int shifted) {

    if (this.expiredDatetime != null) {
      this.expiredDatetime = this.expiredDatetime.plusYears(shifted);
    }
  }
}
