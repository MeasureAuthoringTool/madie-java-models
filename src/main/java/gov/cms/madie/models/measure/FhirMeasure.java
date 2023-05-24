package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonTypeName;

import gov.cms.madie.models.validators.ValidFhirGroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeName("QI-Core v4.1.1")
@ToString(callSuper=true)
@ValidFhirGroup
public class FhirMeasure extends Measure {

  private List<TestCase> testCases;
}
