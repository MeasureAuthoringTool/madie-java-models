package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCasePopulationValue {
  private String id;
  private String criteriaReference;
  private PopulationType name;
  private Object expected;
  private Object actual;
}
