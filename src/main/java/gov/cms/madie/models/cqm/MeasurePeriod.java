package gov.cms.madie.models.cqm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurePeriod {
  private PeriodPoint low;
  private PeriodPoint high;
}
