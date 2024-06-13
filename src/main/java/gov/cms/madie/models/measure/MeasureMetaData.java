package gov.cms.madie.models.measure;

import gov.cms.madie.models.common.Organization;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasureMetaData {
  private Organization steward;
  private List<Organization> developers;
  private String description;
  private String copyright;
  private String disclaimer;
  private String rationale;
  private String guidance;
  private String clinicalRecommendation;

  private boolean draft;
  @Valid private List<Reference> references;
  @Valid private List<Endorsement> endorsements;
  private String riskAdjustment;
  private String definition;
  private boolean experimental;
  private String transmissionFormat;
  private String supplementalDataElements;
  private String measureSetTitle;
  private CqlMetaData cqlMetaData;

  private String translatorVersion;
}
