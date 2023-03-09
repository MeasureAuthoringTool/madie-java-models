package gov.cms.madie.models.measure;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMetaData {
  private LocalDate approvalDate;
  private LocalDate lastReviewDate;
}
