package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Identifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedPerson extends DataElement {
  private Identifier identifier;
  private String linkedPatientId;
  private String qdmTitle = "Related Person";
  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.141";
  private String qdmCategory = "related_person";
  private String qdmVersion = "5.6";
  private String _type = "QDM::RelatedPerson";
}
