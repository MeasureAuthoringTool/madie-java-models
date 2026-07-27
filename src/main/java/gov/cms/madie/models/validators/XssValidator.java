package gov.cms.madie.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class XssValidator implements ConstraintValidator<XssFilter, String> {

  private static final Pattern EXPRESSION_PATTERN = Pattern.compile(".*[\\{\\}\\$\\#\\[\\]\\*].*");

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

      return Jsoup.isValid(decoded, Safelist.none());
    }
    return true;
  }
}
