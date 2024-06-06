package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticStudyRecommended extends DataElement {

  private LocalDateTime authorDatetime;
  private Code negationRationale;
  private List<Entity> requester;
  private String qdmTitle = "Diagnostic Study, Recommended";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.24";
  private String qrdaOid = "2.16.840.1.113883.10.20.24.3.19";
  private String qdmCategory = "diagnostic_study";
  private String qdmStatus = "recommended";
  private String qdmVersion = "5.6";
  private String _type = "QDM::DiagnosticStudyRecommended";
}
