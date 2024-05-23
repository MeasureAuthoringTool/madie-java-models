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
@JsonTypeName("RatioPopulationMap")
public class RatioPopulationMap extends PopulationMap {
  @JsonProperty(value = "IPP")
  private StatementReference IPP;

  @JsonProperty(value = "DENOM")
  private StatementReference DENOM;

  @JsonProperty(value = "NUMER")
  private StatementReference NUMER;

  @JsonProperty(value = "NUMEX")
  private StatementReference NUMEX;

  @JsonProperty(value = "DENEX")
  private StatementReference DENEX;
}
