package gov.cms.madie.models.library;

import gov.cms.madie.models.common.ReviewStatus;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CqlLibraryReview implements Serializable {
  @Id private String id;

  private String libraryId;
  private String librarySetId;

  private ReviewStatus status;
  private String comment;
  private List<String> reviewers;
}
