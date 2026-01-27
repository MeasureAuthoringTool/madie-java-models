package gov.cms.madie.models.measure;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class CqlMetaData implements Serializable {
  private Map<String, CodeSystem> codeSystemMap;
}
