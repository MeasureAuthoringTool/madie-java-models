package gov.cms.madie.models.cqm;

import gov.cms.madie.models.cqm.datacriteria.basetypes.DataElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CqmMeasure {
  private String id;
  private String hqmf_id;
  private String hqmf_set_id;
  private String hqmf_version_number;
  private String cms_id;

  private String title = "";
  private String description = "";
  private boolean composite = false;
  private boolean component = false;
  private List<String> component_hqmf_set_ids = Collections.emptyList();
  private String composite_hqmf_set_id;
  private String measure_scoring = "PROPORTION";
  private String calculation_method = "PATIENT";
  private boolean calculate_sdes;

  private List<CQLLibrary> cql_libraries;
  private String main_cql_library;
  private MeasurePeriod measure_period;
  private List<DataElement> source_data_criteria;
  private List<PopulationSet> population_sets;
}
