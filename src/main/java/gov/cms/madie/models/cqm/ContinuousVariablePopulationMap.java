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
@JsonTypeName("ContinuousVariablePopulationMap")
public class ContinuousVariablePopulationMap extends PopulationMap {
  @JsonProperty(value = "IPP")
  private StatementReference IPP;

  @JsonProperty(value = "MSRPOPL")
  private StatementReference MSRPOPL;

  @JsonProperty(value = "MSRPOPLEX")
  private StatementReference MSRPOPLEX;
}
