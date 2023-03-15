package gov.cms.madie.models.measure;

import lombok.Data;

@Data
public class RiskAdjustment extends SupplementalData {

    public RiskAdjustment() {
        super();
    }

    public RiskAdjustment(String definition, String description) {
        super(definition, description);
    }
}
