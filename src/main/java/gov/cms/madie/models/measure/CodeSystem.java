package gov.cms.madie.models.measure;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeSystem implements Serializable {
  private String name;
  private String display;
  private String fhirVersion;
  private String svsVersion;
  private String codeSystem;
  private String codeSystemOid;
  private String status;
}
