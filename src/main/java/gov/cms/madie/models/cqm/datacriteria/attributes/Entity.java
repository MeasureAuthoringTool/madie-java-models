package gov.cms.madie.models.cqm.datacriteria.attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import gov.cms.madie.models.cqm.datacriteria.PatientEntity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type",
    visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = CarePartner.class, name = "QDM::CarePartner"),
  @JsonSubTypes.Type(value = Location.class, name = "QDM::Location"),
  @JsonSubTypes.Type(value = Organization.class, name = "QDM::Organization"),
  @JsonSubTypes.Type(value = PatientEntity.class, name = "QDM::PatientEntity"),
  @JsonSubTypes.Type(value = Practitioner.class, name = "QDM::Practitioner")
})
public class Entity implements Attribute {
  private DataElement dataElement;
  private String id;
  private Identifier identifier;
  private String qdmVersion = "5.6";
}
