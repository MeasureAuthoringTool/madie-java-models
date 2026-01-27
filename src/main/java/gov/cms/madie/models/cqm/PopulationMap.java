package gov.cms.madie.models.cqm;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ContinuousVariablePopulationMap.class),
  @JsonSubTypes.Type(value = ProportionPopulationMap.class),
  @JsonSubTypes.Type(value = RatioPopulationMap.class),
  @JsonSubTypes.Type(value = CohortPopulationMap.class)
})
public abstract class PopulationMap {}
