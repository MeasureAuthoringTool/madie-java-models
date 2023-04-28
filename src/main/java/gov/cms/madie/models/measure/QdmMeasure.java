package gov.cms.madie.models.measure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import gov.cms.madie.models.validators.ValidMeasureScoring;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeName("QDM v5.6")
@ToString(callSuper=true)
@ValidMeasureScoring
public class QdmMeasure extends Measure {
	
	private String scoring;
	private List<BaseConfigurationTypes> baseConfigurationTypes;
	@Builder.Default
	private boolean patientBasis = true;
	
	@Override
  public void setGroups(List<Group> groups) {
		if(groups==null) {
			return;
		}
    if (groups.stream().map(Group::getScoring).allMatch(scoring -> groups.get(0).getScoring().equalsIgnoreCase(scoring))
    		&& groups.get(0).getScoring().equalsIgnoreCase(this.scoring)) {
      super.setGroups(groups);
    } else throw new RuntimeException("Groups must have same scoring");
  }
}
