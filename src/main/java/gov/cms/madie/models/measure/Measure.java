package gov.cms.madie.models.measure;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.common.Version;
import gov.cms.madie.models.utils.VersionJsonSerializer;
import gov.cms.madie.models.validators.EnumValidator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Measure extends ResourceAcl {

  @Id private String id;

  private String measureHumanReadableId;

  @NotBlank(
    groups = {ValidationOrder1.class},
    message = "Measure Set ID is required.")
  private String measureSetId;

  @JsonSerialize(using = VersionJsonSerializer.VersionSerializer.class)
  @JsonDeserialize(using = VersionJsonSerializer.VersionDeserializer.class)
  private Version version;
  private String state;


  @Indexed(unique = true)
  @NotBlank(
      groups = {ValidationOrder1.class},
      message = "Measure Library Name is required.")
  @Pattern(
      regexp = "^[A-Z][a-zA-Z0-9]*$",
      groups = {
        ValidationOrder2.class,
      },
      message = "Measure Library Name is invalid.")
  private String cqlLibraryName;

  @NotBlank(
      groups = {ValidationOrder6.class},
      message = "eCQM Abbreviated Title is required.")
  @Length(
      min = 1,
      max = 32,
      groups = {ValidationOrder7.class},
      message = "eCQM Abbreviated Title cannot be more than 32 characters.")
  private String ecqmTitle;

  @NotBlank(
      groups = {ValidationOrder1.class},
      message = "Measure Name is required.")
  @Length(
      min = 1,
      max = 500,
      groups = {ValidationOrder2.class},
      message = "Measure Name can not be more than 500 characters.")
  @Pattern(
      regexp = "^[^_]+$",
      groups = {ValidationOrder3.class},
      message = "Measure Name can not contain underscores.")
  @Pattern(
      regexp = ".*[a-zA-Z]+.*",
      groups = {ValidationOrder4.class},
      message = "A measure name must contain at least one letter.")
  private String measureName;

  private boolean active = true;
  // TODO: determine if theres a way to set this from backend or if we should always trust user
  // input for this field
  private boolean cqlErrors;
  private String cql;
  private String elmJson;
  @Transient private String elmXml;
  private List<TestCase> testCases;
  @Valid private List<Group> groups;
  private Instant createdAt;
  private String createdBy;
  private Instant lastModifiedAt;
  private String lastModifiedBy;
  private Date measurementPeriodStart;
  private Date measurementPeriodEnd;

  @EnumValidator(
      enumClass = ModelType.class,
      message = "MADiE was unable to complete your request, please try again.",
      groups = {ValidationOrder5.class})
  private String model;

  private MeasureMetaData measureMetaData = new MeasureMetaData();

  @NotBlank(
      groups = {ValidationOrder1.class},
      message = "Version ID is required.")
  private String versionId;
  private String cmsId;

  @GroupSequence({
    Measure.ValidationOrder1.class,
    Measure.ValidationOrder2.class,
    Measure.ValidationOrder3.class,
    Measure.ValidationOrder4.class,
    Measure.ValidationOrder5.class,
    Measure.ValidationOrder6.class,
    Measure.ValidationOrder7.class,
    Measure.ValidationOrder8.class,
    Default.class
  })
  public interface ValidationSequence {}

  public interface ValidationOrder1 {}

  public interface ValidationOrder2 {}

  public interface ValidationOrder3 {}

  public interface ValidationOrder4 {}

  public interface ValidationOrder5 {}

  public interface ValidationOrder6 {}

  public interface ValidationOrder7 {}

  public interface ValidationOrder8 {}
}
