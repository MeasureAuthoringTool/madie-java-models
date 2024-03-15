package gov.cms.madie.models.measure;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseImportOutcome {
  private String familyName;
  private List<String> givenNames;
  private UUID patientId;
  private String message;
  private boolean successful;
}
