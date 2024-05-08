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
public class CommunicationPerformed extends DataElement {
    private LocalDateTime authorDatetime;
    private Code category;
    private Code medium;
    private List<Entity> sender;
    private List<Entity> recipient;
    private List relatedTo;
    private LocalDateTime sentDatetime;
    private LocalDateTime receivedDatetime;
    private Code negationRationale;
    private String qdmTitle = "Communication, Performed";
    private String hqmfOid = "2.16.840.1.113883.10.20.28.4.132";
    private String qdmCategory = "communication";
    private String qdmStatus = "performed";
    private String qdmVersion = "5.6";
}