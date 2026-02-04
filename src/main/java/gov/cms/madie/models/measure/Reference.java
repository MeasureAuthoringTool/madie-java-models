package gov.cms.madie.models.measure;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Reference implements Serializable {
  @Id private String id;
  private String referenceText;
  private String referenceType;
}
