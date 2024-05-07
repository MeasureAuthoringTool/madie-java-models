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
public class ImmunizationOrder extends DataElement {
    private LocalDateTime activeDatetime;
    private LocalDateTime authorDatetime;
    private Quantity dosage;
    private Quantity supply;
    private Code reason;
    private Code route;
    private Code negationRationale;
    private List<Entity> requester;
    private String qdmTitle = "Immunization, Order";
    private String hqmfOid = "2.16.840.1.113883.10.20.28.4.113";
    private String qdmCategory = "immunization";
    private String qdmStatus = "order";
    private String qdmVersion = "5.6";
}