package gov.cms.madie.models.measure.qdm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.cms.madie.models.measure.HapiOperationOutcome;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import java.time.Instant;
import java.util.List;

public class QdmTestCase {
  private String id;
  private String name;

  @NotBlank(
      groups = {QdmTestCase.ValidationOrder1.class},
      message = "Test Case Title is required.")
  @Length(
      max = 250,
      groups = {QdmTestCase.ValidationOrder2.class},
      message = "Test Case Title can not be more than 250 characters.")
  private String title;

  @Length(
      max = 250,
      groups = {QdmTestCase.ValidationOrder1.class},
      message = "Test Case Series can not be more than 250 characters.")
  private String series;

  @Length(
      max = 250,
      groups = {QdmTestCase.ValidationOrder1.class},
      message = "Test Case Description can not be more than 250 characters.")
  private String description;

  private Instant createdAt;
  private String createdBy;
  private Instant lastModifiedAt;
  private String lastModifiedBy;
  @JsonIgnore
  private String resourceUri;
  private boolean validResource;
  private String json;

  @Transient
  private HapiOperationOutcome hapiOperationOutcome;

  @Valid
  private List<QdmTestCaseGroupPopulation> groupPopulations;

  @GroupSequence({QdmTestCase.ValidationOrder1.class, QdmTestCase.ValidationOrder2.class, Default.class})
  public interface ValidationSequence {
  }

  public interface ValidationOrder1 {
  }

  public interface ValidationOrder2 {
  }
}
