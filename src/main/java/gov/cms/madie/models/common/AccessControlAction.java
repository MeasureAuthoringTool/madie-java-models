package gov.cms.madie.models.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccessControlAction extends Action {
  private String sharedWith;
}
