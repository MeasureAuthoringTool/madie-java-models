package gov.cms.madie.models.measure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MeasureTest {
  @Test
  void testEmptyDeepCopy() {
    Measure measure = new Measure();
    Measure copy = measure.deepCopy();
    assertEquals(measure, copy);

    copy.setMeasureName("name");
    assertNotEquals(copy.getMeasureName(), measure.getMeasureName());
  }

  @Test
  void testDeepCopyCollections() {
    Measure measure =
      new Measure()
        .toBuilder()
        .id("id")
        .active(true)
        .groups(
          List.of(
            Group.builder()
              .groupDescription("group 1 Description")
              .rateAggregation("rate agg")
              .improvementNotationDescription("line go up")
              .populations(
                List.of(
                  Population.builder()
                    .definition("definition1")
                    .build()))
              .stratifications(
                List.of(
                  Stratification.builder()
                    .cqlDefinition("cql 1")
                    .build()))
              .build()))
        .build();

    Measure copy = measure.deepCopy();
    assertThat(copy.getGroups()).isEqualTo(measure.getGroups());

    copy.setGroups(new ArrayList<>());
    assertThat(copy.getGroups()).isNotEqualTo(measure.getGroups());
    assertThat(copy.getGroups()).isEmpty();
    assertThat(measure.getGroups()).isNotEmpty();
  }

  @Test
  void testDeepCopyNestedObjects() {
    Measure measure =
      new Measure()
        .toBuilder()
        .id("id")
        .active(true)
        .measureMetaData(
          MeasureMetaData.builder()
            .draft(true)
            .description("measureDesc")
            .rationale("rationale")
            .purpose("purpose")
            .guidance("guidance")
            .clinicalRecommendation("clinicalRecommendation")
            .references(
              List.of(
                Reference.builder()
                  .referenceText("reference1")
                  .referenceType("CITATION")
                  .build()))
            .measureDefinitions(
              List.of(
                MeasureDefinition.builder()
                  .definition("definition1")
                  .term("term")
                  .build()))
            .copyright("measure Copyright")
            .disclaimer("disclaimer")
            .build())
        .build();

    Measure copy = measure.deepCopy();
    assertThat(copy.getMeasureMetaData()).isEqualTo(measure.getMeasureMetaData());
    copy.getMeasureMetaData().setDescription("another description");
    copy.getMeasureMetaData().setReferences(List.of());

    assertThat(copy.getMeasureMetaData().getDescription()).isEqualTo("another description");
    assertThat(measure.getMeasureMetaData().getDescription()).isEqualTo("measureDesc");

    assertThat(copy.getMeasureMetaData().getReferences()).isEmpty();
    assertThat(measure.getMeasureMetaData().getReferences()).hasSize(1);
    assertThat(measure.getMeasureMetaData().getReferences().get(0).getReferenceText()).isEqualTo("reference1");
  }
}
