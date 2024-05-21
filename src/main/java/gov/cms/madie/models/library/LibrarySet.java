package gov.cms.madie.models.library;

import gov.cms.madie.models.access.AclSpecification;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class LibrarySet {

  @Id private String id;

  @NotBlank(
      groups = {CqlLibrary.ValidationOrder1.class},
      message = "Library Set Id is required")
  @Indexed(unique = true)
  private String librarySetId;

  private String owner;

  private List<AclSpecification> acls;
}
