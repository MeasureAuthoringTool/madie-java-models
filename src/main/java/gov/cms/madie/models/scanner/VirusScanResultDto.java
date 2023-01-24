package gov.cms.madie.models.scanner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VirusScanResultDto {
  private String fileName;
  private boolean infected;
  private List<String> viruses;
}
