package gov.cms.madiejavamodels.measure;

import gov.cms.madiejavamodels.validators.EnumValidator;
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

  @EnumValidator(
      enumClass = MeasureScoring.class,
      message = "Scoring must be a valid MADiE scoring type")
  private String scoring;

  private List<TestCasePopulationValue> populationValues;
}