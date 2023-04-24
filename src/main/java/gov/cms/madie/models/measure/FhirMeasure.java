package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeName("QI-Core v4.1.1")
@ToString(callSuper=true)
public class FhirMeasure extends Measure {
	
}
