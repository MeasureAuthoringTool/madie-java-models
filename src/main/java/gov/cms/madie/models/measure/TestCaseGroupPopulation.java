package gov.cms.madie.models.measure;

import gov.cms.madie.models.validators.EnumValidator;
import gov.cms.madie.models.validators.ValidTestCasePopulationValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
// @ValidScoringPopulation
@ValidTestCasePopulationValue
public class TestCaseGroupPopulation {
  private String groupId;

  @EnumValidator(
      enumClass = MeasureScoring.class,
      message = "Scoring must be a valid MADiE scoring type")
  private String scoring;

  private String populationBasis;

  private List<TestCasePopulationValue> populationValues;

  private List<TestCaseStratificationValue> stratificationValues;
}
