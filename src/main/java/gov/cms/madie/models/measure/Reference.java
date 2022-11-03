package gov.cms.madie.models.measure;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Reference {
	@Id private String id;
	private String referenceText;
	private String referenceType;
}
