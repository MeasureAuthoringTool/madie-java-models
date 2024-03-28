package gov.cms.madie.models.measure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Reporting {
  private String rateAggregation;
  private String improvementNotation;
  private String improvementNotationDescription;
}
