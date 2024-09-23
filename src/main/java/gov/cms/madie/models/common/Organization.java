package gov.cms.madie.models.common;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "organization")
public class Organization {
  @Id
  @NotBlank(message = "Id is required.")
  private String id;

  @NotBlank(message = "Name is required.")
  private String name;

  private String oid;
  private String url;
}
