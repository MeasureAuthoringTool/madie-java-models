package gov.cms.madie.models.measure;

import gov.cms.madie.models.common.Organization;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasureMetaData implements Serializable {
  @Valid private Organization steward;
  @Valid private List<Organization> developers;
  private String description;
  private String copyright;
  private String disclaimer;
  private String rationale;
  private String guidance;
  private String clinicalRecommendation;

  private boolean draft;
  private Instant versionDate;
  private List<Reference> references;
  @Valid private List<Endorsement> endorsements;
  private String definition;
  private Boolean experimental;
  private String transmissionFormat;
  private String measureSetTitle;
  private CqlMetaData cqlMetaData;
  private CodeConcept intendedVenue;
  private Boolean telehealthEligible;
  private String purpose;
  private boolean composite;

  @Valid private List<MeasureDefinition> measureDefinitions;
}
