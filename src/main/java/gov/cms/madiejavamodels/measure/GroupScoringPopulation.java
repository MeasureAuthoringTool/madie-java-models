package gov.cms.madiejavamodels.measure;

import java.util.Map;

public interface GroupScoringPopulation {
  String getScoring();

  Map<MeasurePopulation, ?> getPopulation();
}
