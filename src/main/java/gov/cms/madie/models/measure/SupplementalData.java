package gov.cms.madie.models.measure;

import lombok.Data;

@Data
public class SupplementalData extends DefDescPair {

  public SupplementalData() {
    super();
  }
  
  public SupplementalData(String definition, String description) {
      super(definition, description);
  }

}
