package gov.cms.madie.models.measure;

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

	private Date approvalDate;
  private Date lastReviewDate;
}
