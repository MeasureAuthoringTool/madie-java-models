package gov.cms.madie.models.cqm.datacriteria;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import gov.cms.madie.models.cqm.datacriteria.attributes.Entity;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Code;
import gov.cms.madie.models.cqm.datacriteria.basetypes.Quantity;
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
public class SubstanceRecommended extends DataElement {
    private LocalDateTime authorDatetime;
    private Code reason;
    private Quantity dosage;
    private Code frequency;
    private Integer refills;
    private Code route;
    private Code negationRationale;
    private List<Entity> requester;
    private String qdmTitle = "Substance, Recommended";
    private String hqmfOid = "2.16.840.1.113883.10.20.28.4.78";
    private String qdmCategory = "substance";
    private String qdmStatus = "recommended";
    private String qdmVersion = "5.6";
}