package gov.cms.madie.models.measure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TestCaseTest {

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
