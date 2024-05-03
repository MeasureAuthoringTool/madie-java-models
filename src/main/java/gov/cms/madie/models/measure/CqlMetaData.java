package gov.cms.madie.models.measure;


import java.util.Map;

import lombok.Data;

@Data
public class CqlMetaData {
	private Map<String, CodeSystem> codeSystemMap;
}
