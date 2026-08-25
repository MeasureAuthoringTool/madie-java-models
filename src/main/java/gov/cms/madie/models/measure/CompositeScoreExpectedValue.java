package gov.cms.madie.models.measure;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompositeScoreExpectedValue implements Serializable {

  private String displayId;

  private Score compositeScore;

  private Score denominatorScore;

  private Score numeratorScore;
}
