package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.time.Instant;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {
  private String id;
  private String name;

  @Length(
    max = 250,
    groups = {ValidationOrder1.class},
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
  @JsonIgnore
  private String resourceUri;
  @JsonIgnore
  private boolean isValidResource;
  private String json;

  @Transient
  private HapiOperationOutcome hapiOperationOutcome;

  @Valid
  private List<TestCaseGroupPopulation> groupPopulations;

  @GroupSequence({ValidationOrder1.class, Default.class})
  public interface ValidationSequence {
  }

  public interface ValidationOrder1 {
  }
}
