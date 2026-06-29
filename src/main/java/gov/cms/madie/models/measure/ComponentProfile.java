package gov.cms.madie.models.measure;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentProfile implements Serializable {
  private String measureName;
  private String measureVersion;
  private String measureId;
  private String testCaseGroup;
  private String testCaseTitle;
  private String testCaseDescription;
  private String testCaseId;
  private String testCaseSetId;
  private String originalProfileId;
  private String newProfileId;
}
