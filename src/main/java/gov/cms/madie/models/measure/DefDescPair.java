package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DefDescPair {

  DefDescPair(String definition, String description) {
    this.definition = definition;
    this.description = description;
  }

  private String definition;
  private String description;
  private List<MeasureReportType> includeInReportType;
}
