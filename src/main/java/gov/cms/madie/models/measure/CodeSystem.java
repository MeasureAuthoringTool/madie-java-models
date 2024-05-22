package gov.cms.madie.models.measure;

import lombok.Data;

@Data
public class CodeSystem {
  private String name;
  private String display;
  private String fhirVersion;
  private String svsVersion;
  private String codeSystem;
  private String codeSystemOid;
  private String status;
}
