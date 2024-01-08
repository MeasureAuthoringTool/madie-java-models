package gov.cms.madie.models.measure;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasureDefinition {
	@Id
  private String id;
	private String term;
	private String definition;
}
