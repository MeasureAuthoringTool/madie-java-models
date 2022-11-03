package gov.cms.madie.models.measure;

import java.util.List;

public interface GroupScoringPopulation {
  String getScoring();

  List<Population> getPopulations();
}
