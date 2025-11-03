package gov.cms.madie.models.library;

import java.io.Serializable;
import java.time.Instant;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CqlLibraryLockInfo implements Serializable {
  private static final long serialVersionUID = -1628663789637560861L;

  private String cqlLibraryId;
  private String lockedBy;
  private Instant lockedAt;
  private Instant expiresAt;
}
