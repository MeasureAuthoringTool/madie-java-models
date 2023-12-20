package gov.cms.madie.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslatedLibrary {
  private String name;
  private String version;
  private String cql;
  private String elmXml;
  private String elmJson;
}