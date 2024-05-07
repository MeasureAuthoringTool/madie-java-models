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
public class FamilyHistory extends DataElement {
    private LocalDateTime authorDatetime;
    private Code relationship;
    private List<Entity> recorder;
    private String qdmTitle = "Family History";
    private String hqmfOid = "2.16.840.1.113883.10.20.28.4.111";
    private String qrdaOid = "2.16.840.1.113883.10.20.24.3.12";
    private String qdmCategory = "family_history";
    private String qdmVersion = "5.6";
}