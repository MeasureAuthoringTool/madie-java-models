package gov.cms.madie.models.dto;

import gov.cms.madie.models.library.LibrarySet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CqlLibraryDto {
  private String id;
  private String cqlLibraryName;
  private String model;
  private String version;
  private String cql;
  private String elmJson;
  private String elmXml;
  private String publisher;
  private String description;
  private boolean experimental;
  private boolean draft;
  private String ownerDisplayName;
  private String librarySetId;
  private LibrarySet librarySet;
  private String namespacePrefix;
  private boolean external;
  private String fhirResource;
}
