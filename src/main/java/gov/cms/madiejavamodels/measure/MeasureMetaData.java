package gov.cms.madiejavamodels.measure;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MeasureMetaData {
  private String steward;
  private String description;
  private String copyright;
  private String disclaimer;
  private String rationale;
}
