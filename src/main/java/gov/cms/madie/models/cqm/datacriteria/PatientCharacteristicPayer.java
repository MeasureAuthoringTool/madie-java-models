package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCharacteristicPayer extends DataElement {
  private Interval relevantPeriod;
  private String qdmTitle = "Patient Characteristic Payer";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.58";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "payer";
  private String qdmVersion = "5.6";
  private String _type = "QDM::PatientCharacteristicPayer";

  public void shiftDates(int shifted) {

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
