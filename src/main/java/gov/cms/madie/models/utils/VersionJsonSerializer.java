package gov.cms.madie.models.utils;

import gov.cms.madie.models.common.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JacksonComponent;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ValueSerializer;

@Slf4j
@JacksonComponent
public class VersionJsonSerializer {

  public static class VersionSerializer extends ValueSerializer<Version> {
    @Override
    public void serialize(Version value, JsonGenerator gen, SerializationContext serializers) {
      gen.writeString(value == null ? null : value.toString());
    }
  }

  public static class VersionDeserializer extends ValueDeserializer<Version> {
    @Override
    public Version deserialize(JsonParser jp, DeserializationContext ctxt) {
      try {
        JsonNode node = ctxt.readTree(jp);
        return Version.parse(node.isValueNode() ? node.asString() : "");
      } catch (Exception ex) {
        log.error("An error occurred while deserializing the version", ex);
      }
      return null;
    }
  }
}
