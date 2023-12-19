package gov.cms.madie.models.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslatedLibrary {
  private String name;
  private String version;
  private String cql;
  private String elmXml;
  private String elmJson;
}