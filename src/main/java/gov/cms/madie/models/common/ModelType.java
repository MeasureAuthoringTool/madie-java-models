package gov.cms.madie.models.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public enum ModelType {
  QI_CORE("QI-Core v4.1.1", "qicore"),
  QI_CORE_6_0_0("QI-Core v6.0.0", "qicore6"),
  QDM_5_6("QDM v5.6", "qdm");

  private String value;
  private String shortValue;
  private static final Map<String, ModelType> MODEL_TYPE_BY_VALUE = new HashMap<>();
  private static final Pattern VERSION_PATTERN = Pattern.compile("v(\\d+(\\.\\d+)*)");

  static {
    for (ModelType mt : values()) {
      MODEL_TYPE_BY_VALUE.put(mt.getValue(), mt);
    }
  }

  /**
   * Value is the string that appears in the UI to identify the model being used ShortValue is the
   * string that will be appended to classes to dynamically reference classes
   *
   * @param value
   * @param shortValue
   */
  ModelType(String value, String shortValue) {
    this.value = value;
    this.shortValue = shortValue;
  }

  public String getVersionNumber() {
    Matcher matcher = VERSION_PATTERN.matcher(this.value);
    return matcher.find() ? matcher.group(1) : null;
  }

  @Override
  public String toString() {
    return this.getValue();
  }

  public static ModelType valueOfName(final String name) {
    return MODEL_TYPE_BY_VALUE.get(name);
  }
}
