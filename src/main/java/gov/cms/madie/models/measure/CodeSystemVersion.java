package gov.cms.madie.models.measure;


import lombok.Data;

@Data
public class CodeSystemVersion {
	private String name;
	private String display;
	private String version;
	private String codeSystem;
	private String codeSystemOid;
	private String status;
}
