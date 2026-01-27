package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.Instant;

/**
 * Represents lock information for a measure. This is used to indicate when a measure is being
 * edited by another user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasureLock implements Serializable {
  private String id;
  private String measureId;
  private String lockedBy;
  private Instant lockedAt;
  private Instant expiresAt;
}
