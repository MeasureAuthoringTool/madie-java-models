package gov.cms.madie.models.library;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.validators.EnumValidator;
import gov.cms.madie.models.validators.ValidLibraryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ValidLibraryName
public class CqlLibraryDraft {

  @NotNull(message = "Library name is required.")
  @NotBlank(
      groups = {CqlLibrary.ValidationOrder1.class},
      message = "Library name is required.")
  @Size(
      max = 64,
      groups = {CqlLibrary.ValidationOrder2.class},
      message = "Library name cannot be more than 64 characters.")
  private String cqlLibraryName;

  @EnumValidator(
      enumClass = ModelType.class,
      message = "Model must be one of the supported types in MADiE.",
      groups = {CqlLibrary.ValidationOrder4.class})
  private String model;

  private String cql;
}
