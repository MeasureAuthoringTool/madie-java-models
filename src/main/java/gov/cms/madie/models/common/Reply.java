package gov.cms.madie.models.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * A reply to a review {@link Comment}. Threads are one level deep: a reply carries no section tags
 * of its own and cannot be replied to.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
  private String id;

  private String authorId;
  private String authorName;
  private Instant createdAt;
  private boolean edited;

  private String content;
}
