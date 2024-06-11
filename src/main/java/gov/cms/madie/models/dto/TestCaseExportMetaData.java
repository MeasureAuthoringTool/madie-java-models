package gov.cms.madie.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseExportMetaData {
  private String testCaseId;
  private String patientId;
  private String title;
  private String series;
  private String description;
}
