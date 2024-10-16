package gov.cms.madie.models.measure;

import gov.cms.madie.models.validators.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasureObservation {
  private String id;

  @NotBlank(
      message = "Measure Observation definition is required.",
      groups = {Measure.ValidationOrder5.class})
  private String definition;

  private String description;
  private String criteriaReference;

  @NotNull(
      message = "Aggregate Method is required.",
      groups = {Measure.ValidationOrder5.class})
  @EnumValidator(
      enumClass = AggregateMethodType.class,
      message = "Aggregate Method must be a valid Measure Observation Aggregate Method type",
      groups = {Measure.ValidationOrder5.class})
  private String aggregateMethod;
}
