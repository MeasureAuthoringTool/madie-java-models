package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import gov.cms.madie.models.cqm.datacriteria.basetypes.LocalDateTimeFormatConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CareGoal extends DataElement {

  @DateTimeFormat(iso = ISO.DATE_TIME, pattern = LocalDateTimeFormatConstant.LOCAL_DATE_PATTERN)
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_PATTERN)
  private LocalDate statusDate;

  private Interval relevantPeriod;
  private Object[] relatedTo;
  private Object targetOutcome;
  private List<Entity> performer;
  private String qdmTitle = "Care Goal";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.7";
  private String qdmCategory = "care_goal";
  private String qdmVersion = "5.6";
  private String _type = "QDM::CareGoal";

  public void shiftDates(int shifted) {
    if (this.statusDate != null) {
      this.statusDate = this.statusDate.plusYears(shifted);
    }
    this.relevantPeriod = shiftIntervalByYear(this.relevantPeriod, shifted);
  }
}
