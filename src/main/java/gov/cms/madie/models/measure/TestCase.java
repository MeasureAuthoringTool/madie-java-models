package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gov.cms.madie.models.validators.TestCaseValidationStatusValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TestCaseValidationStatusValidator(enumClass = TestCaseValidationStatus.class)
public class TestCase implements Serializable, Cloneable {
  private String id;
  private Integer caseNumber;
  private String name;

  @NotBlank(
      groups = {ValidationOrder1.class},
      message = "Test Case Title is required.")
  @Length(
      max = 250,
      groups = {ValidationOrder2.class},
      message = "Test Case Title can not be more than 250 characters.")
  private String title;

  @Length(
      max = 250,
      groups = {ValidationOrder1.class},
      message = "Test Case Series can not be more than 250 characters.")
  private String series;

  @Length(
      max = 250,
      groups = {ValidationOrder1.class},
      message = "Test Case Description can not be more than 250 characters.")
  private String description;

  private Instant createdAt;
  private String createdBy;
  private Instant lastModifiedAt;
  private String lastModifiedBy;
  @JsonIgnore private String resourceUri;
  private boolean validResource;
  private boolean createdBeforeVersioning;
  @Getter private String json;

  private UUID patientId;

  private HapiOperationOutcome hapiOperationOutcome;

  @Valid private List<TestCaseGroupPopulation> groupPopulations;

  private String testCaseValidationStatus;

  @GroupSequence({ValidationOrder1.class, ValidationOrder2.class, Default.class})
  public interface ValidationSequence {}

  public interface ValidationOrder1 {}

  public interface ValidationOrder2 {}

  public TestCase deepCopy() {
    return SerializationUtils.clone(this);
  }
}
