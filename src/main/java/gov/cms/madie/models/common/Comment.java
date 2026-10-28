package gov.cms.madie.models.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  private String id;

  private String mainSection;
  private String subSection;
  private String harpId;
  private String authorName;
  private Instant createdAt;
  private boolean edited;
  private List<String> readByUsers;

  private String content;
  private String link;
  private List<Comment> replies;
}
