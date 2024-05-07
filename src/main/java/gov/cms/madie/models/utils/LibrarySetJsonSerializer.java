package gov.cms.madie.models.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import gov.cms.madie.models.library.LibrarySet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@Slf4j
@JsonComponent
public class LibrarySetJsonSerializer {

  public static class LibrarySetSerializer extends JsonSerializer<LibrarySet> {
    @Override
    public void serialize(LibrarySet value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value == null ? null : value.toString());
    }
  }

  public static class LibrarySetDeserializer extends JsonDeserializer<LibrarySet> {
    @Override
    public LibrarySet deserialize(JsonParser jp, DeserializationContext ctxt) {
      try {
        return jp.readValueAs(LibrarySet.class);
      } catch (Exception ex) {
        log.error("An error occurred while deserializing the version", ex);
      }
      return null;
    }
  }
}
