package gov.cms.madie.models.measure;

import jakarta.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReportingTiming implements Serializable {
  @Valid private Repeat repeat;

  @Data
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Repeat implements Serializable {
    private Integer frequency;
    private BigDecimal period;
    private String periodUnit;
    private List<String> timeOfDay;
  }
}
