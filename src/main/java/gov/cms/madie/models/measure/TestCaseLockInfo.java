package gov.cms.madie.models.measure;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO containing test case lock information. This is a transient object populated when retrieving
 * test cases, not persisted to the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseLockInfo implements Serializable {
  private String measureId;
  private String testCaseId;
  private String lockedBy;
  private Instant lockedAt;
  private Instant expiresAt;
}
