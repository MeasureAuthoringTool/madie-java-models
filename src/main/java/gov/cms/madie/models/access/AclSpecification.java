package gov.cms.madie.models.access;

import java.util.List;
import lombok.Data;

@Data
public class AclSpecification {

  private String userId ;
  private List<RoleEnum> roles ; 

}
