package gov.cms.madie.models.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.common.Version;
import gov.cms.madie.models.library.CqlLibrary;
import gov.cms.madie.models.library.LibrarySet;
import gov.cms.madie.models.utils.VersionJsonSerializer;
import gov.cms.madie.models.validators.EnumValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class LibraryList {

    private String id;
    private String librarySetId;

    private String cqlLibraryName;

    @NotBlank(message = "Model is required")
    @EnumValidator(
            enumClass = ModelType.class,
            message = "Model must be one of the supported types in MADiE.",
            groups = {CqlLibrary.ValidationOrder4.class})
    private String model;

    @JsonSerialize(using = VersionJsonSerializer.VersionSerializer.class)
    @JsonDeserialize(using = VersionJsonSerializer.VersionDeserializer.class)
    private Version version;

    @DBRef
    private LibrarySet librarySet;

    private boolean draft;
}
