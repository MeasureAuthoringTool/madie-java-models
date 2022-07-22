package gov.cms.madie.models.measure;

import gov.cms.madie.models.validators.EnumValidator;
import gov.cms.madie.models.validators.ValidGroupScoringPopulation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidGroupScoringPopulation
public class Group implements GroupScoringPopulation {
  @Id
  private String id;

  @NotNull(
      message = "Scoring is required.",
      groups = {Measure.ValidationOrder5.class})
  @EnumValidator(
      enumClass = MeasureScoring.class,
      message = "Scoring must be a valid MADiE scoring type",
      groups = {Measure.ValidationOrder5.class})
  private String scoring;

  private List<Population> populations;

  private String groupDescription;

  private String improvementNotation;

  private String rateAggregation;

  private List<MeasureGroupTypes> measureGroupTypes;
  
  private String scoringUnit;
  
}
