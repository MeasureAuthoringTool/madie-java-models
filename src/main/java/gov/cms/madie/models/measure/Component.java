package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Component {
  private String measureId;
  private String groupId;
  private Double weight;
  @Transient private String groupDisplayId;
  @Transient private String measureLibraryName;
  @Transient private String measureVersion;
  @Transient private boolean draft;
}
