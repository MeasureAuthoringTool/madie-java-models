package gov.cms.madie.models.measure;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Reference implements Serializable {
  @Id private String id;
  private String referenceText;

  @NotBlank(
      message = "Reference type cannot be null.",
      groups = {Measure.ValidationOrder5.class})
  private String referenceType;
}
