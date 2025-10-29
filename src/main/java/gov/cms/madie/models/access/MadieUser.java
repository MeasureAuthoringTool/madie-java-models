package gov.cms.madie.models.access;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MadieUser {
  @Id private String id;
  private String harpId;
  private UserStatus status;
  private String firstName;
  private String lastName;
  private String displayName;
  private String email;
  @Singular private List<HarpRole> roles;
  private Instant lastLoginAt;
  private Instant accessStartAt; // most recent MADiE role granted at this datetime
  private Instant createdAt;
  private Instant lastModifiedAt;
}
