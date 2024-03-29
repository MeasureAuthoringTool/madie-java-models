package gov.cms.madie.models.cql.terminology;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CqlCode {
  String codeId;
  CqlCodeSystem codeSystem;
  long hits;
  String text;
  String name;
  LineInfo start;
  LineInfo stop;
  boolean isValid;
  String errorMessage;

  @Data
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CqlCodeSystem {
    String oid;
    long hits;
    String version;
    String text;
    String name;
    LineInfo start;
    LineInfo stop;
    boolean isValid;
    String errorMessage;
  }

  @Data
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class LineInfo {
    int line;
    int position;
  }
}
