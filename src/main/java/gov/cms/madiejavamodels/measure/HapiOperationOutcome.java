package gov.cms.madiejavamodels.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HapiOperationOutcome {
  private int code;
  private String message;
  // Plain object as we don't know or care about the structure of the HAPI FHIR response
  private Object outcomeResponse;
}
