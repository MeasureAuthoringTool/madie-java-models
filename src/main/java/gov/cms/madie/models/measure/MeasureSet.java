package gov.cms.madie.models.measure;

import gov.cms.madie.models.access.AclSpecification;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MeasureSet {

    @Id
    private String id;

    private int cmsId;

    @NotBlank(
            groups={Measure.ValidationOrder1.class},message="Measure Set Id is required")
    private String measureSetId;

    private String owner;

    private List<AclSpecification> acls;
}
