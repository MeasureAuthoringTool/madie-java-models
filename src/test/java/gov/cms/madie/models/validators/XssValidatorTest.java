package gov.cms.madie.models.validators;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class XssValidatorTest {

  private XssValidator xssValidator;

  @Mock private ConstraintValidatorContext context;

  @BeforeEach
  void setUp() {
    xssValidator = new XssValidator();
  }

  @Test
  void testIsValidReturnsTrueForNullInput() {
    assertTrue(xssValidator.isValid(null, context));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "",
        "   ",
        "Just a regular string with no tags",
        "Medicare Advantage Plan 2026",
        "This is safe alphanumeric input 123"
      })
  void testIsValidReturnsTrueForSafeStrings(String safeInput) {
    assertTrue(xssValidator.isValid(safeInput, context));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "{base}*1 ",
        "${spring.expression}",
        "#{systemProperties}",
        "some[array]element",
        "multiplication*sign"
      })
  void testIsValidReturnsFalseForBackendExpressions(String expressionInput) {
    assertFalse(xssValidator.isValid(expressionInput, context));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "<script>alert(1)</script>",
        "<html><body>malicious</body></html>",
        "<div>plain div tag</div>",
        "<b>bold text</b>",
        "<img src=x onerror=alert(1)>"
      })
  void testIsValidReturnsFalseForFrontendXssAndHtmlTags(String htmlInput) {
    assertFalse(xssValidator.isValid(htmlInput, context));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "%3cbody%20onresize%3d%22print()%22%3e", // URL encoded <body onresize="print()">
        "%3cscript%3ealert(1)%3c/script%3e", // URL encoded <script>alert(1)</script>
        "hello%20world%2a" // URL encoded '*' character at the end
      })
  void testIsValidReturnsFalseForUrlEncodedPayloads(String encodedInput) {
    assertFalse(xssValidator.isValid(encodedInput, context));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hello%20world", "a+b", "simple%2Ddash", "%", "%ZZ", "%3", "9%"})
  void isValidReturnsTrueForWellFormedUrlEncodedStrings(String wellFormedEncoded) {
    assertTrue(xssValidator.isValid(wellFormedEncoded, context));
  }
}
