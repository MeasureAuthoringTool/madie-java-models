package gov.cms.madie.models.measure;

import gov.cms.madie.models.validators.RequiredOnSelect;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@RequiredOnSelect(selectedField = "endorser", requiredField = "endorsementId", message = "EndorsementId is required when Endorser is not null,otherwise if Endorser is null, EndorsementId has to be null")
public class Endorsement {

    private String endorser;
    private String endorserSystemId;
    @Pattern(
        regexp = "^[A-Za-z0-9]*$", message = "Endorsement Id is invalid")
    private String endorsementId;
}
