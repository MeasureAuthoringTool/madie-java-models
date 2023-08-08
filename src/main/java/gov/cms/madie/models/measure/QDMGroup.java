package gov.cms.madie.models.measure;

import gov.cms.madie.models.validators.EnumValidator;
import gov.cms.madie.models.validators.ValidGroupScoringPopulation;
import gov.cms.madie.models.validators.ValidMeasureObservation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
//@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ValidGroupScoringPopulation
@ValidMeasureObservation
public class QDMGroup extends Group {

  private List<Stratification> stratifications;

}
