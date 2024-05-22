package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stratification {
  private String id;

  private String description;

  private String cqlDefinition;

  // TODO: can be removed at the end of MAT-5852
  @Deprecated private PopulationType association;

  private List<PopulationType> associations;
}
