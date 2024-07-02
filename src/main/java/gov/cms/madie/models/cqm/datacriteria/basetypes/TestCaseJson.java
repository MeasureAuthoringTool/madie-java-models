package gov.cms.madie.models.cqm.datacriteria.basetypes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestCaseJson {
  private String _id;
  private String qdmVersion = "5.6";
  private List<DataElement> dataElements;
}
