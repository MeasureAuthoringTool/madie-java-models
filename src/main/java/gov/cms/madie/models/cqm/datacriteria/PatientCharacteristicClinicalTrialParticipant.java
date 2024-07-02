package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Interval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientCharacteristicClinicalTrialParticipant extends DataElement {
  private Code reason;
  private Interval relevantPeriod;
  private String qdmTitle = "Patient Characteristic Clinical Trial Participant";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.6";
  private String qrdaOid = "2.16.840.1.113883.10.20.24.3.51";
  private String qdmCategory = "patient_characteristic";
  private String qdmStatus = "clinical_trial_participant";
  private String qdmVersion = "5.6";
  private String _type = "QDM::PatientCharacteristicClinicalTrialParticipant";

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
