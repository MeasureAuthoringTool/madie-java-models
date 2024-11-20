package gov.cms.madie.models.access;

import java.util.Set;

import lombok.Data;

@Data
public class AclSpecification {

  private String userId;
  private Set<RoleEnum> roles;
}
