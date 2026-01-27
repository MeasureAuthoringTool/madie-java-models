package gov.cms.madie.models.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Action {
  private ActionType actionType;
  private Instant performedAt;
  private String performedBy;
  private String additionalActionMessage;
}
