package gov.cms.madie.models.measure;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class TestCaseConfiguration {

    @Id
    private String id;
    private boolean sdeIncluded;
}
