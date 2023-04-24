package gov.cms.madie.models.measure;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeName("QDM v5.6")
@ToString(callSuper=true)
public class QdmMeasure extends Measure {

	@NotBlank(message = "Scoring is required for QDM Measure.")
	private String scoring;
	
}
