package gov.cms.madiejavamodels.mappingData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeSystemEntry {
  private String oid;
  private String url;
  private String name;
  private List<Version> version;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Version {
    String vsac;
    String fhir;
  }
}
