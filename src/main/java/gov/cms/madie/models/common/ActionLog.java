package gov.cms.madie.models.common;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class ActionLog {
  @Id private String id;
  private String targetId;
  private List<Action> actions;
}
