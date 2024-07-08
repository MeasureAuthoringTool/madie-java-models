package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.attributes.FacilityLocation;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Component;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import gov.cms.madie.models.cqm.datacriteria.basetypes.LocalDateTimeFormatConstant;
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
public class DiagnosticStudyPerformed extends DataElement {
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime authorDatetime;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime relevantDatetime;

  private Interval relevantPeriod;
  private Code reason;
  private String result;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime resultDatetime;

  private Code interpretation;
  private Code status;
  private Code method;
  private FacilityLocation facilityLocation;
  private Code negationRationale;
  private List<Component> components;
  private List<Entity> performer;
  private Object[] relatedTo;
  private String qdmTitle = "Diagnostic Study, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.23";
  private String qdmCategory = "diagnostic_study";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
  private String _type = "QDM::DiagnosticStudyPerformed";

  public void shiftDates(int shifted) {
    this.authorDatetime = shiftDateByYear(this.authorDatetime, shifted);
    this.relevantDatetime = shiftDateByYear(this.relevantDatetime, shifted);
    this.resultDatetime = shiftDateByYear(this.resultDatetime, shifted);
    this.relevantPeriod = shiftIntervalByYear(this.relevantPeriod, shifted);
    if (this.facilityLocation != null) {
      this.facilityLocation.setLocationPeriod(
          shiftIntervalByYear(this.facilityLocation.getLocationPeriod(), shifted));
    }
  }
}
