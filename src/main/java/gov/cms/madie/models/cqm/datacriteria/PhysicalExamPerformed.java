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

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhysicalExamPerformed extends DataElement {
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
  private Code method;
  private Object result;
  private Code anatomicalLocationSite;
  private Code negationRationale;
  private List<Component> components;
  private List<Entity> performer;
  private Object[] relatedTo;
  private String qdmTitle = "Physical Exam, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.62";
  private String qdmCategory = "physical_exam";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
  private String _type = "QDM::PhysicalExamPerformed";

  public void shiftDates(int shifted) {
    this.authorDatetime = shiftDateByYear(this.authorDatetime, shifted);
    this.relevantDatetime = shiftDateByYear(this.relevantDatetime, shifted);
    this.relevantPeriod = shiftIntervalByYear(this.relevantPeriod, shifted);
    this.result = shiftDateByYearForObject(this.result, shifted);
    if (!CollectionUtils.isEmpty(this.components)) {
      this.components.stream()
          .forEach(
              component -> {
                component.setResult(shiftDateByYearForObject(component.getResult(), shifted));
              });
    }
  }
}
