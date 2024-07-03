package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
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
public class EncounterPerformed extends DataElement {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime authorDatetime;

  private Code admissionSource;
  // The cqm javascript code uses "class" here. We may need a conversion like in cqm-models to
  // handle this.
  private Code clazz;
  private Interval relevantPeriod;
  private Code dischargeDisposition;
  private List<Code> facilityLocations;
  private List<Code> diagnoses;
  private Quantity lengthOfStay;
  private Code priority;
  private List<Entity> participant;
  private Object[] relatedTo;
  private String qdmTitle = "Encounter, Performed";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.5";
  private String qdmCategory = "encounter";
  private String qdmStatus = "performed";
  private String qdmVersion = "5.6";
  private String _type = "QDM::EncounterPerformed";

  public void shiftDates(int shifted) {

    if (this.authorDatetime != null) {
      this.authorDatetime = this.authorDatetime.plusYears(shifted);
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
