package gov.cms.madie.models.access;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AclOperation {
  @NotEmpty private List<AclSpecification> acls;
  @NotNull private AclAction action;

  public enum AclAction {
    GRANT,
    REVOKE
  }
}
