package gov.cms.madiejavamodels.measure;

import gov.cms.madiejavamodels.validators.ValidScoringPopulation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ValidScoringPopulation
public class TestCaseGroupPopulation {
  private String groupId;

  private String scoring;

  private List<TestCasePopulationValue> populationValues;
}
