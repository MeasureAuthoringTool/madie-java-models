package gov.cms.madie.models.library;

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
@Document(collection = "cqlLibraryReviews")
public class CqlLibraryReview implements Serializable {
  @Id private String id;

  private String libraryId;
  private String librarySetId;

  private ReviewStatus status;
  private String comment;
}
