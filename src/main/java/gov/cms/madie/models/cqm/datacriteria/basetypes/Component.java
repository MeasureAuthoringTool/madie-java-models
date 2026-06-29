package gov.cms.madie.models.cqm.datacriteria.basetypes;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Component {
  private String qdmVersion;

  @JsonProperty("_type")
  private String _type;

  @JsonProperty("_id")
  private String _id;

  private Code code;

  private Object result;
}
