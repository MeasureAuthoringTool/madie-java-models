package gov.cms.madie.models.measure;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@Data
public class MeasureMetaData {
  private String steward;
  private List<String> developers;
  private String description;
  private String copyright;
  private String disclaimer;
  private String rationale;
  private String guidance;
  private String clinicalRecommendation;
  
  private boolean draft;
  private List<Reference> references;
  private List<Endorsement> endorsements;
  private String riskAdjustment;
  private String definition;
  private boolean experimental;
  private String transmissionFormat;
  private String supplementalDataElements;
  
}
