package gov.cms.madie.models.utils;

public final class CmsIdFormatter {
  public static final int PAD_WIDTH = 4;
  public static final String FHIR_SUFFIX = "FHIR";
  private static final String QI_CORE_PREFIX = "QI-Core";

  private CmsIdFormatter() {}

  public static String pad(Integer cmsId) {
    if (cmsId == null || cmsId <= 0) {
      return "";
    }
    return String.format("%0" + PAD_WIDTH + "d", cmsId);
  }

  public static String format(Integer cmsId, String model) {
    String padded = pad(cmsId);
    if (padded.isEmpty()) {
      return "";
    }
    return model != null && model.startsWith(QI_CORE_PREFIX) ? padded + FHIR_SUFFIX : padded;
  }
}
