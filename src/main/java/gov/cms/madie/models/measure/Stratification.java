package gov.cms.madie.models.measure;

import gov.cms.madie.models.validators.EnumValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Stratification {
    private String id;

    private String description;

    private String cqlDefinition;

    @NotBlank
    @EnumValidator(
        enumClass = PopulationType.class,
        message = "Association must be a valid Population type",
        groups = {Measure.ValidationOrder5.class})
    private String association;

}
