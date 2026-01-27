package gov.cms.madie.models.cqm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("CohortPopulationMap")
public class CohortPopulationMap extends PopulationMap {
  @JsonProperty(value = "IPP")
  private StatementReference IPP;
}
