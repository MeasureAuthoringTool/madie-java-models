package gov.cms.madie.models.measure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import jakarta.validation.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TestCaseTest {

  @ParameterizedTest
  @ValueSource(ints = {0, 250, 251, 3999, 4000})
  void testDescriptionWithinLimit(int length) {
    try (var factory = Validation.buildDefaultValidatorFactory()) {
      TestCase testCase =
          TestCase.builder().title("Valid Title").description("a".repeat(length)).build();
      assertTrue(
          factory.getValidator().validate(testCase, TestCase.ValidationSequence.class).isEmpty());
    }
  }

  @Test
  void testDescriptionExceedingLimit() {
    try (var factory = Validation.buildDefaultValidatorFactory()) {
      TestCase testCase =
          TestCase.builder().title("Valid Title").description("a".repeat(4001)).build();
      var violations = factory.getValidator().validate(testCase, TestCase.ValidationSequence.class);
      assertEquals(1, violations.size());
      var violation = violations.iterator().next();
      assertEquals("description", violation.getPropertyPath().toString());
      assertEquals(
          "Test Case Description cannot be more than 4000 characters.", violation.getMessage());
    }
  }

  @Test
  void testEmptyDeepCopy() {
    TestCase tc = new TestCase();
    TestCase copy = tc.deepCopy();
    assertEquals(tc, copy);

    copy.setTitle("title");
    assertNotEquals(copy.getTitle(), tc.getTitle());
  }

  @Test
  void testPartialDeepCopy() {
    TestCase testCase = new TestCase();
    testCase.setId("TESTID");
    testCase.setTitle("IPPPass");
    testCase.setSeries("BloodPressure bigger than 124");
    testCase.setCreatedBy("TestUser");
    testCase.setLastModifiedBy("TestUser2");
    testCase.setJson("{\n  \"resourceType\" : \"Patient\"\n}");
    testCase.setPatientId(UUID.randomUUID());

    TestCase copy = testCase.deepCopy();
    assertEquals(testCase, copy);
  }

  @Test
  void testDeepCopyCollections() {
    TestCase testCase = new TestCase();
    testCase.setId("TESTID");
    testCase.setTitle("IPPPass");
    //    testCase.setSeries("BloodPressure>124");
    testCase.setSeries("BloodPressure bigger than 124");
    testCase.setCreatedBy("TestUser");
    testCase.setLastModifiedBy("TestUser2");
    testCase.setJson("{\n  \"resourceType\" : \"Patient\"\n}");
    testCase.setPatientId(UUID.randomUUID());

    testCase.toBuilder()
        .groupPopulations(
            List.of(
                TestCaseGroupPopulation.builder()
                    .scoring(MeasureScoring.PROPORTION.toString())
                    .populationBasis("boolean")
                    .populationValues(
                        List.of(
                            TestCasePopulationValue.builder()
                                .name(PopulationType.INITIAL_POPULATION)
                                .expected(true)
                                .build(),
                            TestCasePopulationValue.builder()
                                .name(PopulationType.DENOMINATOR)
                                .expected(true)
                                .build(),
                            TestCasePopulationValue.builder()
                                .name(PopulationType.NUMERATOR)
                                .expected(true)
                                .build()))
                    .build()))
        .build();

    TestCase copy = testCase.deepCopy();
    assertEquals(testCase, copy);

    copy.setGroupPopulations(new ArrayList<>());
    assertNotEquals(copy.getGroupPopulations(), testCase.getGroupPopulations());
  }
}
