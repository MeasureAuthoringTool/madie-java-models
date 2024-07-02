package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
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

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class LaboratoryTestPerformed extends DataElement {

  @DateTimeFormat(
      iso = ISO.DATE_TIME,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  private LocalDateTime authorDatetime;

  @DateTimeFormat(
      iso = ISO.DATE_TIME,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  private LocalDateTime relevantDatetime;

  private Interval relevantPeriod;
  private Code status;
  private Code method;
  private Object result;

  @DateTimeFormat(
      iso = ISO.DATE_TIME,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN,
      timezone = "UTC")
  private LocalDateTime resultDatetime;

  private Code reason;
  private Interval referenceRange;
  private Code interpretation;
  private Code negationRationale;
  //  private Object[] components;
  private List<Component> components;
  private List<Entity> performer;
  private Object[] relatedTo;
  private String qdmTitle = "Laboratory Test, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.42";
  private String qdmCategory = "laboratory_test";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
  private String _type = "QDM::LaboratoryTestPerformed";

  public void shiftDates(int shifted) {

    if (this.authorDatetime != null) {
      //      ZonedDateTime dt = ZonedDateTime.ofInstant(this.authorDatetime, ZoneId.of("UTC"));
      //      dt.plusYears(shifted);
      //      this.authorDatetime = dt.toInstant();
      //
      this.authorDatetime = this.authorDatetime.plusYears(shifted);
    }
    if (this.relevantDatetime != null) {
      this.relevantDatetime = this.relevantDatetime.plusYears(shifted);
      //      ZonedDateTime dt = ZonedDateTime.ofInstant(this.relevantDatetime, ZoneId.of("UTC"));
      //      dt.plusYears(shifted);
      //      this.authorDatetime = dt.toInstant();
    }
    if (this.resultDatetime != null) {
      this.resultDatetime = this.resultDatetime.plusYears(shifted);

      //      ZonedDateTime dt = ZonedDateTime.ofInstant(this.resultDatetime, ZoneId.of("UTC"));
      //      dt.plusYears(shifted);
      //      this.resultDatetime = dt.toInstant();
    }
    if (this.relevantPeriod != null) {
      Interval changeInterval = this.relevantPeriod;
      if (changeInterval.getLow() != null) {
        changeInterval.setLow(changeInterval.getLow().plusYears(shifted));
        //        ZonedDateTime dt = ZonedDateTime.ofInstant(changeInterval.getLow(),
        // ZoneId.systemDefault());
        //        dt.plusYears(shifted);
        //        changeInterval.setLow(dt.toInstant());
      }
      if (changeInterval.getHigh() != null) {
        changeInterval.setHigh(changeInterval.getHigh().plusYears(shifted));
        //        ZonedDateTime dt =
        //            ZonedDateTime.ofInstant(changeInterval.getHigh(), ZoneId.systemDefault());
        //        dt.plusYears(shifted);
        //        changeInterval.setHigh(dt.toInstant());
      }
      this.relevantPeriod = changeInterval;
    }
  }
}
