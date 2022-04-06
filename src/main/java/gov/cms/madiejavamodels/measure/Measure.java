package gov.cms.madiejavamodels.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Measure {

  @Id
  private String id;

  private String measureHumanReadableId;
  private String measureSetId;
  private String version;
  private String revisionNumber;
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

  private String cql;
  private String elmJson;
  private List<TestCase> testCases;
  @Valid
  private List<Group> groups;
  private Instant createdAt;
  private String createdBy;
  private Instant lastModifiedAt;
  private String lastModifiedBy;
  private LocalDate measurementPeriodStart;
  private LocalDate measurementPeriodEnd;

  private String model;

  @NotBlank(
    groups = {ValidationOrder5.class},
    message = "Measure Scoring is required.")
  private String measureScoring;

  private MeasureMetaData measureMetaData = new MeasureMetaData();

  @GroupSequence({
    ValidationOrder1.class,
    ValidationOrder2.class,
    ValidationOrder3.class,
    ValidationOrder4.class,
    ValidationOrder5.class,
    Default.class
  })
  public interface ValidationSequence {
  }

  public interface ValidationOrder1 {
  }

  public interface ValidationOrder2 {
  }

  public interface ValidationOrder3 {
  }

  public interface ValidationOrder4 {
  }

  public interface ValidationOrder5 {
  }
}
