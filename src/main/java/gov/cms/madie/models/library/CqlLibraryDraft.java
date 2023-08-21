package gov.cms.madie.models.library;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CqlLibraryDraft {

  @NotNull(message = "Library name is required.")
  @NotBlank(
      groups = {CqlLibrary.ValidationOrder1.class},
      message = "Library name is required.")
  @Size(
      max = 255,
      groups = {CqlLibrary.ValidationOrder2.class},
      message = "Library name cannot be more than 255 characters.")
  @Pattern(
      regexp = "^[A-Z][a-zA-Z0-9]*$",
      groups = {CqlLibrary.ValidationOrder3.class},
      message =
          "Library name must start with an upper case letter, "
              + "followed by alpha-numeric character(s) and must not contain "
              + "spaces or other special characters.")
  private String cqlLibraryName;
  private String cql;
}
