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
public class AssessmentRecommended extends DataElement {
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime authorDatetime;

  private Code negationRationale;
  private Code reason;
  private List<Entity> requester;
  private String qdmTitle = "Assessment, Recommended";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.118";
  private String qdmCategory = "assessment";
  private String qdmStatus = "recommended";
  private String qdmVersion = "5.6";
  private String _type = "QDM::AssessmentRecommended";

  public void shiftDates(int shifted) {
    this.authorDatetime = shiftDateByYear(this.authorDatetime, shifted);
  }
}
