package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity extends Entity {

  private String hqmfOid = "2.16.840.1.113883.10.20.28.4.136";

  private String qrdaOid = "2.16.840.1.113883.10.20.24.3.161";
}
