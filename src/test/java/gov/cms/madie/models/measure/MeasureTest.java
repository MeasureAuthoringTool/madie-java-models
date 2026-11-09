package gov.cms.madie.models.measure;

import org.junit.jupiter.api.Test;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MeasureTest {
  @Test
  void reportingTimingSurvivesJsonAndDeepCopy() {
    ReportingTiming timing =
        ReportingTiming.builder()
            .repeat(
                ReportingTiming.Repeat.builder()
                    .frequency(2)
                    .period(new BigDecimal("1.5"))
                    .periodUnit("d")
                    .timeOfDay(List.of("08:30:00"))
                    .build())
            .build();
    Measure measure =
        Measure.builder()
            .measureMetaData(MeasureMetaData.builder().reportingTiming(timing).build())
            .build();
    ObjectMapper mapper = new ObjectMapper();
    MeasureMetaData restored =
        mapper.readValue(
            mapper.writeValueAsString(measure.getMeasureMetaData()), MeasureMetaData.class);

    assertThat(restored.getReportingTiming()).isEqualTo(timing);
    assertThat(measure.deepCopy().getMeasureMetaData().getReportingTiming()).isEqualTo(timing);
    MongoCustomConversions conversions = MongoCustomConversions.create(adapter -> {});
    MongoMappingContext context = new MongoMappingContext();
    context.setSimpleTypeHolder(conversions.getSimpleTypeHolder());
    context.afterPropertiesSet();
    MappingMongoConverter converter =
        new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, context);
    converter.setCustomConversions(conversions);
    converter.afterPropertiesSet();
    Document document = new Document();
    converter.write(measure.getMeasureMetaData(), document);
    assertThat(converter.read(MeasureMetaData.class, document).getReportingTiming())
        .isEqualTo(timing);
  }

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
                                List.of(Population.builder().definition("definition1").build()))
                            .stratifications(
                                List.of(Stratification.builder().cqlDefinition("cql 1").build()))
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
                        .limitations("limitations")
                        .trendingIssues("trendingIssues")
                        .authoritativeSource("https://www.test.org")
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
    assertThat(measure.getMeasureMetaData().getReferences().get(0).getReferenceText())
        .isEqualTo("reference1");

    assertThat(copy.getMeasureMetaData().getLimitations()).isEqualTo("limitations");
    assertThat(copy.getMeasureMetaData().getTrendingIssues()).isEqualTo("trendingIssues");
    assertThat(copy.getMeasureMetaData().getAuthoritativeSource())
        .isEqualTo("https://www.test.org");

    copy.getMeasureMetaData().setLimitations("another limitation");
    copy.getMeasureMetaData().setTrendingIssues("another trending issue");
    copy.getMeasureMetaData().setAuthoritativeSource("https://new.test.org");
    assertThat(measure.getMeasureMetaData().getLimitations()).isEqualTo("limitations");
    assertThat(measure.getMeasureMetaData().getTrendingIssues()).isEqualTo("trendingIssues");
    assertThat(measure.getMeasureMetaData().getAuthoritativeSource())
        .isEqualTo("https://www.test.org");
  }
}
