package gov.cms.madie.models.access;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AclSpecification implements Serializable {

  private String userId;
  private Set<RoleEnum> roles;
}
