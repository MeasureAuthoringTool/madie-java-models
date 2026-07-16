package gov.cms.madie.models.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable {
  private ReviewStatus status;
  private String comment;
}
