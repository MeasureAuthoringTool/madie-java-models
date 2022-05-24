package gov.cms.madiejavamodels.mappingData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeSystemEntry {
  private String oid;
  private String url;
  private String name;
  private List<Version> version;

  @Data
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Version {
    String vsac;
    String fhir;
  }
}
