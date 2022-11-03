package gov.cms.madie.models.measure;

import java.util.List;
import gov.cms.madie.models.access.AclSpecification;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class ResourceAcl {

  private List<AclSpecification> acls ;
  
}
