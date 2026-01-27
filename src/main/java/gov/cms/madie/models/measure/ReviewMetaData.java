package gov.cms.madie.models.measure;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMetaData implements Serializable {
  private Instant approvalDate;
  private Instant lastReviewDate;
}
