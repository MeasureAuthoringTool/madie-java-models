package gov.cms.madie.models.measure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gov.cms.madie.models.validators.ValidMeasureScoring;
import gov.cms.madie.models.validators.ValidQDMGroupScoring;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeName("QDM v5.6")
@ToString(callSuper = true)
@ValidMeasureScoring
@ValidQDMGroupScoring
public class QdmMeasure extends Measure {

  private String scoring;
  private List<BaseConfigurationTypes> baseConfigurationTypes;
  @Builder.Default private boolean patientBasis = true;
  private String rateAggregation;
  private String improvementNotation;
  private String improvementNotationDescription;
}
