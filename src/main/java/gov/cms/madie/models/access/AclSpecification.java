package gov.cms.madie.models.access;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AclSpecification {

  private String userId;
  private Set<RoleEnum> roles;
}
