package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import gov.cms.madie.models.cqm.datacriteria.basetypes.LocalDateTimeFormatConstant;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Quantity;
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
public class SubstanceOrder extends DataElement {
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime authorDatetime;

  private Interval relevantPeriod;
  private Code reason;
  private Quantity dosage;
  private Quantity supply;
  private Code frequency;
  private Integer refills;
  private Code route;
  private Code negationRationale;
  private List<Entity> requester;
  private String qdmTitle = "Substance, Order";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.77";
  private String qdmCategory = "substance";
  private String qdmStatus = "order";
  private String qdmVersion = "5.6";
  private String _type = "QDM::SubstanceOrder";

  public void shiftDates(int shifted) {
    this.authorDatetime = shiftDateByYear(this.authorDatetime, shifted);
    this.relevantPeriod = shiftIntervalByYear(this.relevantPeriod, shifted);
  }
}
