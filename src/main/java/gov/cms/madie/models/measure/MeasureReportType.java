package gov.cms.madie.models.measure;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MeasureReportType {
  @JsonProperty("Individual")
  INDIVIDUAL("individual", "Individual"),
  @JsonProperty("Subject List")
  SUBJECT_LIST("subject-list", "Subject List"),
  @JsonProperty("Summary")
  SUMMARY("summary", "Summary"),
  @JsonProperty("Data Collection")
  DATA_COLLECTION("data-collection", "Data Collection");
  private final String code;
  private final String display;

  MeasureReportType(String code, String display) {
    this.code = code;
    this.display = display;
  }

  public String toCode() {
    return code;
  }

  public String getDisplay() {
    return display;
  }
}
