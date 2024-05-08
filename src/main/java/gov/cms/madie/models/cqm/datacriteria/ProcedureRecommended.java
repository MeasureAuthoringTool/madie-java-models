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
public class ProcedureRecommended extends DataElement {
    private LocalDateTime authorDatetime;
    private Code reason;
    private Code anatomicalLocationSite;
    private Integer rank;
    private List<Entity> requester;
    private Code negationRationale;
    private String qdmTitle = "Procedure, Recommended";
    private String hqmfOid = "2.16.840.1.113883.10.20.28.4.68";
    private String qdmCategory = "procedure";
    private String qdmStatus = "recommended";
    private String qdmVersion = "5.6";
}