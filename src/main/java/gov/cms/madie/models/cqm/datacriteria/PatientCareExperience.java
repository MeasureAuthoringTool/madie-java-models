package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
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
public class PatientCareExperience extends DataElement {
  private LocalDateTime authorDatetime;
  private List<Entity> recorder;
  private String qdmTitle = "Patient Care Experience";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.52";
  private String qdmCategory = "care_experience";
  private String qdmVersion = "5.6";
}
