package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// TODO: look into GridFS API as our exports may grow larger than the MongoDB document max of 16MB
public class Export {
  @Id private String id;
  private String measureId;
  private String measureBundleJson;
  private byte[] packageData;
}
