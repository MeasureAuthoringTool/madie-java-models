package gov.cms.madie.models.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import gov.cms.madie.models.validators.EnumValidator;
import gov.cms.madie.models.utils.VersionJsonSerializer;
import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.common.ProgramUseContext;
import gov.cms.madie.models.common.Version;

import java.time.Instant;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CqlLibrary {
  @Id private String id;

  @NotNull(message = "Library name is required.")
  @NotBlank(
      groups = {ValidationOrder1.class},
      message = "Library name is required.")
  @Size(
      max = 255,
      groups = {ValidationOrder2.class},
      message = "Library name cannot be more than 255 characters.")
  @Pattern(
      regexp = "^[A-Z][a-zA-Z0-9]*$",
      groups = {ValidationOrder3.class},
      message =
          "Library name must start with an upper case letter, "
              + "followed by alpha-numeric character(s) and must not contain "
              + "spaces or other special characters.")
  @Indexed(unique = true, name = "UniqueCqlLibraryName")
  private String cqlLibraryName;

  @NotNull(
      groups = {ValidationOrder1.class},
      message = "Model is required.")
  @EnumValidator(
      enumClass = ModelType.class,
      message = "Model must be one of the supported types in MADiE.",
      groups = {ValidationOrder4.class})
  private String model;

  @JsonSerialize(using = VersionJsonSerializer.VersionSerializer.class)
  @JsonDeserialize(using = VersionJsonSerializer.VersionDeserializer.class)
  private Version version;

  private boolean draft;
  private String groupId;
  private boolean cqlErrors;
  private String cql;
  private String elmJson;
  private String elmXml;
  private Instant createdAt;
  private String createdBy;
  private Instant lastModifiedAt;
  private String lastModifiedBy;
  private String publisher;
  private String description;
  private boolean experimental;
  private ProgramUseContext programUseContext;

  @GroupSequence({
    CqlLibrary.ValidationOrder1.class,
    CqlLibrary.ValidationOrder2.class,
    CqlLibrary.ValidationOrder3.class,
    CqlLibrary.ValidationOrder4.class,
    Default.class
  })
  public interface ValidationSequence {}

  public interface ValidationOrder1 {}

  public interface ValidationOrder2 {}

  public interface ValidationOrder3 {}

  public interface ValidationOrder4 {}
}
