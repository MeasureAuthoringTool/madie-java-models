package gov.cms.madie.models.measure;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QICoreStratification extends Stratification{
//    private String id;
//
//    private String description;
//
//    private String cqlDefinition;

    @NotNull
    private PopulationType association;



}
