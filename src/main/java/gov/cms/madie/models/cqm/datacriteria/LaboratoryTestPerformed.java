package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Component;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class LaboratoryTestPerformed extends DataElement {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime authorDatetime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime relevantDatetime;

  private Interval relevantPeriod;
  private Code status;
  private Code method;
  private Object result;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime resultDatetime;

  private Code reason;
  private Interval referenceRange;
  private Code interpretation;
  private Code negationRationale;
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
      this.authorDatetime = this.authorDatetime.plusYears(shifted);
    }
    if (this.relevantDatetime != null) {
      this.relevantDatetime = this.relevantDatetime.plusYears(shifted);
    }
    if (this.resultDatetime != null) {
      this.resultDatetime = this.resultDatetime.plusYears(shifted);
    }
    if (this.relevantPeriod != null) {
      Interval changeInterval = this.relevantPeriod;
      if (changeInterval.getLow() != null) {
        changeInterval.setLow(changeInterval.getLow().plusYears(shifted));
      }
      if (changeInterval.getHigh() != null) {
        changeInterval.setHigh(changeInterval.getHigh().plusYears(shifted));
      }
      this.relevantPeriod = changeInterval;
    }
  }
}
