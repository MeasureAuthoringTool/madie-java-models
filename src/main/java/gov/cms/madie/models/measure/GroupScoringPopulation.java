package gov.cms.madie.models.measure;

import java.util.Map;

public interface GroupScoringPopulation {
  String getScoring();

  Map<MeasurePopulation, ?> getPopulation();
}
