package gov.cms.madie.models.measure;

import java.util.List;
import gov.cms.madie.models.access.AclSpecification;
import lombok.Data;

@Data
public abstract class ResourceAcl {

  private List<AclSpecification> acls ;
  
}
