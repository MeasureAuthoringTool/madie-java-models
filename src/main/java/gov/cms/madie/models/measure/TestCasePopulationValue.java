package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCasePopulationValue implements Serializable {
  private String id;
  private String criteriaReference;
  private PopulationType name;
  private Object expected;
  private Object actual;
}
