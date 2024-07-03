package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
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
public class ProcedureOrder extends DataElement {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime authorDatetime;

  private Code reason;
  private Code anatomicalLocationSite;
  private Integer rank;
  private Code priority;
  private Code negationRationale;
  private List<Entity> requester;
  private String qdmTitle = "Procedure, Order";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.66";
  private String qdmCategory = "procedure";
  private String qdmStatus = "order";
  private String qdmVersion = "5.6";
  private String _type = "QDM::ProcedureOrder";

  public void shiftDates(int shifted) {

    if (this.authorDatetime != null) {
      this.authorDatetime = this.authorDatetime.plusYears(shifted);
    }
  }
}
