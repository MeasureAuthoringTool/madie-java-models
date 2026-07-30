package gov.cms.madie.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class XssValidator implements ConstraintValidator<XssFilter, String> {

  private static final Pattern EXPRESSION_PATTERN = Pattern.compile(".*[{}$\\[\\]*].*");
  private static final Pattern HTML_TAG_PATTERN =
      Pattern.compile("(?i)<\\s*/?\\s*[a-z][a-z0-9]*\\b[^>]*>");

  @Override
  public boolean isValid(String field, ConstraintValidatorContext context) {
    if (field != null) {
      String decoded;
      try {
        decoded = URLDecoder.decode(field, StandardCharsets.UTF_8);
      } catch (IllegalArgumentException e) {
        // Treat malformed encoding strings as plain text and continue validation.
        decoded = field;
      }

      if (EXPRESSION_PATTERN.matcher(decoded).matches()) {
        return false;
      }

      return !containsHtmlElements(decoded);
    }
    return true;
  }

  private boolean containsHtmlElements(String input) {
    // Detect explicit tag-like markup but allow comparator text such as "< 3".
    if (HTML_TAG_PATTERN.matcher(input).find()) {
      return true;
    }

    // Defensive fallback for unusual parser-normalized content.
    return !Jsoup.parse(input).body().children().isEmpty();
  }
}
