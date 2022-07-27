package gov.cms.madie.models.measure;

import lombok.Data;

@Data
public class Stratification {
    private String id;

    private String description;

    private String cqlDefinition;

    private String association;

}