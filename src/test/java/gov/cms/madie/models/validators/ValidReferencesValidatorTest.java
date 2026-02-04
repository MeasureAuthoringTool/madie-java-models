package gov.cms.madie.models.validators;

import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.MeasureMetaData;
import gov.cms.madie.models.measure.Reference;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ValidReferencesValidatorTest {

  private Validator validator;
  private Measure measure;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    measure =
        Measure.builder()
            .model("QI-Core")
            .measureSetId("testMeasureSetId")
            .cqlLibraryName("TestCqlLibraryName")
            .ecqmTitle("TestECQM")
            .measureName("TestMeasureName")
            .versionId("1.0.0")
            .measureMetaData(new MeasureMetaData())
            .build();
  }

  @Test
  public void testNullMeasureOrMetaData() {
    assertTrue(new ValidReferencesValidator().isValid(null, null));

    measure.setMeasureMetaData(null);
    assertTrue(new ValidReferencesValidator().isValid(measure, null));
  }

  @Test
  public void testNullOrEmptyReferences() {
    measure.setMeasureMetaData(new MeasureMetaData());
    assertTrue(validator.validate(measure).isEmpty());

    measure.setMeasureMetaData(MeasureMetaData.builder().references(List.of()).build());
    assertTrue(validator.validate(measure).isEmpty());
  }

  @Test
  public void testValidReference() {
    Reference ref =
        Reference.builder().referenceText("Some text").referenceType("Citation").build();
    measure.setMeasureMetaData(MeasureMetaData.builder().references(List.of(ref)).build());

    Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void testInvalidReferenceText() {
    Reference ref = Reference.builder().referenceText("   ").referenceType("Citation").build();
    measure.setMeasureMetaData(MeasureMetaData.builder().references(List.of(ref)).build());

    Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
    assertFalse(violations.isEmpty(), "Expected at least one violation");

    ConstraintViolation<Measure> violation =
        violations.stream()
            .findFirst()
            .orElseThrow(() -> new AssertionError("Expected a violation, but none found"));
    assertEquals("Reference text cannot be null or empty", violation.getMessage());
    assertTrue(
        violation
            .getPropertyPath()
            .toString()
            .contains("measureMetaData.references[0].referenceText"));
  }

  @Test
  public void testInvalidReferenceType() {
    Reference ref = Reference.builder().referenceText("Some text").referenceType("BadType").build();
    measure.setMeasureMetaData(MeasureMetaData.builder().references(List.of(ref)).build());

    Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
    assertFalse(violations.isEmpty(), "Expected at least one violation");

    ConstraintViolation<Measure> violation =
        violations.stream()
            .findFirst()
            .orElseThrow(() -> new AssertionError("Expected a violation, but none found"));
    assertTrue(violation.getMessage().contains("Reference type must be one of"));
    assertTrue(
        violation
            .getPropertyPath()
            .toString()
            .contains("measureMetaData.references[0].referenceType"));
  }

  @Test
  public void testQdm56AllowsUnknownType() {
    Reference ref = Reference.builder().referenceText("Some text").referenceType("Unknown").build();
    measure.setModel("QDM v5.6");
    measure.setMeasureMetaData(MeasureMetaData.builder().references(List.of(ref)).build());

    Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void testMultipleInvalidReferences() {
    Reference ref1 = Reference.builder().referenceText("").referenceType("Citation").build();
    Reference ref2 = Reference.builder().referenceText("Text").referenceType("BadType").build();
    measure.setMeasureMetaData(MeasureMetaData.builder().references(List.of(ref1, ref2)).build());

    Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
    assertEquals(2, violations.size());

    Set<String> messages =
        violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    Set<String> paths =
        violations.stream().map(v -> v.getPropertyPath().toString()).collect(Collectors.toSet());

    assertTrue(messages.contains("Reference text cannot be null or empty"));
    assertTrue(messages.stream().anyMatch(m -> m.contains("Reference type must be one of")));

    assertTrue(paths.stream().anyMatch(p -> p.contains("references[0].referenceText")));
    assertTrue(paths.stream().anyMatch(p -> p.contains("references[1].referenceType")));
  }
}
