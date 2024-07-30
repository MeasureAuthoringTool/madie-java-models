package gov.cms.madie.models.cqm.datacriteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiagnosisComponent {
  private String qdmVersion = "5.6";
  private String _type = "QDM::DiagnosisComponent";
  private String _id;
  private Code code;
  private Code presentOnAdmissionIndicator;
  private int rank;
}
