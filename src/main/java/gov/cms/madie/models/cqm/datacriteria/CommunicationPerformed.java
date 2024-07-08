package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.basetypes.LocalDateTimeFormatConstant;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunicationPerformed extends DataElement {
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime authorDatetime;

  private Code category;
  private Code medium;
  private List<Entity> sender;
  private List<Entity> recipient;
  private List relatedTo;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime sentDatetime;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime receivedDatetime;

  private Code negationRationale;
  private String qdmTitle = "Communication, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.132";
  private String qdmCategory = "communication";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
  private String _type = "QDM::CommunicationPerformed";

  public void shiftDates(int shifted) {
    this.authorDatetime = shiftDateByYear(this.authorDatetime, shifted);
    this.sentDatetime = shiftDateByYear(this.sentDatetime, shifted);
    this.receivedDatetime = shiftDateByYear(this.receivedDatetime, shifted);
  }
}
