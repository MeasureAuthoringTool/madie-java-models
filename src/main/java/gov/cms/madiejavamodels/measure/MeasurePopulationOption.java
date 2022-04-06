package gov.cms.madiejavamodels.measure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurePopulationOption {
  MeasurePopulation measurePopulation;
  boolean required;
}
