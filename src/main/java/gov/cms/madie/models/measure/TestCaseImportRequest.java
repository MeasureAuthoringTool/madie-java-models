package gov.cms.madie.models.measure;

import java.util.List;

import gov.cms.madie.models.dto.TestCaseExportMetaData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseImportRequest {
  private String familyName;
  private List<String> givenNames;
  private UUID patientId;
  private String json;
  private TestCaseExportMetaData testCaseMetaData;
}
