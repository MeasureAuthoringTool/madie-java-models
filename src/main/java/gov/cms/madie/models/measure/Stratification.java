package gov.cms.madie.models.measure;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Stratification {
    private String id;

    private String description;

    private String cqlDefinition;

    @NotNull
    private PopulationType association;

}
