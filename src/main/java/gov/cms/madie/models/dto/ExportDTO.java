package gov.cms.madie.models.dto;

import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.common.BundleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExportDTO {
    private Measure measure;
    private List<String> testCaseIds;
    private BundleType bundleType ; 
}
	