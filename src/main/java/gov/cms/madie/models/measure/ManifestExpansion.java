package gov.cms.madie.models.measure;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ManifestExpansion {
    private String fullUrl;
    private String id;
}
