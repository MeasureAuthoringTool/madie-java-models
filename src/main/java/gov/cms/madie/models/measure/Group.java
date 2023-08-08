package gov.cms.madie.models.measure;

import gov.cms.madie.models.validators.EnumValidator;
import gov.cms.madie.models.validators.ValidGroupScoringPopulation;
import gov.cms.madie.models.validators.ValidMeasureObservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ValidGroupScoringPopulation
@ValidMeasureObservation
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

  @Valid
  private List<Population> populations;

  private List<MeasureObservation> measureObservations;

  private String groupDescription;

  private String improvementNotation;

  private String rateAggregation;

  //@NotEmpty
  private List<MeasureGroupTypes> measureGroupTypes;

  private Object scoringUnit;

  private String populationBasis;

}
