package gov.cms.madie.models.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "librarySetActionLog")
public class LibrarySetActionLog {
  @Id private String id;

  private String targetId;
  private List<AccessControlAction> actions;
}
