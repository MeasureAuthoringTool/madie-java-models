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
public class VirusScanResponseDto {
  private int filesScanned;
  private int infectedFileCount;
  private int cleanFileCount;
  private List<VirusScanResultDto> scanResults;
}
