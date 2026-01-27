package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Population implements Serializable {
  @Id
  @NotBlank(message = "Population ID is required.")
  private String id;

  private PopulationType name;
  private String definition;
  private AssociationType associationType;
  private String description;
  private String displayId;
}
