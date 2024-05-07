package gov.cms.madie.models.cqm.datacriteria.attributes;

import gov.cms.madie.models.cqm.datacriteria.attributes.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Identifier implements Attribute {
    private String namingSystem;
    private String value;
    private String qdmVersion = "5.6";

    // CQM validates uniqueness of value with conditions
    // where namingSystem is CQM::Provider::NPI_OID
    // Might need to implement validation logic here
}