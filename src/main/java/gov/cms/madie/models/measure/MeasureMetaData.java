package gov.cms.madie.models.measure;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
public class MeasureMetaData {
  @NotBlank(message = "Steward is required.")
  private String steward;
  @NotEmpty(message = "At least 1 developer is required.")
  private List<String> developers;
  private String description;
  private String copyright;
  private String disclaimer;
  private String rationale;
  private String guidance;
}
