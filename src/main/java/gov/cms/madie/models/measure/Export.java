package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// TODO: look into GridFS API as our exports may grow larger than the MongoDB document max of 16MB
public class Export {
  @Id private String id;
  private String measureId;
  private String measureBundleJson;
  private String measureBundleGridFsId;
  private String measureBundleJsonWithoutWarnings;
  private String measureBundleWithoutWarningsGridFsId;
  private String humanReadable;
  private List<ComponentHumanReadable> componentHumanReadables;
  private byte[] packageData;
  private byte[] publishablePackageData; // Elm does not include Warnings

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ComponentHumanReadable {
    private String componentId;
    private String fileName;
    private String humanReadable;
  }
}
