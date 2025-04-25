package gov.cms.madie.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OverlappingCodeDto {
  private String code;
  private String codeSystem;
  private String description;
  private String codeSystemName;
  private String codeSystemVersion;
  List<OverlappingValueSetDto> valueSets;
}
