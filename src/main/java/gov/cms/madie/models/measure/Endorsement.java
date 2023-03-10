package gov.cms.madie.models.measure;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Endorsement {
	private String endorser;
	private String endorserSystemId;
	private String endorsementId;
}
