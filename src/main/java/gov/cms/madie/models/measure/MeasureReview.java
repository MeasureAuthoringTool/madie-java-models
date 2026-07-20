package gov.cms.madie.models.measure;

import gov.cms.madie.models.common.ReviewStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "measureReviews")
public class MeasureReview implements Serializable {
  @Id private String id;

  private String measureId;
  private String measureSetId;

  private ReviewStatus status;
  private String comment;
}
