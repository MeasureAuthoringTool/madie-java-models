package gov.cms.madie.models.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseStratificationValue {
    private String id;
    private String name;
    private Object expected;
    private Object actual;
    private TestCasePopulationValue populationValues;
}

