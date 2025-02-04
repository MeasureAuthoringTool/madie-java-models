package gov.cms.madie.models.measure;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasureDefinition {
  @Id private String id;

  @NotBlank(
      message = "Term cannot be null.",
      groups = {Measure.ValidationOrder7.class})
  private String term;

  @NotBlank(
      message = "Definition cannot be null.",
      groups = {Measure.ValidationOrder8.class})
  private String definition;
}
