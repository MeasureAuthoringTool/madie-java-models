package gov.cms.madie.models.measure;

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
  private UUID patientId;
  private String message;
  private boolean successful;
}
