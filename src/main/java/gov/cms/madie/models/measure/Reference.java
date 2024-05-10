package gov.cms.madie.models.measure;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Reference {
	@Id private String id;
	private String referenceText;
	@NotNull(
		message = "Reference type cannot be null.",
		groups = {Measure.ValidationOrder5.class})
	@NotBlank
	@NotEmpty(
		message = "Reference type cannot be null.",
		groups = {Measure.ValidationOrder5.class})
	private String referenceType;
}
