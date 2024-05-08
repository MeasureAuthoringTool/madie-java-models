package gov.cms.madie.models.cqm.datacriteria.attributes;

import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Practitioner extends Entity {
    private Code role;
    private Code specialty;
    private Code qualification;
    private String hqmfOid = "2.16.840.1.113883.10.20.28.4.137";
    private String qrdaOid = "2.16.840.1.113883.10.20.24.3.162";
}