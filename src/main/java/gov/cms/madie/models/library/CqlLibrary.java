package gov.cms.madie.models.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import gov.cms.madie.models.validators.EnumValidator;
import gov.cms.madie.models.validators.ValidLibraryName;
import gov.cms.madie.models.utils.VersionJsonSerializer;
import gov.cms.madie.models.common.ModelType;
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
@ValidLibraryName
public class CqlLibrary {
  @Id private String id;

  @NotBlank(
      groups = {CqlLibrary.ValidationOrder1.class},
      message = "Library Set ID is required.")
  private String librarySetId;

  @NotNull(message = "Library name is required.")
  @NotBlank(
      groups = {ValidationOrder1.class},
      message = "Library name is required.")
  @Size(
      max = 64,
      groups = {ValidationOrder2.class},
      message = "Library name cannot be more than 64 characters.")
  @Indexed
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
  private boolean active = true;
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

  @Transient private LibrarySet librarySet;

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
