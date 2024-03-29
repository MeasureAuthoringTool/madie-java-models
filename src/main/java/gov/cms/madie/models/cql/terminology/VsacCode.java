package gov.cms.madie.models.cql.terminology;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VsacCode {
  private String message;
  private String status;

  private VsacData data;
  private VsacError errors;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class VsacData {
    private int resultCount;
    private List<VsacDataResultSet> resultSet = new ArrayList<>();
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class VsacError {
    private int errorCount;
    private List<VsacErrorResultSet> resultSet = new ArrayList<>();
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class VsacErrorResultSet {
    private String errDesc;
    private String errCode;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class VsacDataResultSet {
    private String csName;
    private String csOID;
    private String csVersion;
    private String code;
    private String contentMode;
    private String codeName;
    private String termType;
    private String active;
    private Long revision;
  }
}
