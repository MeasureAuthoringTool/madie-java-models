package gov.cms.madie.models.scanner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ScanValidationDto {
  private String fileName;
  private boolean valid;
  private ObjectError error;
}
