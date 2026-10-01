package gov.cms.madie.models.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id private String id;

  private String mainSection;
  private String subSection;
  private Instant createdAt;
  private String authorName;
  private boolean edited;
  private List<String> readByUsers;

  private String content;
  private String link;
  private List<Comment> replies;
}
