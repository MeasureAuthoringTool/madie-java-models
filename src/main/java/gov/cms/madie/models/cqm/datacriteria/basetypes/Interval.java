package gov.cms.madie.models.cqm.datacriteria.basetypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interval {
  private LocalDateTime low;
  private LocalDateTime high;
  private boolean lowClosed;
  private boolean highClosed;
}
